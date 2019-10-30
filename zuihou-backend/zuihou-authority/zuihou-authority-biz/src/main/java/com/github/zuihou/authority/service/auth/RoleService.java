package com.github.zuihou.authority.service.auth;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.dto.auth.RoleSaveDTO;
import com.github.zuihou.authority.dto.auth.RoleUpdateDTO;
import com.github.zuihou.authority.entity.auth.Role;

/**
 * <p>
 * 业务接口
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据ID查
     *
     * @param id 主键
     * @return
     */
    Role getByIdWithCache(Long id);

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    boolean removeByIdWithCache(Long id);

    /**
     * 判断用户是否超级管理员
     * @param userId
     * @return
     */
    boolean isSuperAdmin(Long userId);

    /**
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Long userId);

    /**
     * 保存角色
     *
     * @param data
     * @param userId 用户id
     */
    void saveRole(RoleSaveDTO data, Long userId);

    /**
     * 修改
     *
     * @param role
     * @param userId
     */
    void updateRole(RoleUpdateDTO role, Long userId);

    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 角色编码
     * @return
     */
    List<Long> findUserIdByCode(String[] codes);
}
