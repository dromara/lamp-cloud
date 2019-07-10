package com.github.zuihou.authority.service.auth;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * 查询用户拥有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleByUserId(Long userId);
}
