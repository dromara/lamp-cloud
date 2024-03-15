package top.tangyh.lamp.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import top.tangyh.basic.base.R;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.context.ContextConstants;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.exception.UnauthorizedException;
import top.tangyh.basic.jwt.TokenHelper;
import top.tangyh.basic.jwt.model.Token;
import top.tangyh.basic.jwt.utils.Base64Util;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.common.cache.common.TokenUserIdCacheKeyBuilder;
import top.tangyh.lamp.common.constant.BizConstant;
import top.tangyh.lamp.common.properties.IgnoreProperties;

import static top.tangyh.basic.context.ContextConstants.APPLICATION_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.APPLICATION_ID_KEY;
import static top.tangyh.basic.context.ContextConstants.CLIENT_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CLIENT_KEY;
import static top.tangyh.basic.context.ContextConstants.TOKEN_KEY;
import static top.tangyh.basic.exception.code.ExceptionCode.JWT_NOT_LOGIN;
import static top.tangyh.basic.exception.code.ExceptionCode.JWT_OFFLINE;

/**
 * 过滤器
 *
 * @author zuihou
 * @date 2019/07/31
 */
@Component
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({IgnoreProperties.class})
public class TokenContextFilter implements WebFilter, Ordered {
    private final IgnoreProperties ignoreProperties;
    private final TokenHelper tokenUtil;
    private final CacheOps cacheOps;
    @Value("${spring.profiles.active:dev}")
    protected String profiles;

    protected boolean isDev(String token) {
        return !StrPool.PROD.equalsIgnoreCase(profiles) && (StrPool.TEST_TOKEN.equalsIgnoreCase(token) || StrPool.TEST.equalsIgnoreCase(token));
    }

    @Override
    public int getOrder() {
        return OrderedConstant.TOKEN;
    }


    /**
     * 忽略 用户token
     */
    protected boolean isIgnoreToken(ServerHttpRequest request) {
        return ignoreProperties.isIgnoreUser(request.getMethod().name(), request.getPath().toString());
    }

    /**
     * 忽略 租户编码
     */
    protected boolean isIgnoreTenant(ServerHttpRequest request) {
        return ignoreProperties.isIgnoreTenant(request.getMethod().name(), request.getPath().toString());
    }

    protected String getHeader(String headerName, ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(headerName);
        return StrUtil.isNotBlank(token) ? token : request.getQueryParams().getFirst(headerName);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        try {
            ContextUtil.setGrayVersion(getHeader(ContextConstants.GRAY_VERSION, request));

            ServerHttpRequest.Builder mutate = request.mutate();

            // 1,解码 Authorization
            parseClient(request, mutate);

            // 2, 获取 应用id
            parseApplication(request, mutate);

            //4，解码 并验证 token
            return parseToken(exchange, chain)
                    .doFinally(signalType -> removeHeaders())
                    .onErrorResume(e -> handleException(response, e));
        } catch (Exception e) {
            return handleException(response, e);
        }
    }

    private Mono<Void> parseToken(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();

        // 获取请求头中的token
        String token = getHeader(TOKEN_KEY, request);
        addHeader(mutate, ContextConstants.TOKEN_HEADER, token);

        // 判断接口是否需要忽略token验证
        if (isIgnoreToken(request)) {
            log.debug("当前接口：{}, 不解析用户token", request.getPath());
            return chain.filter(exchange);
        }

        Token tokenObj;
        // 测试环境 && token=test 时，写死一个用户信息，便于测试
        if (isDev(token)) {
            tokenObj = new Token();
            tokenObj.setUserId(1L).setEmployeeId(160566476187631622L).setCurrentTopCompanyId(1L).setCurrentCompanyId(1L).setCurrentDeptId(1L);
            setTokenHeaders(mutate, tokenObj);
            return Mono.empty();
        }

        tokenObj = tokenUtil.parseToken(token);

        // 验证 是否在其他设备登录或被挤下线
        // TOKEN_USER_ID:{token} === T
        CacheKey cacheKey = TokenUserIdCacheKeyBuilder.builder(tokenObj.getUuid());
        CacheResult<String> tokenCache = cacheOps.get(cacheKey);
        if (StrUtil.isEmpty(tokenCache.getValue())) {
            return errorResponse(response, JWT_NOT_LOGIN.getMsg(), JWT_NOT_LOGIN.getCode(), HttpStatus.UNAUTHORIZED);
        } else if (StrUtil.equals(BizConstant.LOGIN_STATUS, tokenCache.getValue())) {
            return errorResponse(response, JWT_OFFLINE.getMsg(), JWT_OFFLINE.getCode(), HttpStatus.UNAUTHORIZED);
        } else {
            // 将请求头中的token解析出来的用户信息重新封装到请求头，转发到业务服务
            // 业务服务在利用HeaderThreadLocalInterceptor拦截器将请求头中的用户信息解析到ThreadLocal中
            setTokenHeaders(mutate, tokenObj);
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }
    }

    private void removeHeaders() {
        if (MDC.get(APPLICATION_ID_HEADER) != null) {
            MDC.remove(APPLICATION_ID_HEADER);
        }

        if (MDC.get(ContextConstants.USER_ID_HEADER) != null) {
            MDC.remove(ContextConstants.USER_ID_HEADER);
        }

        if (MDC.get(ContextConstants.EMPLOYEE_ID_HEADER) != null) {
            MDC.remove(ContextConstants.EMPLOYEE_ID_HEADER);
        }
    }

    private Mono<Void> handleException(ServerHttpResponse response, Throwable e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        int code = R.FAIL_CODE;
        String message = "验证 token 出错";

        if (e instanceof UnauthorizedException) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            code = ((UnauthorizedException) e).getCode();
            message = e.getMessage();
        } else if (e instanceof BizException) {
            code = ((BizException) e).getCode();
            message = e.getMessage();
        }

        return errorResponse(response, message, code, httpStatus);
    }

    private void setTokenHeaders(ServerHttpRequest.Builder mutate, Token tokenObj) {
        ContextUtil.setUserId(tokenObj.getUserId());
        ContextUtil.setEmployeeId(tokenObj.getEmployeeId());
        ContextUtil.setCurrentCompanyId(tokenObj.getCurrentCompanyId());
        ContextUtil.setCurrentTopCompanyId(tokenObj.getCurrentTopCompanyId());
        ContextUtil.setCurrentDeptId(tokenObj.getCurrentDeptId());
        addHeader(mutate, ContextConstants.USER_ID_HEADER, tokenObj.getUserId());
        addHeader(mutate, ContextConstants.EMPLOYEE_ID_HEADER, tokenObj.getEmployeeId());
        addHeader(mutate, ContextConstants.CURRENT_DEPT_ID_HEADER, tokenObj.getCurrentDeptId());
        addHeader(mutate, ContextConstants.CURRENT_COMPANY_ID_HEADER, tokenObj.getCurrentCompanyId());
        addHeader(mutate, ContextConstants.CURRENT_TOP_COMPANY_ID_HEADER, tokenObj.getCurrentTopCompanyId());
        MDC.put(ContextConstants.USER_ID_HEADER, String.valueOf(tokenObj.getUserId()));
        MDC.put(ContextConstants.EMPLOYEE_ID_HEADER, String.valueOf(tokenObj.getEmployeeId()));
    }

    private void parseClient(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        String base64Authorization = getHeader(CLIENT_KEY, request);
        if (StrUtil.isNotEmpty(base64Authorization)) {
            String[] client = Base64Util.getClient(base64Authorization);
            ContextUtil.setClientId(client[0]);
            addHeader(mutate, CLIENT_ID_HEADER, ContextUtil.getClientId());
        }
    }

    private void parseApplication(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        String applicationIdStr = getHeader(APPLICATION_ID_KEY, request);
        if (StrUtil.isNotEmpty(applicationIdStr)) {
            ContextUtil.setApplicationId(applicationIdStr);
            addHeader(mutate, APPLICATION_ID_HEADER, ContextUtil.getApplicationId());
            MDC.put(APPLICATION_ID_HEADER, applicationIdStr);
        }
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = URLUtil.encode(valueStr);
        mutate.header(name, valueEncode);
    }

    protected Mono<Void> errorResponse(ServerHttpResponse response, String errMsg, int errCode, HttpStatus httpStatus) {
        R<Object> tokenError = R.fail(errCode, errMsg);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatusCode(httpStatus);
//        response.setStatusCode(HttpStatus.OK);
        DataBuffer dataBuffer = response.bufferFactory().wrap(tokenError.toString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

}
