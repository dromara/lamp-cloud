package com.tangyh.lamp.authority.strategy;

import com.tangyh.basic.database.mybatis.auth.DataScopeType;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建环境角色Context
 *
 * @author zuihou
 * @date 2019/07/22
 */
@Service
public class DataScopeContext {

    private final Map<String, AbstractDataScopeHandler> strategyMap = new ConcurrentHashMap<>();

    /**
     * strategyMap 里边的key是指定其名字，这个会作为key放到strategyMap里
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
    public List<Long> getOrgIdsForDataScope(List<Long> orgList, DataScopeType dsType, Long userId) {
        AbstractDataScopeHandler handler = strategyMap.get(dsType.name());
        if (handler == null) {
            return Collections.emptyList();
        }
        return handler.getOrgIds(orgList, userId);
    }
}
