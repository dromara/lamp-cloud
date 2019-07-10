package com.github.zuihou.common.config;

import java.util.List;

import com.github.zuihou.common.feign.UserResolveApi;
import com.github.zuihou.common.resolver.TokenArgumentResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author zlt
 * @date 2018/8/25
 */
public class LoginArgResolverConfig implements WebMvcConfigurer {
    @Lazy
    @Autowired
    private UserResolveApi userResolveApi;

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolver(userResolveApi));
    }
}
