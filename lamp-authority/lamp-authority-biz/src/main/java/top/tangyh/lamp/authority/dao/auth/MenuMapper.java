package top.tangyh.lamp.authority.dao.auth;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.authority.entity.auth.Menu;
import top.tangyh.lamp.authority.entity.auth.ResourceDataScope;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Repository
public interface MenuMapper extends SuperMapper<Menu> {

    /**
     * 查询用户可用菜单
     *
     * @param userId 用户id
     * @return 菜单
     */
    List<Menu> findVisibleMenu(@Param("userId") Long userId);

    /**
     * 查询指定应用、指定path的数据权限
     *
     * @param path   路径
     * @param idList 数据权限ID
     * @return
     */
    ResourceDataScope getDataScopeByPath(@Param("path") String path,
                                         @Param("idList") List<Long> idList);

    /**
     * 查询指定应用、指定path的默认数据权限
     *
     * @param path 路径
     * @return
     */
    ResourceDataScope getDefDataScopeByPath(@Param("path") String path);
}
