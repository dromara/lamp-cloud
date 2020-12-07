package com.tangyh.lamp.authority.service.auth;

import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.lamp.authority.dto.auth.RoleSaveDTO;
import com.tangyh.lamp.authority.dto.auth.RoleUpdateDTO;
import com.tangyh.lamp.authority.entity.auth.Role;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface RoleService extends SuperCacheService<Role> {


    /**
     * 根据ID删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 判断用户是否 租户系统的超级管理员
     *
     * @param code 角色编码
     * @return 是否成功
     */
    boolean isPtAdmin(String code);

    /**
     * 查询用户拥有的角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param data   角色
     * @param userId 用户id
     */
    void saveRole(RoleSaveDTO data, Long userId);

    /**
     * 修改
     *
     * @param role   角色
     * @param userId 用户id
     */
    void updateRole(RoleUpdateDTO role, Long userId);

    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 角色编码
     * @return 用户id
     */
    List<Long> findUserIdByCode(String[] codes);

    /**
     * 检测编码重复
     *
     * @param code 角色编码
     * @return 存在返回真
     */
    Boolean check(String code);
}
