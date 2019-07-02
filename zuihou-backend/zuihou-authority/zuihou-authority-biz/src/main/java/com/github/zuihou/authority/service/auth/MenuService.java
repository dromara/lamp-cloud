package com.github.zuihou.authority.service.auth;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.auth.Menu;

/**
 * <p>
 * 业务接口
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
public interface MenuService extends IService<Menu> {

    /**
     * 查询用户可用菜单
     *
     * @param group
     * @param userId
     * @return
     */
    List<Menu> findVisibleMenu(String group, Long userId);
}
