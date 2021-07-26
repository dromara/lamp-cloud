package top.tangyh.lamp.gateway.fallback;


import top.tangyh.basic.base.R;
import top.tangyh.basic.exception.code.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 响应超时熔断处理器
 *
 * @author zuihou
 */
@RestController
@Slf4j
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<R> fallback(ServerWebExchange exchange) {
//        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
//        ServerWebExchange delegate = ((ServerWebExchangeDecorator) exchange).getDelegate();
//        log.warn("接口调用失败，URL={}", delegate.getRequest().getURI(), exception);
//        if (exception != null && exception.getMessage() != null) {
//            log.warn("接口调用失败: " + exception.getMessage());
//        } else {
//            log.warn("接口调用失败");
//        }
        return Mono.just(R.validFail(ExceptionCode.SYSTEM_TIMEOUT));
    }
}
