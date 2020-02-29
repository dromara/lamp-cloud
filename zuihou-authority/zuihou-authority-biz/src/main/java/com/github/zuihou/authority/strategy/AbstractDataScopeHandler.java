package com.github.zuihou.authority.strategy;

import java.util.List;


/**
 * @Classname AbstractDataScopeHandler
 * @Description 创建抽象策略角色 主要作用 数据权限范围使用
 * @Author Created by Lihaodong (alias:小东啊) lihaodongmail@163.com
 * @Date 2019-06-08 15:45
 * @Version 1.0
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
