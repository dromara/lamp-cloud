package com.github.zuihou.authority.strategy;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zuihou.database.mybatis.auth.DataScopeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建环境角色Context
 *
 * @author tangyh
 * @date 2019/07/22
 */
@Service
public class DataScopeContext {

    @Autowired
    private final Map<String, AbstractDataScopeHandler> strategyMap = new ConcurrentHashMap<>();

    /**
     * Component里边的1是指定其名字，这个会作为key放到strategyMap里
     *
     * @param strategyMap
     */
    public DataScopeContext(Map<String, AbstractDataScopeHandler> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    /**
     * 通过数据权限类型，查询最全、最新的组织id
     *
     * @param orgList
     * @param dsType
     * @return
     */
    public List<Long> getOrgIdsForDataScope(List<Long> orgList, Integer dsType, Long userId) {
        DataScopeType dataScopeType = DataScopeType.get(dsType);
        return Objects.requireNonNull(strategyMap.get(dataScopeType.name()), String.format("找不到数据权限处理器，请确保数据权限类型[dsType=%s]正确", dsType))
                .getOrgIds(orgList, userId);
    }
}
