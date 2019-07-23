package com.github.zuihou.auth.server.configuration;


import com.github.zuihou.auth.server.properties.AuthServerProperties;
import com.github.zuihou.auth.server.utils.JwtTokenServerUtils;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 认证服务端配置
 *
 * @author zuihou
 * @date 2018/11/20
 */
@EnableConfigurationProperties(value = {
        AuthServerProperties.class,
})
public class AuthServerConfiguration {
    @Bean
    public JwtTokenServerUtils getJwtTokenServerUtils(AuthServerProperties authServerProperties) {
        return new JwtTokenServerUtils(authServerProperties);
    }
}
