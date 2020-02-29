package com.github.zuihou.authority.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuihou.authority.entity.auth.UserRole;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
