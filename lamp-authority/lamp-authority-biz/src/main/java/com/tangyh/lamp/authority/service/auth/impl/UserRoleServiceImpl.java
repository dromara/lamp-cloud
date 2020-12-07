package com.tangyh.lamp.authority.service.auth.impl;


import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.exception.BizException;
import com.tangyh.lamp.authority.dao.auth.RoleMapper;
import com.tangyh.lamp.authority.dao.auth.UserRoleMapper;
import com.tangyh.lamp.authority.entity.auth.Role;
import com.tangyh.lamp.authority.entity.auth.UserRole;
import com.tangyh.lamp.authority.service.auth.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tangyh.lamp.common.constant.BizConstant.INIT_ROLE_CODE;

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

@RequiredArgsConstructor
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final RoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
