//package top.tangyh.lamp.userinfo.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import top.tangyh.basic.utils.SpringUtils;
//
///**
// * 类型为Feign时，使用的的实现类
// *
// * @author zuihou
// * @date 2020年02月29日21:35:37
// */
//@Configuration
//@EnableFeignClients()
//public class UserResolveFeignConfiguration {
//
//
//    @Bean
//    @ConditionalOnMissingBean(SpringUtils.class)
//    public SpringUtils getSpringUtils(ApplicationContext applicationContext) {
//        SpringUtils instance = SpringUtils.getInstance();
//        SpringUtils.setApplicationContext(applicationContext);
//        return instance;
//    }
//
//}
