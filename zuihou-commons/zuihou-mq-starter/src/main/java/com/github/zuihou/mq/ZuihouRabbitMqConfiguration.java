package com.github.zuihou.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * zipkin 客服端自定义配置
 *
 * @author zuihou
 * @date 2019/09/20
 */
@Slf4j
@Import(ZuihouRabbitMqConfiguration.RabbitMqConfiguration.class)
public class ZuihouRabbitMqConfiguration {

    @Configuration
    @ConditionalOnProperty(name = {
            "zuihou.rabbitmq.enabled"
    }, havingValue = "false", matchIfMissing = true)
    @EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
    public static class RabbitMqConfiguration {

    }

}
