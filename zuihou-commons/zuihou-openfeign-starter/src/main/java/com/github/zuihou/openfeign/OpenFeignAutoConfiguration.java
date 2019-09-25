package com.github.zuihou.openfeign;

import java.util.List;

import com.github.zuihou.openfeign.hystrix.ThreadLocalHystrixConcurrencyStrategy;
import com.github.zuihou.openfeign.interceptor.FeignAddHeaderRequestInterceptor;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * OpenFeign 配置
 *
 * @author zuihou
 * @date 2019/07/25
 */
public class OpenFeignAutoConfiguration {
    /**
     * 在feign调用方配置， 解决入参和出参是 date 类型
     *
     * @return
     */
    @Bean
    public DateFormatRegister dateFormatRegister() {
        return new DateFormatRegister();
    }

    /**
     * feign 表单编码
     *
     * @return
     */
    @Bean
    public Encoder feignFormEncoder() {
        List<HttpMessageConverter<?>> converters = new RestTemplate().getMessageConverters();
        ObjectFactory<HttpMessageConverters> factory = () -> new HttpMessageConverters(converters);
        return new SpringFormEncoder(new SpringEncoder(factory));
    }

    /**
     * feign client 请求头传播
     *
     * @return
     */
    @Bean
    public FeignAddHeaderRequestInterceptor getClientTokenInterceptor() {
        return new FeignAddHeaderRequestInterceptor();
    }

    /**
     * 本地线程 Hystrix并发策略
     *
     * @return
     */
    @Bean
    public ThreadLocalHystrixConcurrencyStrategy getThreadLocalHystrixConcurrencyStrategy() {
        return new ThreadLocalHystrixConcurrencyStrategy();
    }

}
