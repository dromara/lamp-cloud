package top.tangyh.lamp.gateway.filter;

import cn.hutool.core.util.IdUtil;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import top.tangyh.basic.context.ContextConstants;

/**
 * 生成日志链路追踪id，并传入header中
 *
 * @author zuihou
 * @date 2020年03月09日18:02:47
 */
@Component
public class TraceFilter implements WebFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        //链路追踪id
        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(ContextConstants.TRACE_ID_HEADER, traceId);
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                .headers(h -> h.add(ContextConstants.TRACE_ID_HEADER, traceId))
                .build();

//        ServerHttpResponse originalResponse = exchange.getResponse();
//        ServerHttpResponseDecorator decoratedResponse = new CommonResponseDecorator(originalResponse);
//        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).response(decoratedResponse).build();

        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return OrderedConstant.TRACE;
    }
}
