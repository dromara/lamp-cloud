package com.github.zuihou.swagger2;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 启动类
 * 启动条件：
 * 1，配置文件中zuihou.swagger.enabled=true
 * 2，配置文件中不存在：zuihou.swagger.enabled 值
 *
 * @author zuihou
 * @date 2018/11/18 9:20
 */
@Configuration
@ConditionalOnProperty(name = "zuihou.swagger.enabled", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Configuration {
}
