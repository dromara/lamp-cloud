package com.github.zuihou.file.config.datasource;


import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.zuihou.datasource.configure.BaseDbConfiguration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 一体化平台 中心数据库配置 附件配置
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@MapperScan(
        basePackages = {"com.github.zuihou.file.dao"},
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "fileSqlSessionFactory")
public class FileAutoConfiguration extends BaseDbConfiguration {

    @Bean(name = "fileDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.file")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "txFile")
    @Primary
    public DataSourceTransactionManager fileTransactionManager() {
        return new DataSourceTransactionManager(db1());
    }

    @Bean("fileSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("fileGlobalConfig") GlobalConfig globalConfig,
                                               MybatisPlusProperties properties,
                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(db1());
        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory, properties.getMapperLocations(), globalConfig, myMetaObjectHandler);
    }

    @Bean("fileTxAdvice")
    @Primary
    @Override
    public TransactionInterceptor txAdvice(@Qualifier("txFile") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean("fileTxAdviceAdvisor")
    @Primary
    @Override
    public Advisor txAdviceAdvisor(@Qualifier("txFile") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }

    /**
     * 全局配置
     *
     * @return
     */
    @Bean("fileGlobalConfig")
    public GlobalConfig globalConfig() {
        GlobalConfig conf = new GlobalConfig();
        return conf;
    }

}
