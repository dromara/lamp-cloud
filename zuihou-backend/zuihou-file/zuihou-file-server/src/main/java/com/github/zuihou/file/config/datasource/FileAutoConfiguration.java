package com.github.zuihou.file.config.datasource;


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
        basePackages = {"com.github.zuihou.file.dao"},
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "fileSqlSessionFactory")
public class FileAutoConfiguration extends BaseDbConfiguration {

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

    @Bean(name = "fileDataSource")
    public DataSource db1(@Value("${spring.profiles.active}") String profiles, @Qualifier("druidDataSource") DataSource dataSource) {
        if (ArrayUtil.contains(DEV_PROFILES, profiles)) {
            return new P6DataSource(dataSource);
        } else {
            return dataSource;
        }
    }


    @Bean(name = "txFile")
    @Primary
    public DataSourceTransactionManager fileTransactionManager(@Qualifier("fileDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("fileSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("fileGlobalConfig") GlobalConfig globalConfig,
                                               @Qualifier("fileDataSource") DataSource dataSource,
                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory,
                new String[]{"classpath:mapper_file/**/*Mapper.xml"}
                , globalConfig, myMetaObjectHandler);
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
