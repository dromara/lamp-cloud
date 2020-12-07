package com.tangyh.lamp.authority.service.auth;

import com.tangyh.lamp.authority.dto.auth.RoleAuthoritySaveDTO;
import com.tangyh.lamp.authority.dto.auth.UserRoleSaveDTO;
import com.tangyh.lamp.authority.entity.auth.RoleAuthority;
import com.tangyh.basic.base.service.SuperService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface RoleAuthorityService extends SuperService<RoleAuthority> {

    /**
     * 给用户分配角色
     *
     * @param userRole 用于角色
     * @return 是否成功
     */
    boolean saveUserRole(UserRoleSaveDTO userRole);

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleAuthoritySaveDTO 角色授权信息
     * @return 是否成功
     */
    boolean saveRoleAuthority(RoleAuthoritySaveDTO roleAuthoritySaveDTO);

    /**
     * 根据权限id 删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByAuthorityId(List<Long> ids);
}
