package com.tangyh.lamp.authority.service.core;

import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.basic.model.LoadService;
import com.tangyh.lamp.authority.entity.core.Org;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
public interface OrgService extends SuperCacheService<Org>, LoadService {
    /**
     * 查询指定id集合下的所有子集
     *
     * @param ids id
     * @return 所有的子组织
     */
    List<Org> findChildren(List<Long> ids);

    /**
     * 批量删除以及删除其子节点
     *
     * @param ids id
     * @return 是否成功
     */
    boolean remove(List<Long> ids);

    /**
     * 检测名称是否存在
     *
     * @param id   id
     * @param name name
     * @return boolean
     * @author zuihou
     * @date 2021/5/23 9:37 下午
     * @create [2021/5/23 9:37 下午 ] [tangyh] [初始创建]
     */
    boolean check(Long id, String name);
}
