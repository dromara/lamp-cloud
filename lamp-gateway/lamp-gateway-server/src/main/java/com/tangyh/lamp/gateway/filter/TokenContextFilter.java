package com.tangyh.lamp.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.tangyh.basic.base.R;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.repository.CacheOps;
import com.tangyh.basic.context.ContextConstants;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.jwt.TokenUtil;
import com.tangyh.basic.jwt.model.AuthInfo;
import com.tangyh.basic.jwt.utils.JwtUtil;
import com.tangyh.basic.utils.StrPool;
import com.tangyh.lamp.common.cache.common.TokenUserIdCacheKeyBuilder;
import com.tangyh.lamp.common.constant.BizConstant;
import com.tangyh.lamp.common.properties.IgnoreProperties;
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

import static com.tangyh.basic.context.ContextConstants.BASIC_HEADER_KEY;
import static com.tangyh.basic.context.ContextConstants.BEARER_HEADER_KEY;
import static com.tangyh.basic.context.ContextConstants.JWT_KEY_CLIENT_ID;
import static com.tangyh.basic.context.ContextConstants.JWT_KEY_TENANT;
import static com.tangyh.basic.exception.code.ExceptionCode.JWT_NOT_LOGIN;
import static com.tangyh.basic.exception.code.ExceptionCode.JWT_OFFLINE;

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
    @Value("${spring.profiles.active:dev}")
    protected String profiles;
    @Value("${lamp.database.multiTenantType:SCHEMA}")
    protected String multiTenantType;
    private final IgnoreProperties ignoreProperties;
    private final TokenUtil tokenUtil;
    private final CacheOps cacheOps;

    protected boolean isDev(String token) {
        return !StrPool.PROD.equalsIgnoreCase(profiles) && (StrPool.TEST_TOKEN.equalsIgnoreCase(token) || StrPool.TEST.equalsIgnoreCase(token));
    }

    @Override
    public int getOrder() {
        return -1000;
    }


    /**
     * 忽略 用户token
     */
    protected boolean isIgnoreToken(String path) {
        return ignoreProperties.isIgnoreToken(path);
    }

    /**
     * 忽略 租户编码
     */
    protected boolean isIgnoreTenant(String path) {
        return ignoreProperties.isIgnoreTenant(path);
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
            //1, 解码 请求头中的租户信息
            parseTenant(request, mutate);

            // 2,解码 Authorization 后面完善
            parseClient(request, mutate);

            // 3，解码 并验证 token
            Mono<Void> token = parseToken(exchange, chain);
            if (token != null) {
                return token;
            }
        } catch (BizException e) {
            return errorResponse(response, e.getMessage(), e.getCode(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return errorResponse(response, "验证token出错", R.FAIL_CODE, HttpStatus.BAD_REQUEST);
        }

        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }


    private Mono<Void> parseToken(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();
        // 判断接口是否需要忽略token验证
        if (isIgnoreToken(request.getPath().toString())) {
            log.debug("当前接口：{}, 不解析用户token", request.getPath().toString());
            return chain.filter(exchange);
        }

        // 获取请求头中的token
        String token = getHeader(BEARER_HEADER_KEY, request);

        AuthInfo authInfo;
        // 测试环境 && token=test 时，写死一个用户信息，便于测试
        if (isDev(token)) {
            authInfo = new AuthInfo().setAccount("lamp").setUserId(2L)
                    .setTokenType(BEARER_HEADER_KEY).setName("超级管理员");
        } else {
            authInfo = tokenUtil.getAuthInfo(token);

            // 验证 是否在其他设备登录或被挤下线
            String newToken = JwtUtil.getToken(token);
            // TOKEN_USER_ID:{token} === T
            CacheKey cacheKey = new TokenUserIdCacheKeyBuilder().key(newToken);
            String tokenCache = cacheOps.get(cacheKey);

            if (StrUtil.isEmpty(tokenCache)) {
                return errorResponse(response, JWT_NOT_LOGIN.getMsg(), JWT_NOT_LOGIN.getCode(), HttpStatus.UNAUTHORIZED);
            } else if (StrUtil.equals(BizConstant.LOGIN_STATUS, tokenCache)) {
                return errorResponse(response, JWT_OFFLINE.getMsg(), JWT_OFFLINE.getCode(), HttpStatus.UNAUTHORIZED);
            }
        }

        // 将请求头中的token解析出来的用户信息重新封装到请求头，转发到业务服务
        // 业务服务在利用HeaderThreadLocalInterceptor拦截器将请求头中的用户信息解析到ThreadLocal中
        if (authInfo != null) {
            addHeader(mutate, ContextConstants.JWT_KEY_ACCOUNT, authInfo.getAccount());
            addHeader(mutate, ContextConstants.JWT_KEY_USER_ID, authInfo.getUserId());
            addHeader(mutate, ContextConstants.JWT_KEY_NAME, authInfo.getName());

            MDC.put(ContextConstants.JWT_KEY_USER_ID, String.valueOf(authInfo.getUserId()));
        }
        return null;
    }

    private void parseClient(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        String base64Authorization = getHeader(BASIC_HEADER_KEY, request);
        if (StrUtil.isNotEmpty(base64Authorization)) {
            String[] client = JwtUtil.getClient(base64Authorization);
            ContextUtil.setClientId(client[0]);
            addHeader(mutate, JWT_KEY_CLIENT_ID, ContextUtil.getClientId());
        }
    }

    private void parseTenant(ServerHttpRequest request, ServerHttpRequest.Builder mutate) {
        // 判断是否忽略tenant
        if ("NONE".equals(multiTenantType)) {
            addHeader(mutate, JWT_KEY_TENANT, StrPool.EMPTY);
            MDC.put(JWT_KEY_TENANT, StrPool.EMPTY);
            return;
        }
        if (isIgnoreTenant(request.getPath().toString())) {
            return;
        }
        String base64Tenant = getHeader(JWT_KEY_TENANT, request);
        if (StrUtil.isNotEmpty(base64Tenant)) {
            String tenant = JwtUtil.base64Decoder(base64Tenant);

            ContextUtil.setTenant(tenant);
            addHeader(mutate, JWT_KEY_TENANT, ContextUtil.getTenant());
            MDC.put(JWT_KEY_TENANT, ContextUtil.getTenant());
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
