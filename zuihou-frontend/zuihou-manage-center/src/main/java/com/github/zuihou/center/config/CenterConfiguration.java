package com.github.zuihou.center.config;

import java.util.ArrayList;
import java.util.Collections;

import com.github.zuihou.common.adapter.BaseConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Primary
public class CenterConfiguration extends BaseConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
//        super.addInterceptors(registry);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    private ArrayList<String> getExcludeCommonPathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/v2/api-docs",
                "/swagger-resources/**",
                "/client/**",
                "/login",
                "/register",
                "/jwt/**"
        };
        Collections.addAll(list, urls);
        return list;
    }


}
