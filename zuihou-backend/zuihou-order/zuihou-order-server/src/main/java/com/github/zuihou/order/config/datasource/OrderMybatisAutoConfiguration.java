package com.github.zuihou.order.config.datasource;


import com.github.zuihou.authority.api.UserApi;
import com.github.zuihou.database.datasource.BaseMybatisConfiguration;
import com.github.zuihou.database.mybatis.auth.DataScopeInterceptor;
import com.github.zuihou.database.properties.DatabaseProperties;
import com.github.zuihou.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 配置一些拦截器
 *
 * @author zuihou
 * @createTime 2017-11-18 0:34
 */
@Configuration
@Slf4j
public class OrderMybatisAutoConfiguration extends BaseMybatisConfiguration {


    public OrderMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);

    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Order(10)
    @Bean
    @ConditionalOnProperty(name = "zuihou.database.isDataScope", havingValue = "true", matchIfMissing = true)
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor((userId) -> SpringUtils.getBean(UserApi.class).getDataScopeById(userId));
    }

}
