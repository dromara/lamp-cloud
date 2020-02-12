package com.github.zuihou.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.auth.MenuMapper;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.authority.service.auth.ResourceService;
import com.github.zuihou.common.constant.CacheKey;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.zuihou.utils.StrPool.DEF_PARENT_ID;

/**
 * <p>
 * 业务实现类
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CacheChannel cache;

    /**
     * 查询用户可用菜单
     * 1，查询缓存中存放的当前用户拥有的所有菜单列表 [menuId,menuId...]
     * 2，缓存&DB为空则返回
     * 3，缓存总用户菜单列表 为空，但db存在，则将list便利set到缓存，并直接返回
     * 4，缓存存在用户菜单列表，则根据菜单id遍历去缓存查询菜单。
     * 5，过滤group后，返回
     *
     * <p>
     * 注意：什么地方需要清除 USER_MENU 缓存
     * 给用户重新分配角色时， 角色重新分配资源/菜单时
     *
     * @param group
     * @param userId
     * @return
     */
    @Override
    public List<Menu> findVisibleMenu(String group, Long userId) {
        String key = CacheKey.buildKey(userId);
        List<Menu> visibleMenu = new ArrayList<>();
        CacheObject cacheObject = cache.get(CacheKey.USER_MENU, key, (k) -> {
            visibleMenu.addAll(baseMapper.findVisibleMenu(userId));
            return visibleMenu.stream().mapToLong(Menu::getId).boxed().collect(Collectors.toList());
        });

        if (cacheObject.getValue() == null) {
            return Collections.emptyList();
        }

        if (!visibleMenu.isEmpty()) {
            visibleMenu.forEach((menu) -> {
                String menuKey = CacheKey.buildKey(menu.getId());
                cache.set(CacheKey.MENU, menuKey, menu);
            });

            return menuListFilterGroup(group, visibleMenu);
        }

        List<Long> list = (List<Long>) cacheObject.getValue();

        //使用 this::getByIdWithCache 会导致无法读取缓存
//        List<Menu> menuList = list.stream().map(this::getByIdWithCache).collect(Collectors.toList());
        List<Menu> menuList = list.stream().map(((MenuService) AopContext.currentProxy())::getByIdWithCache)
                .filter(Objects::nonNull).collect(Collectors.toList());

        return menuListFilterGroup(group, menuList);
    }

    private List<Menu> menuListFilterGroup(String group, List<Menu> visibleMenu) {
        if (StrUtil.isEmpty(group)) {
            return visibleMenu;
        }
        return visibleMenu.stream().filter((menu) -> group.equals(menu.getGroup())).collect(Collectors.toList());
    }


    @Override
    @Cacheable(value = CacheKey.MENU, key = "#id")
    public Menu getByIdWithCache(Long id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        boolean result = super.removeByIds(ids);
        if (result) {
            resourceService.removeByMenuIdWithCache(ids);

            String[] keys = ids.stream().map(CacheKey::buildKey).toArray(String[]::new);
            cache.evict(CacheKey.MENU, keys);
        }
        return result;
    }

    @Override
    public boolean updateWithCache(Menu menu) {
        boolean result = super.updateById(menu);
        if (result) {
            String menuKey = CacheKey.buildKey(menu.getId());
            cache.evict(CacheKey.MENU, menuKey);
            cache.clear(CacheKey.USER_MENU);
        }
        return result;
    }

    @Override
    public boolean saveWithCache(Menu menu) {
        menu.setIsEnable(Convert.toBool(menu.getIsEnable(), true));
        menu.setIsPublic(Convert.toBool(menu.getIsPublic(), false));
        menu.setParentId(Convert.toLong(menu.getParentId(), DEF_PARENT_ID));

        super.save(menu);

        String menuKey = CacheKey.buildKey(menu.getId());
        cache.set(CacheKey.MENU, menuKey, menu);
        if (menu.getIsPublic()) {
            cache.evict(CacheKey.USER_MENU);
        }

        return true;
    }
}
