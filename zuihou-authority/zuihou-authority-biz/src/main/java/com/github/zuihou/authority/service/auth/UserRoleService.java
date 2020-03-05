package com.github.zuihou.authority.service.auth;

import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.base.service.SuperService;

/**
 * <p>
 * 业务接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface UserRoleService extends SuperService<UserRole> {
    /**
     * 初始化超级管理员角色 权限
     *
     * @param userId
     */
    void initAdmin(Long userId);
}
