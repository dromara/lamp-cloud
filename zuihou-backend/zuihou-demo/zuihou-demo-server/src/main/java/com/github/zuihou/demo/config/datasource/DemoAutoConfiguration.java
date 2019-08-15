package com.github.zuihou.demo.config.datasource;


import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.zuihou.authority.api.UserApi;
import com.github.zuihou.database.datasource.BaseDbConfiguration;
import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
import com.github.zuihou.utils.SpringUtil;

import io.seata.rm.datasource.DataSourceProxy;
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
 * zuihou 中心数据库配置 附件配置
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@MapperScan(
        basePackages = {"com.github.zuihou.demo.dao"},
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "demoSqlSessionFactory")
public class DemoAutoConfiguration extends BaseDbConfiguration {

    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "txdemo")
    @Primary
    public DataSourceTransactionManager demoTransactionManager(DataSourceProxy dataSourceProxy) {
        return new DataSourceTransactionManager(dataSourceProxy);
    }

    @Bean("demoSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("demoGlobalConfig") GlobalConfig globalConfig,
                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler,
                                               DataSourceProxy dataSourceProxy
    ) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSourceProxy);
        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory,
                new String[]{"classpath:mapper_demo/**/*Mapper.xml"}, globalConfig, myMetaObjectHandler);
    }

    @Bean
    public DataSourceProxy dataSourceProxy(@Qualifier("demoDataSource") DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean("demoTxAdvice")
    @Primary
    @Override
    public TransactionInterceptor txAdvice(@Qualifier("txdemo") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean("demoTxAdviceAdvisor")
    @Primary
    @Override
    public Advisor txAdviceAdvisor(@Qualifier("txdemo") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }

    /**
     * 全局配置
     *
     * @return
     */
    @Bean("demoGlobalConfig")
    public GlobalConfig globalConfig() {
        return defGlobalConfig();
    }


    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Override
    public DataScopeInterceptor getDataScopeInterceptor() {
        return new DataScopeInterceptor((userId) -> SpringUtil.getBean(UserApi.class).getDataScopeById(userId));
    }
}
