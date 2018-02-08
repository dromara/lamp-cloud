package com.github.zuihou.center.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static com.github.zuihou.center.configuration.GlobalVariableProperties.GLOBAL_VARIABLE_PREFIX;


@ConfigurationProperties(prefix = GLOBAL_VARIABLE_PREFIX)
@Data
@NoArgsConstructor
@Configuration
public class GlobalVariableProperties {
    public static final String GLOBAL_VARIABLE_PREFIX = "zuihou.variable";

    /** 网关服务的url前缀 */
    private String gateWayUrlPrefix = "http://gateway.zuihou.com:9770";
}
