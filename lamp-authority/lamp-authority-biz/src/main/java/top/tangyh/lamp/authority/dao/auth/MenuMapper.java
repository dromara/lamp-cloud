package top.tangyh.lamp.authority.dao.auth;

import top.tangyh.lamp.authority.entity.auth.Menu;
import top.tangyh.basic.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
