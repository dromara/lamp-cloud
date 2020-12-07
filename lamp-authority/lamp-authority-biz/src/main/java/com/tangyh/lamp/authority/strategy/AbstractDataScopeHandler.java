package com.tangyh.lamp.authority.strategy;

import java.util.List;


/**
 * 创建抽象策略角色 主要作用 数据权限范围使用
 *
 * @author zuihou
 * @version 1.0
 * @date 2019-06-08 15:45
 */

public interface AbstractDataScopeHandler {

    /**
     * 获取最新的全量的组织id列表
     *
     * @param orgList 已选的组织id
     * @param userId  用户id
     * @return
     */
    List<Long> getOrgIds(List<Long> orgList, Long userId);
}
