package com.github.zuihou.zuul.config.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 为okhttpclient的bean添加过滤器
 * 解决使用 okhttp 时，zipkin无法正常记录调用相关信息
 *
 * @author zuihou
 * @date 2019-12-09 17:23
 */
@Slf4j
public class OkHttpTracePostProcessor implements BeanPostProcessor {

    final OkhttpTraceInterceptor okhttpTraceInterceptor;

    public OkHttpTracePostProcessor(OkhttpTraceInterceptor okhttpTraceInterceptor) {
        this.okhttpTraceInterceptor = okhttpTraceInterceptor;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof okhttp3.OkHttpClient) {
            //添加过滤器，该过滤器主要是将在请求头中添加追踪信息
            okhttp3.OkHttpClient client = (okhttp3.OkHttpClient) bean;
            bean = client.newBuilder()
                    .addInterceptor(okhttpTraceInterceptor)
                    .build();
            log.info("addInterceptor okhttpTraceInterceptor to bean:{}", beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


}
