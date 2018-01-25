package com.github.zuihou.auth.config.datasource;


import com.github.zuihou.core.spring.autoconfigure.datasource.DataSourceFactory;
import com.github.zuihou.core.spring.autoconfigure.datasource.DataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 统一平台 中心数据库配置
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@EnableCaching
@MapperScan(
        basePackages = {
                "com.github.zuihou.admin.repository.account.dao",
                "com.github.zuihou.admin.repository.authority.dao",
        },
        sqlSessionFactoryRef = "sqlSessionFactory_admin")
@EnableConfigurationProperties({AuthDbConfiguration.AdminDataSourceProperties.class})
public class AuthDbConfiguration {

    private final AdminDataSourceProperties dataSourceProperties;

    public AuthDbConfiguration(AdminDataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean(name = "dataSource_admin", initMethod = "init", destroyMethod = "close")
    public DataSource rdsDataSource() {
        return DataSourceFactory.createDataSource(this.dataSourceProperties);
    }

    @Bean(name = "tx_admin")
    public DataSourceTransactionManager rdsTransactionManager() {
        return new DataSourceTransactionManager(rdsDataSource());
    }

    @Bean(name = "sqlSessionFactory_admin")
    public SqlSessionFactory rdsSqlSessionFactory(@Qualifier("dataSource_admin") DataSource rdsDataSource) throws Exception {
        return DataSourceFactory.
                createSqlSessionFactoryBean(rdsDataSource, new String[]{"classpath:mapper_admin/**/*.xml"})
                .getObject();

    }

    @ConfigurationProperties(
            prefix = "zuihou.mysql.admin"
    )
    static class AdminDataSourceProperties extends DataSourceProperties {

    }
}
