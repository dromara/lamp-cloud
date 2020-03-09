package com.github.zuihou.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.github.zuihou.auth.client.properties.AuthClientProperties;
import com.github.zuihou.auth.client.utils.JwtTokenClientUtils;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.base.R;
import com.github.zuihou.common.adapter.IgnoreTokenConfig;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 过滤器
 *
 * @author zuihou
 * @date 2019/07/31
 */
@Component
@Slf4j
public class AccessFilter implements GlobalFilter, Ordered {
    @Value("${spring.profiles.active:dev}")
    protected String profiles;
    @Autowired
    private AuthClientProperties authClientProperties;
    @Autowired
    private JwtTokenClientUtils jwtTokenClientUtils;

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
    protected boolean isIgnoreToken(String uri) {
        return IgnoreTokenConfig.isIgnoreToken(uri);
    }

    protected String getHeader(String headerName, ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String token = StrUtil.EMPTY;
        if (headers == null || headers.isEmpty()) {
            return token;
        }

        List<String> headerList = headers.get(headerName);
        if (headerList == null || headerList.isEmpty()) {
            return token;
        }
        token = headerList.get(0);

        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        if (queryParams == null || queryParams.isEmpty()) {
            return token;
        }
        return queryParams.getFirst(headerName);
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        BaseContextHandler.setGrayVersion(getHeader(BaseContextConstants.GRAY_VERSION, request));

        // 不进行拦截的地址
        if (isIgnoreToken(request.getPath().toString())) {
            log.debug("access filter not execute");
            return chain.filter(exchange);
        }

        //获取token， 解析，然后想信息放入 heade
        //1, 获取token

        String userToken = getHeader(authClientProperties.getUser().getHeaderName(), request);

        //2, 解析token
        JwtUserInfo userInfo = null;

        //添加测试环境的特殊token
        if (isDev() && StrPool.TEST.equalsIgnoreCase(userToken)) {
            userInfo = new JwtUserInfo(1L, "zuihou", "最后");
        }
        try {
            if (userInfo == null) {
                userInfo = jwtTokenClientUtils.getUserInfo(userToken);
            }
        } catch (BizException e) {
            return errorResponse(response, e.getMessage(), e.getCode(), 200);
        } catch (Exception e) {
            return errorResponse(response, "验证token出错", R.FAIL_CODE, 200);
        }

        ServerHttpRequest.Builder mutate = request.mutate();

        //3, 将信息放入header
        if (userInfo != null) {
            addHeader(mutate, BaseContextConstants.JWT_KEY_ACCOUNT, userInfo.getAccount());
            addHeader(mutate, BaseContextConstants.JWT_KEY_USER_ID, userInfo.getUserId());
            addHeader(mutate, BaseContextConstants.JWT_KEY_NAME, userInfo.getName());
        }

        MDC.put(BaseContextConstants.TENANT, BaseContextHandler.getTenant());
        MDC.put(BaseContextConstants.JWT_KEY_USER_ID, String.valueOf(BaseContextHandler.getUserId()));

        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value) {
        if (org.springframework.util.StringUtils.isEmpty(value)) {
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
