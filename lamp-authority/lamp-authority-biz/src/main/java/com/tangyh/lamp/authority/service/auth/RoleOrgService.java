package com.tangyh.lamp.authority.service.auth;

import com.tangyh.lamp.authority.entity.auth.RoleOrg;
import com.tangyh.basic.base.service.SuperService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色组织关系
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface RoleOrgService extends SuperService<RoleOrg> {

    /**
     * 根据角色id查询
     *
     * @param roleId 角色id
     * @return 组织Id
     */
    List<Long> listOrgByRoleId(Long roleId);
}
