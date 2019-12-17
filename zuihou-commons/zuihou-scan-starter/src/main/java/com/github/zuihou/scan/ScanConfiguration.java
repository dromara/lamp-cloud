package com.github.zuihou.scan;

import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.base.R;
import com.github.zuihou.common.constant.QueueConstants;
import com.github.zuihou.scan.feign.SystemApiApi;
import com.github.zuihou.scan.feign.SystemApiApiFallback;
import com.github.zuihou.scan.model.SystemApiScanSaveDTO;
import com.github.zuihou.scan.service.SystemApiScanService;
import com.github.zuihou.utils.SpringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 系统api扫描配置类
 *
 * @author zuihou
 * @date 2019/12/16
 */
@ComponentScan(
        basePackages = {
                "com.github.zuihou.scan.controller"
        }
)
@Import({ScanConfiguration.ScanRabbitConfiguration.class, ScanConfiguration.ScanFeignConfiguration.class})
@Slf4j
public class ScanConfiguration {
    @Bean
    @ConditionalOnMissingBean(SpringUtils.class)
    public SpringUtils getSpringUtils(ApplicationContext applicationContext) {
        SpringUtils.setApplicationContext(applicationContext);
        return new SpringUtils();
    }

    @Configuration
    //    @ConditionalOnProperty(name = "zuihou.scan.type", havingValue = "FEIGN", matchIfMissing = true)
    @ConditionalOnProperty(prefix = "zuihou.rabbitmq", name = "enabled", havingValue = "false", matchIfMissing = true)
    @EnableFeignClients(basePackageClasses = SystemApiApi.class)
    @EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
    public static class ScanFeignConfiguration {


        @Bean("systemApiService")
        @ConditionalOnMissingBean(SystemApiScanService.class)
        public SystemApiScanService getSystemApiService() {
            return new SystemApiFeignServiceImpl();
        }

        @Bean
        public SystemApiApiFallback getSystemApiApiFallback() {
            return new SystemApiApiFallback();
        }

        public class SystemApiFeignServiceImpl implements SystemApiScanService {

            @Autowired
            private SystemApiApi systemApiApi;

            @Override
            public Boolean batchSave(SystemApiScanSaveDTO data) {
                R<Boolean> result = systemApiApi.batchSave(data);
                return result.getIsSuccess() ? result.getData() : false;
            }
        }

    }

    /**
     * 使用队列时，消费者需要自行实现
     */
//    @ConditionalOnProperty(name = "zuihou.scan.type", havingValue = "RABBIT")
    @ConditionalOnProperty(prefix = "zuihou.rabbitmq", name = "enabled", havingValue = "true")
//    @ImportAutoConfiguration(value = {RabbitAutoConfiguration.class})
    public static class ScanRabbitConfiguration {

        @Bean("systemApiService")
        @ConditionalOnMissingBean(SystemApiScanService.class)
        public SystemApiScanService getSystemApiService() {
            return new SystemApiRabbitServiceImpl();
        }

        public class SystemApiRabbitServiceImpl implements SystemApiScanService {

            @Autowired
            private RabbitTemplate rabbitTemplate;

            @Override
            public Boolean batchSave(SystemApiScanSaveDTO data) {
                try {
                    rabbitTemplate.convertAndSend(QueueConstants.QUEUE_SCAN_API_RESOURCE, JSONObject.toJSONString(data));
                    return true;
                } catch (Exception e) {
                    log.warn("发送消息失败", e);
                    return false;
                }
            }
        }

    }

}
