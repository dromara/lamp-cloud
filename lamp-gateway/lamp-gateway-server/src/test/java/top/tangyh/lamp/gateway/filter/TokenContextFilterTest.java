package top.tangyh.lamp.gateway.filter;

import cn.dev33.satoken.config.SaTokenConfig;
import org.junit.jupiter.api.Test;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import top.tangyh.lamp.common.properties.IgnoreProperties;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static top.tangyh.basic.context.ContextConstants.CURRENT_COMPANY_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CURRENT_DEPT_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.CURRENT_TOP_COMPANY_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.EMPLOYEE_ID_HEADER;
import static top.tangyh.basic.context.ContextConstants.USER_ID_HEADER;

class TokenContextFilterTest {

    @Test
    void ignoredTokenRouteRemovesCallerSuppliedIdentityHeaders() {
        IgnoreProperties ignoreProperties = new IgnoreProperties();
        ignoreProperties.setAnyUser(Map.of("ALL", Set.of("/anyUser/**")));
        TokenContextFilter filter = new TokenContextFilter(new SaTokenConfig(), ignoreProperties);

        MockServerHttpRequest request = MockServerHttpRequest.get("/anyUser/ping")
                .header(USER_ID_HEADER, "1001")
                .header(EMPLOYEE_ID_HEADER, "2002")
                .header(CURRENT_TOP_COMPANY_ID_HEADER, "3003")
                .header(CURRENT_COMPANY_ID_HEADER, "4004")
                .header(CURRENT_DEPT_ID_HEADER, "5005")
                .header("X-Trace-Id", "trace-1")
                .build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);
        AtomicReference<ServerHttpRequest> forwardedRequest = new AtomicReference<>();
        WebFilterChain chain = forwardedExchange -> {
            forwardedRequest.set(forwardedExchange.getRequest());
            return Mono.empty();
        };

        filter.filter(exchange, chain).block();

        assertThat(forwardedRequest).hasValueSatisfying(forwarded -> {
            assertThat(forwarded.getHeaders().getFirst(USER_ID_HEADER)).isNull();
            assertThat(forwarded.getHeaders().getFirst(EMPLOYEE_ID_HEADER)).isNull();
            assertThat(forwarded.getHeaders().getFirst(CURRENT_TOP_COMPANY_ID_HEADER)).isNull();
            assertThat(forwarded.getHeaders().getFirst(CURRENT_COMPANY_ID_HEADER)).isNull();
            assertThat(forwarded.getHeaders().getFirst(CURRENT_DEPT_ID_HEADER)).isNull();
            assertThat(forwarded.getHeaders().getFirst("X-Trace-Id")).isEqualTo("trace-1");
        });
    }
}
