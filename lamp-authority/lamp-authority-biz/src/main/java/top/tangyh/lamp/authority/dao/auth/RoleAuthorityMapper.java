package top.tangyh.lamp.authority.dao.auth;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.authority.entity.auth.RoleAuthority;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface RoleAuthorityMapper extends SuperMapper<RoleAuthority> {

    /**
     * 根据用户-角色-数据权限关系，查询可用的数据权限ID
     * <p>
     * 角色被禁用后，用户不在拥有此角色的权限
     *
     * @param userId   用户ID
     * @param category 角色类别
     * @return
     */
    List<Long> selectDataScopeIdFromRoleByUserId(@Param("userId") Long userId, @Param("category") String category);

}
