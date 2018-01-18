package com.github.zuihou.gateway.config;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-18 17:55
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(GateIgnoreProperties.PREFIX)
@Configuration
public class GateIgnoreProperties {
    public static final String PREFIX = "gateway.ignore";
    private List<String> startWithList = Lists.newArrayList();
}
