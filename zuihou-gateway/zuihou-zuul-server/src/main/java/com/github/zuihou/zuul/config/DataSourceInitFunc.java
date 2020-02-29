//package com.github.zuihou.zuul.config;
//
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//
//import com.alibaba.cloud.sentinel.SentinelProperties;
//import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
//import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
//import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
//import com.alibaba.csp.sentinel.property.SentinelProperty;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.TypeReference;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// * sentinel 数据源配置
// *
// * @author zuihou
// * @date 2019/12/06
// */
//@Component
//public class DataSourceInitFunc {
//    @Autowired
//    private SentinelProperties sentinelProperties;
//    @Bean
//    public DataSourceInitFunc init() throws Exception {
//        sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
//            return map.getValue().getNacos() != null;
//        }).forEach(map -> {
//            NacosDataSourceProperties nacos = map.getValue().getNacos();
//            Properties properties = new Properties();
//            properties.setProperty("serverAddr", nacos.getServerAddr());
//            properties.setProperty("namespace", nacos.getNamespace());
//            ReadableDataSource<String, List<FlowRule>> flowRuleDataSource =
////                    new NacosDataSource<>(nacos.getServerAddr(), nacos.getGroupId(), nacos.getDataId(),
////                    source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
////                    }));
//                    new NacosDataSource<>(properties, nacos.getGroupId(), nacos.getDataId(),
//                    source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
//                    }));
//            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
////            GatewayRuleManager.register2Property(flowRuleDataSource.getProperty());
//        });
//        return new DataSourceInitFunc();
//    }
//}
