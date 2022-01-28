package top.tangyh.lamp.authority.service.auth;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.authority.dto.auth.RoleAuthoritySaveDTO;
import top.tangyh.lamp.authority.dto.auth.RoleUserSaveVO;
import top.tangyh.lamp.authority.dto.auth.UserRoleSaveDTO;
import top.tangyh.lamp.authority.entity.auth.RoleAuthority;

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
     * 根据用户-角色-数据权限关系，查询可用的数据权限ID
     * <p>
     * 角色被禁用后，用户不在拥有此角色的权限
     *
     * @param userId   用户ID
     * @param category 角色类别
     * @return
     */
    List<Long> selectDataScopeIdFromRoleByUserId(Long userId, String category);

    /**
     * 给用户分配角色
     *
     * @param userRole 用于角色
     * @return 是否成功
     */
    boolean saveUserRole(UserRoleSaveDTO userRole);

    /**
     * 给角色绑定用户
     *
     * @param roleUser 用于角色
     * @return 是否成功
     */
    List<Long>  saveRoleUser(RoleUserSaveVO roleUser);

    /**
     * 根据角色查找用户
     *
     * @param roleId 角色ID
     * @return
     */
    List<Long> findUserIdByRoleId(Long roleId);

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
