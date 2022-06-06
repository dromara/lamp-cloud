package top.tangyh.lamp.authority.service.auth.impl;

import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperCacheServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.DefValueHelper;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.basic.utils.ValidatorUtil;
import top.tangyh.lamp.authority.dao.auth.MenuMapper;
import top.tangyh.lamp.authority.dto.auth.MenuResourceTreeVO;
import top.tangyh.lamp.authority.entity.auth.Menu;
import top.tangyh.lamp.authority.entity.auth.Resource;
import top.tangyh.lamp.authority.enumeration.auth.AuthorizeType;
import top.tangyh.lamp.authority.enumeration.auth.ResourceTypeEnum;
import top.tangyh.lamp.authority.service.auth.MenuService;
import top.tangyh.lamp.authority.service.auth.ResourceService;
import top.tangyh.lamp.authority.service.auth.UserService;
import top.tangyh.lamp.common.cache.auth.MenuCacheKeyBuilder;
import top.tangyh.lamp.common.cache.auth.UserMenuCacheKeyBuilder;
import top.tangyh.lamp.common.constant.DefValConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.tangyh.basic.utils.StrPool.DEF_PARENT_ID;

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
@RequiredArgsConstructor
public class MenuServiceImpl extends SuperCacheServiceImpl<MenuMapper, Menu> implements MenuService {

    private final ResourceService resourceService;
    private final UserService userService;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new MenuCacheKeyBuilder();
    }

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
     * @param group  分组
     * @param userId 用户id
     * @return 指定用户的拥有的菜单
     */
    @Override
    public List<Menu> findVisibleMenu(String group, Long userId) {
        CacheKey userMenuKey = new UserMenuCacheKeyBuilder().key(userId);
        List<Menu> visibleMenu = new ArrayList<>();

        List<Long> list = cacheOps.get(userMenuKey, k -> {
            log.debug("userMenuKey={}", userMenuKey.getKey());
            visibleMenu.addAll(baseMapper.findVisibleMenu(userId));
            return visibleMenu.stream().map(Menu::getId).collect(Collectors.toList());
        });
        log.debug("visibleMenu={}", visibleMenu.size());
        if (!visibleMenu.isEmpty()) {
            visibleMenu.forEach(this::setCache);
        } else {
            log.debug("list={}", list.size());
            visibleMenu.addAll(findByIds(list, this::listByIds));
        }
        log.debug("visibleMenu2={}", visibleMenu.size());
        return menuListFilterGroup(group, visibleMenu);
    }

    private List<Menu> menuListFilterGroup(String group, List<Menu> visibleMenu) {
        if (StrUtil.isEmpty(group)) {
            return visibleMenu;
        }
        return visibleMenu.stream().filter(menu -> group.equals(menu.getGroup())).collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        boolean result = this.removeByIds(ids);
        if (result) {
            resourceService.removeByMenuIdWithCache(ids);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWithCache(Menu menu) {
        if (StrUtil.containsAny(menu.getResourceType(), ResourceTypeEnum.MENU.getCode())) {
            ArgumentAssert.notEmpty(menu.getPath(), "【地址栏路径】不能为空");
            ArgumentAssert.notEmpty(menu.getComponent(), "【页面路径】不能为空");
            ArgumentAssert.isFalse(checkName(menu.getId(), menu.getLabel()), "【名称】:{}重复", menu.getLabel());
            if (!ValidatorUtil.isUrl(menu.getPath())) {
                ArgumentAssert.isFalse(checkPath(menu.getId(), menu.getPath()), "【地址栏路径】:{}重复", menu.getPath());
            }
        }
        Menu old = getById(menu);
        ArgumentAssert.notNull(old, "您修改的菜单已不存在");
        checkGeneral(menu, true);

        fill(menu);
        Boolean oldIsPublic = DefValueHelper.getOrDef(old.getIsGeneral(), false);
        Boolean newIsPublic = DefValueHelper.getOrDef(menu.getIsGeneral(), false);
        if (!oldIsPublic.equals(newIsPublic)) {
            List<Long> userIds = userService.findAllUserId();
            cacheOps.del(userIds.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }

        return this.updateById(menu);
    }

    public Boolean checkPath(Long id, String path) {
        return baseMapper.selectCount(Wraps.<Menu>lbQ().ne(Menu::getId, id).eq(Menu::getPath, path)) > 0;
    }

    public Boolean checkName(Long id, String name) {
        return baseMapper.selectCount(Wraps.<Menu>lbQ().ne(Menu::getId, id)
                .in(Menu::getResourceType, ResourceTypeEnum.MENU.getCode()).eq(Menu::getLabel, name)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveWithCache(Menu menu) {
        if (StrUtil.containsAny(menu.getResourceType(), ResourceTypeEnum.MENU.getCode())) {
            ArgumentAssert.notEmpty(menu.getPath(), "请填写【地址栏路径】");
            ArgumentAssert.notEmpty(menu.getComponent(), "请填写【页面路径】");
            ArgumentAssert.isFalse(checkName(null, menu.getLabel()), "【名称】:{}重复", menu.getLabel());
            if (!ValidatorUtil.isUrl(menu.getPath())) {
                ArgumentAssert.isFalse(checkPath(null, menu.getPath()), "【地址栏路径】:{}重复", menu.getPath());
            }
        }

        fill(menu);
        menu.setState(Convert.toBool(menu.getState(), true));
        menu.setIsGeneral(Convert.toBool(menu.getIsGeneral(), false));
        menu.setParentId(Convert.toLong(menu.getParentId(), DEF_PARENT_ID));

        checkGeneral(menu, false);

        save(menu);

        if (menu.getIsGeneral()) {
            List<Long> userIds = userService.findAllUserId();
            cacheOps.del(userIds.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return true;
    }

    private void checkGeneral(Menu data, boolean isUpdate) {
        if (data.getIsGeneral() == null) {
            return;
        }

        if (data.getIsGeneral()) {
            // isGeneral 子节点 改成是，父节点全是
            if (!TreeUtil.isRoot(data.getParentId())) {
                Menu parent = getById(data.getParentId());
                ArgumentAssert.notNull(parent, "父节点不存在");
                ArgumentAssert.isTrue(parent.getIsGeneral(), "请先将父节点{} “公共资源”字段修改为：“是”，在修改本节点", parent.getLabel());
            }
            return;
        }

        if (isUpdate) {
            // isGeneral 父节点 改成否，子节点全否
            List<Menu> childrenList = findChildrenByParentId(data.getId());
            if (CollUtil.isNotEmpty(childrenList)) {
                childrenList.forEach(item -> {
                    ArgumentAssert.isFalse(item.getIsGeneral(), "请先将子节点{} “公共资源”字段修改为：“否”，在修改本节点", item.getLabel());
                });
            }
        }
    }

    public List<Menu> findChildrenByParentId(Long parentId) {
        ArgumentAssert.notNull(parentId, "parentId 不能为空");
        return list(Wraps.<Menu>lbQ().in(Menu::getParentId, parentId).orderByAsc(Menu::getSortValue));
    }

    private void fill(Menu resource) {
        if (resource.getParentId() == null || resource.getParentId() <= 0) {
            resource.setParentId(DefValConstants.PARENT_ID);
            resource.setTreePath(DefValConstants.ROOT_PATH);
            resource.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            Menu parent = getByIdCache(resource.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级");
            resource.setTreeGrade(parent.getTreeGrade() + 1);
            resource.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    public List<MenuResourceTreeVO> findMenuResourceTree() {
        List<MenuResourceTreeVO> list = new ArrayList<>();
        List<Menu> menus = super.list(Wraps.<Menu>lbQ().eq(Menu::getResourceType, ResourceTypeEnum.MENU.getCode()));
        List<Resource> resources = resourceService.list();

        List<MenuResourceTreeVO> menuList = menus.stream().map(item -> {
            MenuResourceTreeVO menu = new MenuResourceTreeVO();
            BeanPlusUtil.copyProperties(item, menu);
            menu.setType(AuthorizeType.MENU);
            return menu;
        }).collect(Collectors.toList());
        List<MenuResourceTreeVO> resourceList = resources.stream().map(item -> {
            MenuResourceTreeVO menu = new MenuResourceTreeVO();
            CopyOptions copyOptions = new CopyOptions();
            copyOptions.setFieldMapping(MapUtil.of(Pair.of("name", "label"),
                    Pair.of("name", "label"), Pair.of("menuId", "parentId")));
            BeanPlusUtil.copyProperties(item, menu, copyOptions);
            menu.setType(AuthorizeType.RESOURCE);
            return menu;
        }).collect(Collectors.toList());
        list.addAll(menuList);
        list.addAll(resourceList);
        return TreeUtil.buildTree(list);
    }

    @Override
    public List<MenuResourceTreeVO> findMenuDataScopeTree() {
        List<Menu> datas = super.list(Wraps.<Menu>lbQ().eq(Menu::getResourceType, ResourceTypeEnum.DATA.getCode()));

        // 将id和treePath截取后 合并成list，其中treePath存放的是该节点的所有父节点ID
        Stream<Long> dataScopeIdStream = datas.parallelStream().map(Menu::getId);
        Stream<Long> parentIdStream = datas.parallelStream()
                .map(item -> StrUtil.splitToArray(item.getTreePath(), DefValConstants.ROOT_PATH)) // 将父节点路径截取为父ID数组
                .flatMap(Arrays::stream) // 数组流 转 字符串流
                .filter(ObjectUtil::isNotEmpty) // 去除空数据
                .map(Convert::toLong) // 类型转换
                ;
        // 合并 数据权限ID 和 父ID
        List<Long> resourceIdList = Stream.concat(dataScopeIdStream, parentIdStream).distinct().collect(Collectors.toList());
        if (CollUtil.isEmpty(resourceIdList)) {
            return Collections.emptyList();
        }
        List<Menu> menus = listByIds(resourceIdList);

        List<MenuResourceTreeVO> menuList = menus.stream().map(item -> {
            MenuResourceTreeVO menu = new MenuResourceTreeVO();
            BeanPlusUtil.copyProperties(item, menu);
            if (ResourceTypeEnum.DATA.eq(item.getResourceType())) {
                menu.setType(AuthorizeType.DATA);
            } else {
                menu.setType(AuthorizeType.MENU);
            }
            return menu;
        }).collect(Collectors.toList());

        return TreeUtil.buildTree(menuList);
    }

}
