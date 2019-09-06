package com.github.zuihou.order.config.datasource;


import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.zuihou.authority.api.UserApi;
import com.github.zuihou.database.datasource.BaseDbConfiguration;
import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
import com.github.zuihou.utils.SpringUtil;
import com.p6spy.engine.spy.P6DataSource;

import cn.hutool.core.util.ArrayUtil;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
        basePackages = {"com.github.zuihou.order.dao"},
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderAutoConfiguration extends BaseDbConfiguration {

    /**
     * 数据源信息
     *
     * @return
     */
    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druid() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "orderDataSource")
    public DataSource db1(@Value("${spring.profiles.active}") String profiles, @Qualifier("druidDataSource") DataSource dataSource) {
        if (ArrayUtil.contains(DEV_PROFILES, profiles)) {
            return new P6DataSource(dataSource);
        } else {
            return dataSource;
        }
    }

    @Bean
    public DataSourceProxy dataSourceProxy(@Qualifier("orderDataSource") DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean(name = "txorder")
    @Primary
    public DataSourceTransactionManager orderTransactionManager(DataSourceProxy dataSourceProxy) {
        return new DataSourceTransactionManager(dataSourceProxy);
    }

    @Bean("orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("orderGlobalConfig") GlobalConfig globalConfig,
                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler,
                                               DataSourceProxy dataSourceProxy) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSourceProxy);
        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory,
                new String[]{"classpath:mapper_order/**/*Mapper.xml"}, globalConfig, myMetaObjectHandler);
    }


    @Bean("orderTxAdvice")
    @Primary
    @Override
    public TransactionInterceptor txAdvice(@Qualifier("txorder") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    @Bean("orderTxAdviceAdvisor")
    @Primary
    @Override
    public Advisor txAdviceAdvisor(@Qualifier("txorder") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }

    /**
     * 全局配置
     *
     * @return
     */
    @Bean("orderGlobalConfig")
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
