//package com.github.zuihou.demo.config.datasource;
//
//
//import cn.hutool.core.util.ArrayUtil;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.github.zuihou.authority.api.UserApi;
//import com.github.zuihou.database.datasource.BaseDbConfiguration;
//import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
//import com.github.zuihou.utils.SpringUtils;
//import com.p6spy.engine.spy.P6DataSource;
//import io.seata.rm.datasource.DataSourceProxy;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.aop.Advisor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//
//import javax.sql.DataSource;
//
///**
// * zuihou 中心数据库配置 附件配置
// *
// * @author zuihou
// * @createTime 2017-11-18 0:34
// */
//@Configuration
//@MapperScan(
//        basePackages = {"com.github.zuihou.demo.dao"},
//        annotationClass = Repository.class,
//        sqlSessionFactoryRef = "sqlSessionFactory")
//public class DemoDataSourceAutoConfiguration extends BaseDbConfiguration {
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    /**
//     * 数据源信息
//     *
//     * @return
//     */
//    @Bean(name = "ds")
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource druid() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "p6ds")
//    public DataSource p6ds(@Qualifier("ds") DataSource dataSource) {
//        if (ArrayUtil.contains(DEV_PROFILES, this.profiles)) {
//            return new P6DataSource(dataSource);
//        } else {
//            return dataSource;
//        }
//    }
//
//    @Bean("dataSourceProxy")
//    public DataSourceProxy dataSourceProxy(@Qualifier("p6ds") DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }
//
//    @Bean(name = "txdemo")
//    @Primary
//    public DataSourceTransactionManager demoTransactionManager(@Qualifier("dataSourceProxy") DataSourceProxy dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean("sqlSessionFactory")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("demoGlobalConfig") GlobalConfig globalConfig,
//                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler,
//                                               @Qualifier("dataSourceProxy") DataSourceProxy dataSource
//    ) throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource);
//        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory,
//                new String[]{"classpath*:mapper_**/**/*Mapper.xml"},
//                globalConfig, myMetaObjectHandler);
//    }
//
//
//    @Bean("demoTxAdvice")
//    @Primary
//    @Override
//    public TransactionInterceptor txAdvice(@Qualifier("txdemo") PlatformTransactionManager transactionManager) {
//        return super.txAdvice(transactionManager);
//    }
//
//    @Bean("demoTxAdviceAdvisor")
//    @Primary
//    @Override
//    public Advisor txAdviceAdvisor(@Qualifier("txdemo") PlatformTransactionManager transactionManager) {
//        return super.txAdviceAdvisor(transactionManager);
//    }
//
//    /**
//     * 全局配置
//     *
//     * @return
//     */
//    @Bean("demoGlobalConfig")
//    public GlobalConfig globalConfig() {
//        return this.defGlobalConfig();
//    }
//
//
//    /**
//     * 数据权限插件
//     *
//     * @return DataScopeInterceptor
//     */
//    @Override
//    public DataScopeInterceptor getDataScopeInterceptor() {
//        return new DataScopeInterceptor((userId) -> SpringUtils.getBean(UserApi.class).getDataScopeById(userId));
//    }
//}
