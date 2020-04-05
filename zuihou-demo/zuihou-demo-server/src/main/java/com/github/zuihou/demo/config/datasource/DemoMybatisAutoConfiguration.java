package com.github.zuihou.demo.config.datasource;


import com.github.zuihou.database.datasource.BaseMybatisConfiguration;
import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
import com.github.zuihou.database.properties.DatabaseProperties;
import com.github.zuihou.oauth.api.UserApi;
import com.github.zuihou.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class DemoMybatisAutoConfiguration extends BaseMybatisConfiguration {


    public DemoMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);

    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Order(10)
    @Bean
    @ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "isDataScope", havingValue = "true", matchIfMissing = true)
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor((userId) -> SpringUtils.getBean(UserApi.class).getDataScopeById(userId));
    }

}
