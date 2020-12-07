//package com.tangyh.lamp.tenant.config.mq;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tangyh.lamp.common.constant.BizMqQueue;
//import com.tangyh.basic.database.properties.DatabaseProperties;
//import com.tangyh.basic.mq.properties.MqProperties;
//import com.tangyh.lamp.tenant.service.DataSourceService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.handler.annotation.Payload;
//
//import java.util.HashMap;
//
///**
// * 消息队列配置
// *
// * @author zuihou
// * @date 2019/12/17
// */
//@Configuration
//@AllArgsConstructor
//@Slf4j
//@ConditionalOnProperty(prefix = MqProperties.PREFIX, name = "enabled", havingValue = "true")
//public class TenantMqAutoConfiguration {
//    private final DataSourceService dataSourceService;
//
//    @Bean
//    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//    public Queue dsQueue() {
//        return new Queue(BizMqQueue.TENANT_DS_QUEUE_BY_AUTHORITY);
//    }
//
//    @Bean
//    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//    public Binding dsQueueBinding() {
//        return new Binding(BizMqQueue.TENANT_DS_QUEUE_BY_AUTHORITY, Binding.DestinationType.QUEUE, BizMqQueue.TENANT_DS_FANOUT_EXCHANGE, "", new HashMap(1));
//    }
//
//    @RabbitListener(queues = BizMqQueue.TENANT_DS_QUEUE_BY_AUTHORITY)
//    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//    public void dsRabbitListener(@Payload String param) {
//        log.debug("异步初始化数据源=={}", param);
//        JSONObject map = JSONObject.parseObject(param);
//        if ("init".equals(map.getString("method"))) {
//            dataSourceService.initDataSource(map.getString("tenant"));
//        } else {
//            dataSourceService.remove(map.getString("tenant"));
//        }
//    }
//}
