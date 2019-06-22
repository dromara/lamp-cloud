package com.github.zuihou.auth.client.configuration;


import com.github.zuihou.auth.client.properties.AuthClientProperties;
import com.github.zuihou.auth.client.utils.JwtTokenClientUtils;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 客户端认证配置
 *
 * @author zuihou
 * @createTime 2017-12-13 15:27
 */
@Configuration
@EnableConfigurationProperties(value = {
        AuthClientProperties.class,
})
public class AuthClientConfiguration {
    @Bean
    public JwtTokenClientUtils getJwtTokenClientUtils(AuthClientProperties authClientProperties) {
        return new JwtTokenClientUtils(authClientProperties);
    }

}