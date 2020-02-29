package com.github.zuihou.zuul.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.exception.code.ExceptionCode;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 基础网关层面服务熔断 处理类
 *
 * @author zuihou
 * @date 2018/07/09
 */
public abstract class AbstractServiceFallbackProvider implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(R.fail(ExceptionCode.SYSTEM_TIMEOUT).toString().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
