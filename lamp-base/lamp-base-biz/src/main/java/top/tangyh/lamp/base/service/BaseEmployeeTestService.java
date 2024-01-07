package top.tangyh.lamp.base.service;

import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.lamp.base.entity.user.BaseEmployee;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/9/20 11:31 AM
 * @create [2022/9/20 11:31 AM ] [tangyh] [初始创建]
 */
public interface BaseEmployeeTestService extends SuperCacheManager<BaseEmployee> {
    /**
     * 单体查询
     *
     * @param id id
     * @return top.tangyh.lamp.base.entity.user.BaseEmployee
     * @author tangyh
     * @date 2022/10/28 9:20 AM
     * @create [2022/10/28 9:20 AM ] [tangyh] [初始创建]
     */
    BaseEmployee get(Long id);
}
