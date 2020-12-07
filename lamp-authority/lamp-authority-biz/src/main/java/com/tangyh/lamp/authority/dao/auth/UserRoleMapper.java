package com.tangyh.lamp.authority.dao.auth;

import com.tangyh.lamp.authority.entity.auth.UserRole;
import com.tangyh.basic.base.mapper.SuperMapper;
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
public interface UserRoleMapper extends SuperMapper<UserRole> {

}
