package top.tangyh.lamp.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author zuihou
 * @date 2021/10/29 14:55
 */
@Slf4j
public class CommonResponseDecorator extends ServerHttpResponseDecorator {

    private DataBufferFactory bufferFactory;

    public CommonResponseDecorator(ServerHttpResponse delegate) {
        super(delegate);
        this.bufferFactory = delegate.bufferFactory();
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        if (body instanceof Flux) {
            Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//            if (this.getDelegate().getHeaders().getContentLength() < 1) {
////                ResponseVo response = new ResponseVo();
////                response.setCode(0);
////                response.setMessage("success");
////                byte[] resp = BeanMapper.toJsonString(response).getBytes(Charset.forName("UTF-8"));
////                this.getDelegate().getHeaders().setContentLength(resp.length);
//
//                return getDelegate().writeWith(Flux.just(bufferFactory.wrap(resp)));
//            }
            this.getDelegate().setStatusCode(HttpStatus.OK);
            MediaType contentType = getDelegate().getHeaders().getContentType();
            log.info("contentType1={}", contentType);
            log.info("contentType2={}", MediaType.APPLICATION_JSON.equals(contentType));

            return super.writeWith(fluxBody.map(dataBuffer -> {
                // probably should reuse buffers
                byte[] content = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(content);
                // 释放掉内存
                DataBufferUtils.release(dataBuffer);
                if (MediaType.APPLICATION_JSON.equals(contentType)) {
                    String rs = new String(content, Charset.forName("UTF-8"));
                    log.info(rs);
                }
//                ResponseVo response = new ResponseVo();
//                response.setCode(0);
//                response.setMessage("success");
//                response.setData(BeanMapper.parseJson(rs, Map.class));
//                byte[] newRs = BeanMapper.toJsonString(response).getBytes(Charset.forName("UTF-8"));
//                this.getDelegate().getHeaders().setContentLength(newRs.length);//如果不重新设置长度则收不到消息。
                return bufferFactory.wrap(content);
            }));
        }
        return super.writeWith(body);
    }
}
