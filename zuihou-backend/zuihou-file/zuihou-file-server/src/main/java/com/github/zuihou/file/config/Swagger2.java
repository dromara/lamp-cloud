package com.github.zuihou.file.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.github.zuihou.auth.interceptor.AuthClientContextHandlerInterceptor;
import com.github.zuihou.common.swagger2.Swagger2WebMvcConfigurerAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 *
 * @author zuihou.
 * @create 2017-04-08
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties({Swagger2.Swagger2BaseProperties.class})
public class Swagger2 extends Swagger2WebMvcConfigurerAdapter {
    @Autowired
    Swagger2BaseProperties swagger2Properties;

    @Override
    protected Swagger2BaseProperties getSwagger2BaseProperties() {
        return swagger2Properties;
    }

    @Bean
    public Docket createFileInsideApi() {
        return getDefDocket("file", "priFile");
    }

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
