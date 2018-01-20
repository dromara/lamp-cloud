package com.github.zuihou.core.spring.autoconfigure.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Mysql工厂工具类
 *
 * @author zuihou
 */
public final class DataSourceFactory {

    private DataSourceFactory() {
    }

    /**
     * 创建 DruidDataSource 实例
     *
     * @return
     */
    public static DruidDataSource createDataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(3600000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("select current_timestamp()");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            dataSource.setFilters("config");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties("config.decrypt=true");
        return dataSource;
    }


    /**
     * 根据指定的DataSource创建SqlSessionFactoryBean
     *
     * @param rdsDataSource
     * @param resourceLocationPatterns
     * @return
     */
    public static SqlSessionFactoryBean createSqlSessionFactoryBean(
            DataSource rdsDataSource,
            String[] resourceLocationPatterns) throws IOException {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rdsDataSource);

        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        properties.put("reasonable", "true");

        PageInterceptor pi = new PageInterceptor();
        pi.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[]{pi});

        if (!Objects.isNull(resourceLocationPatterns) && resourceLocationPatterns.length > 0) {
            Resource[] all = new Resource[]{};
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            for (String locationPattern : resourceLocationPatterns) {
                all = ArrayUtils.addAll(all, resolver.getResources(locationPattern));
            }
            sessionFactory.setMapperLocations(all);
        }

        return sessionFactory;
    }

}
