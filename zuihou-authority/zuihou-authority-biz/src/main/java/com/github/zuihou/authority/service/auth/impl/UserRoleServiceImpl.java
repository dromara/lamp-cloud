package com.github.zuihou.authority.service.auth.impl;

import com.github.zuihou.authority.dao.auth.UserRoleMapper;
import com.github.zuihou.authority.entity.auth.UserRole;
import com.github.zuihou.authority.service.auth.UserRoleService;
import com.github.zuihou.base.service.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.github.zuihou.common.constant.BizConstant.INIT_ROLE_ID;

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

    @Override
    public void initAdmin(Long userId) {
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(INIT_ROLE_ID)
                .build();

        super.save(userRole);
    }
}
