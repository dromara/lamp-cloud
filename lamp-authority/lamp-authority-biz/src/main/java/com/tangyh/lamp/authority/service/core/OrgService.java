package com.tangyh.lamp.authority.service.core;

import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.lamp.authority.entity.core.Org;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
public interface OrgService extends SuperCacheService<Org> {
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
     * 根据 id 查询组织，并转换成Map结构
     *
     * @param ids id
     * @return id-岗位
     */
    Map<Serializable, Object> findOrgByIds(Set<Serializable> ids);

    /**
     * 根据 id 查询名称，并转换成Map结构
     *
     * @param ids id
     * @return id-名称
     */
    Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids);
}
