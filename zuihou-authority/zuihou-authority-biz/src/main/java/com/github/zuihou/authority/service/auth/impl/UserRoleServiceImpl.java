package com.github.zuihou.authority.service.auth.impl;


import com.github.zuihou.authority.dao.auth.RoleMapper;
import com.github.zuihou.authority.dao.auth.UserRoleMapper;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.github.zuihou.common.constant.BizConstant.INIT_ROLE_CODE;

/**
 * <p>
 * 业务实现类
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service

public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public boolean initAdmin(Long userId) {
        Role role = roleMapper.selectOne(Wraps.<Role>lbQ().eq(Role::getCode, INIT_ROLE_CODE));
        if (role == null) {
            throw BizException.wrap("初始化用户角色失败, 无法查询到内置角色:%s", INIT_ROLE_CODE);
        }
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(role.getId())
                .build();

        return super.save(userRole);
    }
}
