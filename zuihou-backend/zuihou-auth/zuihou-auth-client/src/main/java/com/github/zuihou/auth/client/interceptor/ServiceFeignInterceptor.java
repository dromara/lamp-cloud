package com.github.zuihou.auth.client.interceptor;


import com.github.zuihou.auth.client.config.AppAuthConfig;
import com.github.zuihou.commons.context.BaseContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author zuihou
 * @date 2017/9/15
 */
public class ServiceFeignInterceptor implements RequestInterceptor {
    @Autowired
    private AppAuthConfig userAuthConfig;

    public ServiceFeignInterceptor() {
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(userAuthConfig.getTokenHeader(), BaseContextHandler.getToken());
    }


    public void setUserAuthConfig(AppAuthConfig userAuthConfig) {
        this.userAuthConfig = userAuthConfig;
    }

}