//package com.github.zuihou.config;
//
//
//import javax.sql.DataSource;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.github.zuihou.database.datasource.BaseDbConfiguration;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.aop.Advisor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//
///**
// * zuihou 中心数据库配置 附件配置
// *
// * @author zuihou
// * @createTime 2017-11-18 0:34
// */
//@Configuration
//@MapperScan(
//        basePackages = {
//                "com.github.zuihou.msgs.dao",
//                "com.github.zuihou.sms.dao"
//        },
//        annotationClass = Repository.class,
//        sqlSessionFactoryRef = "msgsSqlSessionFactory")
//public class MsgsDbAutoConfiguration extends BaseDbConfiguration {
//
//    @Bean(name = "msgsDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.msgs")
//    public DataSource db1() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "txMsgs")
//    public DataSourceTransactionManager msgsTransactionManager(@Qualifier("msgsDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean("msgsSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("msgsGlobalConfig") GlobalConfig globalConfig,
//                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler,
//                                               @Qualifier("msgsDataSource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory, new String[]{
////                "classpath:mapper_msgs/**/*Mapper.xml",
//                "classpath:mapper_sms/**/*Mapper.xml"
//        }, globalConfig, myMetaObjectHandler);
//    }
//
//
//    @Bean("msgsTxAdvice")
//    @Override
//    public TransactionInterceptor txAdvice(@Qualifier("txMsgs") PlatformTransactionManager transactionManager) {
//        return super.txAdvice(transactionManager);
//    }
//
//    @Bean("msgsTxAdviceAdvisor")
//    @Override
//    public Advisor txAdviceAdvisor(@Qualifier("txMsgs") PlatformTransactionManager transactionManager) {
//        return super.txAdviceAdvisor(transactionManager);
//    }
//
//    /**
//     * 全局配置
//     *
//     * @return
//     */
//    @Bean("msgsGlobalConfig")
//    public GlobalConfig globalConfig() {
//        return defGlobalConfig();
//    }
//
//}
