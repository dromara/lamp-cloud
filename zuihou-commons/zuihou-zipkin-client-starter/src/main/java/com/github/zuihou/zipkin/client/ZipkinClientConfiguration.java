package com.github.zuihou.zipkin.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * zipkin 客服端自定义配置
 *
 * @author tangyh
 * @date 2019/09/20
 */
@Slf4j
@Import(ZipkinClientConfiguration.ZipkinRabbitMqConfiguration.class)
public class ZipkinClientConfiguration {
    /**
     * 该配置用来排除启动时，自动加载mq
     * <p>
     * 适用场景： 开发环境，专心开发业务时，不想启动太多与业务无关的中间件和配置，则可以使用如下 方法1 + 方法2 来禁用zipkin
     * <p>
     * 方法列表：
     * 1，想要禁用zipkin client， 且系统中没有其他地方使用mq， 则配置如下
     * zuihou.zipkin.enabled: false
     * zuihou.rabbitmq.enabled: false
     * <p>
     * 2，想要禁用zipkin client ，但系统中有其他地方需要使用mq， 则配置如下
     * zuihou.zipkin.enabled: false
     * zuihou.rabbitmq.enabled: true
     * <p>
     * 3，以下2种情况， 都会同时启用zipkin client 和 mq, 即：zuihou.zipkin.enabled=true时， 会忽略zuihou.rabbitmq.enabled
     * zuihou.zipkin.enabled: true
     * zuihou.rabbitmq.enabled: true
     * <p>
     * zuihou.zipkin.enabled: true
     * zuihou.rabbitmq.enabled: false
     */
    @Configuration
    @ConditionalOnProperty(name = {
            "zuihou.zipkin.enabled",
            "zuihou.rabbitmq.enabled"
    }, havingValue = "false", matchIfMissing = true)
    @EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
    public static class ZipkinRabbitMqConfiguration {

    }

}
