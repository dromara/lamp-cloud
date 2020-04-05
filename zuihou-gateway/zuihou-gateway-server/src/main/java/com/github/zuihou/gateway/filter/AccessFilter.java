package com.github.zuihou.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.github.zuihou.base.R;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.gateway.properties.IgnoreTokenProperties;
import com.github.zuihou.jwt.TokenUtil;
import com.github.zuihou.jwt.model.AuthInfo;
import com.github.zuihou.jwt.utils.JwtUtil;
import com.github.zuihou.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.github.zuihou.context.BaseContextConstants.BEARER_HEADER_KEY;
import static com.github.zuihou.context.BaseContextConstants.JWT_KEY_TENANT;
import static com.github.zuihou.exception.code.ExceptionCode.JWT_OFFLINE;

/**
 * 过滤器
 *
 * @author zuihou
 * @date 2019/07/31
 */
@Component
@Slf4j
@EnableConfigurationProperties({IgnoreTokenProperties.class})
public class AccessFilter implements GlobalFilter, Ordered {
    @Value("${spring.profiles.active:dev}")
    protected String profiles;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private IgnoreTokenProperties ignoreTokenProperties;

    @Autowired
    private CacheChannel channel;

    protected boolean isDev() {
        return !StrPool.PROD.equalsIgnoreCase(profiles);
    }

    @Override
    public int getOrder() {
        return -1000;
    }


    /**
     * 忽略应用级token
     *
     * @return
     */
    protected boolean isIgnoreToken(String path) {
        return ignoreTokenProperties.isIgnoreToken(path);
    }

    protected String getHeader(String headerName, ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String token = StrUtil.EMPTY;
        if (headers == null || headers.isEmpty()) {
            return token;
        }

        token = headers.getFirst(headerName);

        if (StringUtils.isNotBlank(token)) {
            return token;
        }

        return request.getQueryParams().getFirst(headerName);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        BaseContextHandler.setGrayVersion(getHeader(BaseContextConstants.GRAY_VERSION, request));

        //2, 解析token
        AuthInfo authInfo = null;
        try {
            //1, 请求头中的租户信息
            String base64Tenant = getHeader(JWT_KEY_TENANT, request);
            if (StrUtil.isNotEmpty(base64Tenant)) {
                String tenant = JwtUtil.base64Decoder(base64Tenant);
                BaseContextHandler.setTenant(tenant);
            }

            // 不进行拦截的地址
            if (isIgnoreToken(request.getPath().toString())) {
                log.debug("access filter not execute");
                return chain.filter(exchange);
            }

            //获取token， 解析，然后想信息放入 heade
            //1, 获取token
            String token = getHeader(BEARER_HEADER_KEY, request);

            // 测试环境 token=test 时，写死一个用户信息，便于测试
            if (isDev() && StrPool.TEST.equalsIgnoreCase(token)) {
                authInfo = new AuthInfo().setAccount("zuihou").setUserId(1L)
                        .setTokenType(BEARER_HEADER_KEY).setName("平台管理员");
            }

            if (authInfo == null) {
                authInfo = tokenUtil.getAuthInfo(token);
            }

            String newToken = JwtUtil.getToken(token);
            String tokenKey = CacheKey.buildKey(newToken);
            CacheObject tokenCache = channel.get(CacheKey.TOKEN_USER_ID, tokenKey);
            if (tokenCache.getValue() == null) {
                // 为空就认为是没登录或者被T会有bug，该 bug 取决于登录成功后，异步调用UserTokenService.save 方法的延迟
            } else if (StrUtil.equals(BizConstant.LOGIN_STATUS, (String) tokenCache.getValue())) {
                return errorResponse(response, JWT_OFFLINE.getMsg(), JWT_OFFLINE.getCode(), 200);
            }
        } catch (BizException e) {
            return errorResponse(response, e.getMessage(), e.getCode(), 200);
        } catch (Exception e) {
            return errorResponse(response, "验证token出错", R.FAIL_CODE, 200);
        }

        ServerHttpRequest.Builder mutate = request.mutate();

        //3, 将信息放入header
        if (authInfo != null) {
            addHeader(mutate, BaseContextConstants.JWT_KEY_ACCOUNT, authInfo.getAccount());
            addHeader(mutate, BaseContextConstants.JWT_KEY_USER_ID, authInfo.getUserId());
            addHeader(mutate, BaseContextConstants.JWT_KEY_NAME, authInfo.getName());
            addHeader(mutate, JWT_KEY_TENANT, BaseContextHandler.getTenant());

            MDC.put(JWT_KEY_TENANT, BaseContextHandler.getTenant());
            MDC.put(BaseContextConstants.JWT_KEY_USER_ID, String.valueOf(authInfo.getUserId()));
        }

        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (ObjectUtil.isEmpty(value)) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = URLUtil.encode(valueStr);
        mutate.header(name, valueEncode);
    }

    protected Mono<Void> errorResponse(ServerHttpResponse response, String errMsg, int errCode, int httpStatusCode) {
        R tokenError = R.fail(errCode, errMsg);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(tokenError.toString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

}
