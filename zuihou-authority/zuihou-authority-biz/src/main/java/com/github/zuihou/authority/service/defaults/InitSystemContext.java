package com.github.zuihou.authority.service.defaults;

import com.github.zuihou.database.properties.DatabaseProperties;
import com.github.zuihou.utils.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InitSystemContext {
    private final Map<String, InitSystemStrategy> initSystemStrategyMap = new ConcurrentHashMap<>();
    private final DatabaseProperties databaseProperties;

    @Autowired
    public InitSystemContext(Map<String, InitSystemStrategy> strategyMap, DatabaseProperties databaseProperties) {
        strategyMap.forEach(this.initSystemStrategyMap::put);
        this.databaseProperties = databaseProperties;
    }

    /**
     * 初始化系统
     *
     * @param tenant
     */
    public boolean init(String tenant) {
        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
        BizAssert.notNull(initSystemStrategy, String.format("您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name()));

        return initSystemStrategy.init(tenant);
    }

    /**
     * 重置系统
     *
     * @param tenant
     */
    public boolean reset(String tenant) {
        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
        BizAssert.notNull(initSystemStrategy, String.format("您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name()));
        return initSystemStrategy.reset(tenant);
    }
}
