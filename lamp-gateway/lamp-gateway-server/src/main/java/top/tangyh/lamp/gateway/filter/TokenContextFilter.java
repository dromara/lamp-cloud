package top.tangyh.lamp.gateway.filter;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.spring.pathmatch.SaPathPatternParserUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
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
import top.tangyh.basic.context.ContextConstants;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.exception.UnauthorizedException;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.common.properties.IgnoreProperties;
import top.tangyh.lamp.common.utils.Base64Util;

import static top.tangyh.basic.context.ContextConstants.APPLICATION_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.APPLICATION_ID_KEY;
import static top.tangyh.basic.context.ContextConstants.CLIENT_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CLIENT_KEY;
import static top.tangyh.basic.context.ContextConstants.CURRENT_COMPANY_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CURRENT_DEPT_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CURRENT_TOP_COMPANY_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.EMPLOYEE_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_COMPANY_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_DEPT_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_EMPLOYEE_ID;
import static top.tangyh.basic.context.ContextConstants.JWT_KEY_TOP_COMPANY_ID;
import static top.tangyh.basic.context.ContextConstants.USER_ID_HEADER;

/**
 * 过滤器
 *
 * @author zuihou
 * @date 2019/07/31
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class TokenContextFilter implements WebFilter, Ordered {
    protected final SaTokenConfig saTokenConfig;
    private final IgnoreProperties ignoreProperties;
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
        HttpHeaders headers = request.getHeaders();
        String token = StrUtil.EMPTY;
        if (headers == null || headers.isEmpty()) {
            return token;
        }

        token = headers.getFirst(headerName);

        if (StrUtil.isNotBlank(token)) {
            return token;
        }

        return request.getQueryParams().getFirst(headerName);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();

        ContextUtil.setGrayVersion(getHeader(ContextConstants.GRAY_VERSION, request));


        try {
            // 2,解码 Authorization
            parseClient(request, mutate);

            // 3, 获取 应用id
            parseApplication(request, mutate);


            Mono<Void> token = parseToken(exchange, chain, mutate);
            if (token != null) {
                return token;
            }

        } catch (UnauthorizedException e) {
            log.error(request.getPath().toString(), e);
            return errorResponse(response, e.getMessage(), e.getCode(), HttpStatus.UNAUTHORIZED);
        } catch (BizException e) {
            log.error(request.getPath().toString(), e);
            return errorResponse(response, e.getMessage(), e.getCode(), HttpStatus.BAD_REQUEST);
        } catch (SaTokenException e) {
            log.error(request.getPath().toString(), e);
            return errorResponse(response, e.getMessage(), e.getCode(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(request.getPath().toString(), e);
            return errorResponse(response, "验证token出错", R.FAIL_CODE, HttpStatus.BAD_REQUEST);
        }

        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    private Mono<Void> parseToken(ServerWebExchange exchange, WebFilterChain chain, ServerHttpRequest.Builder mutate) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 判断接口是否需要忽略token验证
        if (isIgnoreToken(request)) {
            log.debug("当前接口：{}, 不解析用户token", request.getPath());
            return chain.filter(exchange);
        }

        SaSession tokenSession = StpUtil.getTokenSessionByToken(getHeader(saTokenConfig.getTokenName(), request));
        log.info("{}", tokenSession);

        if (tokenSession != null) {
            Long userId = (Long) tokenSession.getLoginId();
            long topCompanyId = tokenSession.getLong(JWT_KEY_TOP_COMPANY_ID);
            long companyId = tokenSession.getLong(JWT_KEY_COMPANY_ID);
            long deptId = tokenSession.getLong(JWT_KEY_DEPT_ID);
            long employeeId = tokenSession.getLong(JWT_KEY_EMPLOYEE_ID);

            mutate.header(USER_ID_HEADER, String.valueOf(userId));
            mutate.header(EMPLOYEE_ID_HEADER, String.valueOf(employeeId));
            mutate.header(CURRENT_TOP_COMPANY_ID_HEADER, String.valueOf(topCompanyId));
            mutate.header(CURRENT_COMPANY_ID_HEADER, String.valueOf(companyId));
            mutate.header(CURRENT_DEPT_ID_HEADER, String.valueOf(deptId));
        }

        return null;
    }

    private void parseClient(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        try {
            String pattern = "/actuator/**";
            if (!SaPathPatternParserUtil.match(pattern, request.getPath().toString())) {
                String base64Authorization = getHeader(CLIENT_KEY, request);
                if (StrUtil.isNotEmpty(base64Authorization)) {
                    String[] client = Base64Util.getClient(base64Authorization);
                    ContextUtil.setClientId(client[0]);
                    addHeader(mutate, CLIENT_ID_HEADER, ContextUtil.getClientId());
                }
            }
        } catch (Exception ignore) {
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
        R tokenError = R.fail(errCode, errMsg);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatusCode(httpStatus);
        DataBuffer dataBuffer = response.bufferFactory().wrap(tokenError.toString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

}
