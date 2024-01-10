package top.tangyh.lamp.gateway.filter;

import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
import top.tangyh.basic.exception.code.ExceptionCode;
import top.tangyh.lamp.common.properties.IgnoreProperties;
import top.tangyh.lamp.gateway.feign.AsyncAnyoneApi;

import java.util.concurrent.Future;

/**
 * 一定要在 TokenContextFilter 之后执行
 *
 * @author zuihou
 * @date 2021/12/7 22:10
 */
@Component
@Slf4j
public class AuthenticationFilter implements WebFilter, Ordered {
    private final static String UN_RESOURCE_AUTHORIZED = "对不起，您无该URI资源的权限!";
    private final static String DEF_MSG = "对不起，您无权限!";
    @Resource
    private IgnoreProperties ignoreProperties;
    @Resource
    private AsyncAnyoneApi anyoneApi;

    @Override
    public int getOrder() {
        return OrderedConstant.AUTHENTICATION;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (!ignoreProperties.getAuthEnabled()) {
            return chain.filter(exchange);
        }

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String eId = request.getHeaders().getFirst(ContextConstants.EMPLOYEE_ID_HEADER);
        String path = request.getPath().toString();
        String method = request.getMethod().name();
        // 1. 是否忽略验证
        // 判断接口是否需要忽略token验证
        if (isIgnoreUriAuth(request)) {
            log.debug("当前接口：{}, 请求方式={}, 忽略权限验证", path, method);
            return chain.filter(exchange);
        }

        Long applicationId = ContextUtil.getApplicationId();
        Long employeeId = ContextUtil.getEmployeeId();

        // 3. 普通用户 需要校验 uri + method 的权限, 租户管理员 拥有分配给该企业的所有 资源权限
        long apiStart = System.currentTimeMillis();
        Future<R<Boolean>> hasApiAsync = anyoneApi.checkUri(employeeId, applicationId, path, method);
        R<Boolean> hasApi = hasApiAsync.get();
        long apiEnd = System.currentTimeMillis();
        log.info("校验api权限:{} - {}  耗时:{}", path, method, (apiEnd - apiStart));
        if (!hasApi.getIsSuccess()) {
            log.warn("3. uri={}, applicationId={}, employeeId={}, eId={}, hasApp={}", request.getPath(), applicationId, employeeId, eId, hasApi);
            return forbidden(response, hasApi.getMsg());
        } else if (hasApi.getData() == null || !hasApi.getData()) {
            log.warn("4. uri={}, applicationId={}, employeeId={}, eId={}, hasApp={}", request.getPath(), applicationId, employeeId, eId, hasApi);
            return forbidden(response, UN_RESOURCE_AUTHORIZED);
        }
        log.info("thread id ={}, name={}", Thread.currentThread().getId(), Thread.currentThread().getName());
        ContextUtil.remove();
        return chain.filter(exchange);
    }

    /**
     * 忽略 权限
     * 路径中包含 anyone
     */
    protected boolean isIgnoreUriAuth(ServerHttpRequest request) {
        return ignoreProperties.isIgnoreUriAuth(request.getMethod().name(), request.getPath().toString());
    }

    public Mono<Void> forbidden(ServerHttpResponse response, String message) {
        ContextUtil.remove();
        R<String> r = R.fail(ExceptionCode.FORBIDDEN.getCode(), message == null ? DEF_MSG : message);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(r.toString().getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}
