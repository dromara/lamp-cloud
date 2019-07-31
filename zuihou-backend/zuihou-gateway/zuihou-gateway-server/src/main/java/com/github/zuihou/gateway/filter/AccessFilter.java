package com.github.zuihou.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/07/31
 */
@Component
@Slf4j
public class AccessFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        ServerHttpRequest build = mutate.build();

        return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
    }
}
