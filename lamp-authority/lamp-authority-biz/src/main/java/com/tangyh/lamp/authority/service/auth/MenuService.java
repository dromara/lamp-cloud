package com.tangyh.lamp.authority.service.auth;

import com.tangyh.basic.base.service.SuperCacheService;
import com.tangyh.lamp.authority.dto.auth.MenuResourceTreeVO;
import com.tangyh.lamp.authority.entity.auth.Menu;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface MenuService extends SuperCacheService<Menu> {

    /**
     * 根据ID删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 修改菜单
     *
     * @param menu 菜单
     * @return  是否成功
     */
    boolean updateWithCache(Menu menu);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 是否成功
     */
    boolean saveWithCache(Menu menu);

    /**
     * 查询用户可用菜单
     *
     * @param group  组
     * @param userId 用户id
     * @return 菜单
     */
    List<Menu> findVisibleMenu(String group, Long userId);

    /**
     * 查询系统所有的菜单和资源树
     *
     * @return java.util.List<com.tangyh.lamp.authority.dto.auth.MenuResourceTreeVO>
     * @author tangyh
     * @date 2021/6/6 11:41 上午
     * @create [2021/6/6 11:41 上午 ] [tangyh] [初始创建]
     */
    List<MenuResourceTreeVO> findMenuResourceTree();
}
