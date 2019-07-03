package com.github.zuihou.authority.config;

import com.github.zuihou.auth.interceptor.AuthClientContextHandlerInterceptor;
import com.github.zuihou.common.adapter.BaseConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zuihou
 * @createTime 2017-12-15 14:42
 */
@Configuration
public class AuthorityConfiguration extends BaseConfig {

    /**
     * 覆盖父类的拦截器
     *
     * @return
     */
    @Bean
    @Override
    public HandlerInterceptor getHandlerInterceptor() {
        return new AuthClientContextHandlerInterceptor();
    }

}
