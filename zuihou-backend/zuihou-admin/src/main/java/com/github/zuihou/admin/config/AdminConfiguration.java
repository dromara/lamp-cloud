//package com.github.zuihou.admin.config;
//
//import com.hengyunsoft.base.id.IdGenerate;
//import com.hengyunsoft.base.id.SnowflakeIDGenerate;
//import com.hengyunsoft.commons.handler.GlobalExceptionHandler;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author tyh
// * @createTime 2017-12-15 14:42
// */
//@Configuration
//public class AdminConfiguration {
//    /**
//     * 全局异常处理
//     *
//     * @return
//     */
//    @Bean
//    public GlobalExceptionHandler getGlobalExceptionHandler() {
//        return new GlobalExceptionHandler();
//    }
//
//    @Bean
//    public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
//        return new SnowflakeIDGenerate(machineCode);
//    }
//
//}
