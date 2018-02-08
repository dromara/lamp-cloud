package com.github.zuihou.center.config;

import com.github.zuihou.center.interceptor.TokenSetInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Collections;

@Configuration("centerConfig")
@Primary
public class CenterConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getTokenSetInterceptor()).addPathPatterns("/**").excludePathPatterns(commonPathPatterns.toArray(new String[]{}));
        super.addInterceptors(registry);
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

    @Bean
    TokenSetInterceptor getTokenSetInterceptor() {
        return new TokenSetInterceptor();
    }

}
