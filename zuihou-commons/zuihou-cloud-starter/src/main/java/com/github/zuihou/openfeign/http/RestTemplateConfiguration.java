package com.github.zuihou.openfeign.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Http RestTemplateHeaderInterceptor 配置
 *
 * @author zuihou
 */
@Configuration
@ConditionalOnClass(okhttp3.OkHttpClient.class)
@AllArgsConstructor
public class RestTemplateConfiguration {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private final ObjectMapper objectMapper;

    /**
     * dev, test 环境打印出BODY
     *
     * @return HttpLoggingInterceptor
     */
    @Bean("httpLoggingInterceptor")
    @Profile({"dev", "test"})
    public HttpLoggingInterceptor testLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new OkHttpSlf4jLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * docker 环境 打印 请求头
     *
     * @return HttpLoggingInterceptor
     */
    @Bean("httpLoggingInterceptor")
    @Profile("docker")
    public HttpLoggingInterceptor onTestLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new OkHttpSlf4jLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return interceptor;
    }

    /**
     * prod 环境只打印请求url
     *
     * @return HttpLoggingInterceptor
     */
    @Bean("httpLoggingInterceptor")
    @Profile("prod")
    public HttpLoggingInterceptor prodLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new OkHttpSlf4jLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    /**
     * okhttp3 链接池配置
     *
     * @param connectionPoolFactory 链接池配置
     * @param httpClientProperties  httpClient配置
     * @return okhttp3.ConnectionPool
     */
    @Bean
    @ConditionalOnMissingBean(okhttp3.ConnectionPool.class)
    public okhttp3.ConnectionPool httpClientConnectionPool(
            FeignHttpClientProperties httpClientProperties,
            OkHttpClientConnectionPoolFactory connectionPoolFactory) {
        Integer maxTotalConnections = httpClientProperties.getMaxConnections();
        Long timeToLive = httpClientProperties.getTimeToLive();
        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
    }

//    @Bean
//    public RestTemplateHeaderInterceptor requestHeaderInterceptor(
//            @Autowired(required = false) @Nullable BladeHystrixAccountGetter accountGetter,
//            BladeHystrixHeadersProperties properties) {
//        return new RestTemplateHeaderInterceptor(accountGetter, properties);
//    }

    /**
     * 配置OkHttpClient
     *
     * @param httpClientFactory    httpClient 工厂
     * @param connectionPool       链接池配置
     * @param httpClientProperties httpClient配置
     * @param interceptor          拦截器
     * @return OkHttpClient
     */
    @Bean
    @ConditionalOnMissingBean(okhttp3.OkHttpClient.class)
    public okhttp3.OkHttpClient httpClient(
            OkHttpClientFactory httpClientFactory,
            okhttp3.ConnectionPool connectionPool,
            FeignHttpClientProperties httpClientProperties,
            HttpLoggingInterceptor interceptor) {
        Boolean followRedirects = httpClientProperties.isFollowRedirects();
        Integer connectTimeout = httpClientProperties.getConnectionTimeout();
        return httpClientFactory.createBuilder(httpClientProperties.isDisableSslValidation())
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .followRedirects(followRedirects)
                .connectionPool(connectionPool)
                .addInterceptor(interceptor)
                .build();
    }

//    /**
//     * 支持负载均衡的 LbRestTemplate
//     *
//     * @param httpClient  OkHttpClient
//     * @param interceptor RestTemplateHeaderInterceptor
//     * @return LbRestTemplate
//     */
//    @Bean
//    @LoadBalanced
//    @ConditionalOnMissingBean(LbRestTemplate.class)
//    public LbRestTemplate lbRestTemplate(okhttp3.OkHttpClient httpClient, RestTemplateHeaderInterceptor interceptor) {
//        LbRestTemplate lbRestTemplate = new LbRestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient));
//        lbRestTemplate.setInterceptors(Collections.singletonList(interceptor));
//        this.configMessageConverters(lbRestTemplate.getMessageConverters());
//        return lbRestTemplate;
//    }

    /**
     * 普通的 RestTemplate，不透传请求头，一般只做外部 http 调用
     *
     * @param httpClient OkHttpClient
     * @return RestTemplate
     */
    @Bean
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate(okhttp3.OkHttpClient httpClient) {
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory(httpClient));
        this.configMessageConverters(restTemplate.getMessageConverters());
        return restTemplate;
    }

    private void configMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof MappingJackson2HttpMessageConverter);
        converters.add(new StringHttpMessageConverter(UTF_8));
        converters.add(new MappingJackson2HttpMessageConverter(this.objectMapper));
    }
}
