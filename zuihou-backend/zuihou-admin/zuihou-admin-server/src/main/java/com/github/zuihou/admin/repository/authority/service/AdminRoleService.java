package com.github.zuihou.admin.repository.authority.service;

import com.github.zuihou.admin.entity.authority.po.AdminRole;
import com.github.zuihou.admin.repository.authority.example.AdminRoleExample;
import com.github.zuihou.base.service.BaseService;

import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-15 10:45
 */
public interface AdminRoleService extends BaseService<Long, AdminRole, AdminRoleExample> {
    /**
     * 检测code是否存在
     * @param appId appId
     * @param code 角色code
     * @return  存在返回true ，不存在返回false
     */
    boolean check(String appId, String code);

    /**
     * 给角色授权应用
     * @param appId appId
     * @param roleId 角色id
     * @param applicationIdList 应用id
     */
    void authorityAdmin(String appId, Long roleId, List<Long> applicationIdList);

    /**
     * 给角色授权资源+菜单
     * @param appId appId
     * @param menuGroupCode 菜单组
     * @param roleId 角色id
     * @param elementIdList 资源id
     */
    void authorityResources(String appId, String menuGroupCode,  Long roleId, List<Long>  elementIdList);

    /**
     * 根据userId查找用户拥有的角色
     * @param appId appId
     * @param applicationId 应用id
     * @return
     */
    List<AdminRole> findRole(String appId, Long applicationId);
}
