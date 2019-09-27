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
//    /**
//     * 这段注释已经过时， 最新用法，请看下面的
//     *
//     * 该配置用来排除启动时，自动加载mq
//     * <p>
//     * 适用场景： 开发环境，专心开发业务时，不想启动太多与业务无关的中间件和配置，则可以使用如下 方法1 + 方法2 来禁用zipkin
//     * <p>
//     * 方法列表：
//     * 1，想要禁用zipkin client， 且系统中没有其他地方使用mq， 则配置如下
//     * zuihou.zipkin.enabled: false
//     * zuihou.rabbitmq.enabled: false
//     * <p>
//     * 2，想要禁用zipkin client ，但系统中有其他地方需要使用mq， 则配置如下
//     * zuihou.zipkin.enabled: false
//     * zuihou.rabbitmq.enabled: true
//     * <p>
//     * 3，以下2种情况， 都会同时启用zipkin client 和 mq, 即：zuihou.zipkin.enabled=true时， 会忽略zuihou.rabbitmq.enabled
//     * zuihou.zipkin.enabled: true
//     * zuihou.rabbitmq.enabled: true
//     * <p>
//     * zuihou.zipkin.enabled: true
//     * zuihou.rabbitmq.enabled: false
//     */

    /**
     * 该配置用来排除启动时，自动加载mq
     * 适用场景： 开发环境，专心开发业务时，不想启动太多与业务无关的中间件和配置，则可以使用如下 方法1 + 方法2 来禁用zipkin
     *
     * 1, 想要禁用 zipkin client           (zuihou.rabbitmq.enabled 的开关不会影响zipkin了)
     * zuihou.zipkin.enabled: false
     *
     * 2, 想要使用zipkin client ，并且使用rabbit作为传输介质 （需要连接mq）
     * zuihou.zipkin.enabled: true
     * zuihou.rabbitmq.enabled: true
     *
     * 3，想要使用zipkin client ，并且使用 http 方式作为传输介质 (必须启动zuihou-zipkin项目，否则程序可能会报错)
     * zuihou.zipkin.enabled: true
     * zuihou.rabbitmq.enabled: false
     *
     * 并且在zuihou-zipkin 项目中注释如下 依赖：
     *  <dependency>
     *             <groupId>io.zipkin.java</groupId>
     *             <artifactId>zipkin-autoconfigure-collector-rabbitmq</artifactId>
     *             <version>${zipkin.version}</version>
     *         </dependency>
     */
    @Configuration
    @ConditionalOnProperty(name = {
//            "zuihou.zipkin.enabled",
            "zuihou.rabbitmq.enabled"
    }, havingValue = "false", matchIfMissing = true)
    @EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
    public static class ZipkinRabbitMqConfiguration {

    }

}
