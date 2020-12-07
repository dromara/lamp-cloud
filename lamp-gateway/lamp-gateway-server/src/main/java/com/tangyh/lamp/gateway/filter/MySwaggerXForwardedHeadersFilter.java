package com.tangyh.lamp.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.headers.HttpHeadersFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;
import java.util.LinkedHashSet;

import static org.springframework.cloud.gateway.filter.headers.XForwardedHeadersFilter.X_FORWARDED_PREFIX_HEADER;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 1, spring.cloud.gateway.x-forwarded.prefixEnabled: false ， 仅供官方 XForwardedHeadersFilter 的部分功能
 * 2，重写 XForwardedHeadersFilter 禁用掉的部分功能
 *
 * @author zuihou
 * @date 2019/08/13
 */
@Component
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class MySwaggerXForwardedHeadersFilter implements HttpHeadersFilter, Ordered {
    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean supports(Type type) {
        return true;
    }

    @Override
    public HttpHeaders filter(HttpHeaders input, ServerWebExchange exchange) {
        HttpHeaders original = input;
        HttpHeaders updated = new HttpHeaders();

        original.entrySet().stream()
                .forEach(entry -> updated.addAll(entry.getKey(), entry.getValue()));

        LinkedHashSet<URI> originalUris = exchange.getAttribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI requestUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);

        if (originalUris != null && requestUri != null) {

            originalUris.stream().forEach(originalUri -> {
                if (originalUri != null && originalUri.getPath() != null) {
                    String prefix = originalUri.getPath();

                    String originalUriPath = stripTrailingSlash(originalUri);
                    String requestUriPath = stripTrailingSlash(requestUri);

                    if (requestUriPath != null && (originalUriPath.endsWith(requestUriPath))) {
                        prefix = originalUriPath.replace(requestUriPath, "");
                        if (prefix != null && prefix.length() > 0 &&
                                prefix.length() <= originalUri.getPath().length()) {
                            // 就是这里跟官方的XForwardedHeadersFilter 不同，多了：contextPath
                            updated.set(X_FORWARDED_PREFIX_HEADER, contextPath + prefix);
                        }
                    }
                }
            });
        }

        return updated;
    }

    private String stripTrailingSlash(URI uri) {
        if (uri.getPath().endsWith("/")) {
            return uri.getPath().substring(0, uri.getPath().length() - 1);
        } else {
            return uri.getPath();
        }
    }
}
