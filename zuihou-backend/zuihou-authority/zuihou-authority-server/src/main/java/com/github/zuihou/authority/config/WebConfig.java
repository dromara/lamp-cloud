//package com.github.zuihou.authority.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * web 配置
// *
// * @author pdy
// * @date 2019-06-12
// */
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    /**
//     * 注册 拦截器
//     *
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(frontCacheInterceptor());
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//
//}
