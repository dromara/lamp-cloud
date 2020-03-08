package com.github.zuihou.authority.controller.auth;

import com.github.zuihou.authority.dto.auth.MenuSaveDTO;
import com.github.zuihou.authority.dto.auth.MenuUpdateDTO;
import com.github.zuihou.authority.dto.auth.RouterMeta;
import com.github.zuihou.authority.dto.auth.VueRouter;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import com.github.zuihou.utils.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", tags = "菜单")
public class MenuController extends SuperCacheController<MenuService, Long, Menu, Menu, MenuSaveDTO, MenuUpdateDTO> {

    @Autowired
    private DozerUtils dozer;

    @Override
    public R<Menu> handlerSave(MenuSaveDTO menuSaveDTO) {
        Menu menu = BeanPlusUtil.toBean(menuSaveDTO, Menu.class);
        baseService.saveWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Menu> handlerUpdate(MenuUpdateDTO model) {
        Menu menu = BeanPlusUtil.toBean(model, Menu.class);
        baseService.updateWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.removeByIdWithCache(ids);
        return success();
    }

    /**
     * 查询用户可用的所有资源
     *
     * @param group  分组 <br>
     * @param userId 指定用户id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "查询用户可用的所有菜单", notes = "查询用户可用的所有菜单")
    @GetMapping("/menus")
    public R<List<Menu>> myMenus(@RequestParam(value = "group", required = false) String group,
                                 @RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null || userId <= 0) {
            userId = getUserId();
        }
        List<Menu> list = baseService.findVisibleMenu(group, userId);
        List<Menu> tree = TreeUtil.buildTree(list);
        return success(tree);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "查询用户可用的所有菜单路由树", notes = "查询用户可用的所有菜单路由树")
    @GetMapping("/router")
    public R<List<VueRouter>> myRouter(@RequestParam(value = "group", required = false) String group,
                                       @RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null || userId <= 0) {
            userId = getUserId();
        }
        List<Menu> list = baseService.findVisibleMenu(group, userId);
        List<VueRouter> treeList = dozer.mapList(list, VueRouter.class);
        return success(TreeUtil.buildTree(treeList));
    }

    @ApiOperation(value = "查询超管菜单路由树", notes = "查询超管菜单路由树")
    @GetMapping("/admin/router")
    public R<List<VueRouter>> adminRouter() {
        return success(buildSuperAdminRouter());
    }

    private List<VueRouter> buildSuperAdminRouter() {
        List<VueRouter> tree = new ArrayList<>();
        List<VueRouter> children = new ArrayList<>();

        VueRouter tenant = new VueRouter();
        tenant.setPath("/defaults/tenant");
        tenant.setComponent("zuihou/defaults/tenant/Index");
        tenant.setHidden(false);
        // 没有name ，刷新页面后，切换菜单会报错：
        // [Vue warn]: Error in nextTick: "TypeError: undefined is not iterable (cannot read property Symbol(Symbol.iterator))"
        // found in
        // <TagsView> at src/layout/components/TagsView/index.vue
        tenant.setName("租户管理");
        tenant.setAlwaysShow(true);
        tenant.setMeta(RouterMeta.builder()
                .title("租户管理").breadcrumb(true).icon("")
                .build());
        tenant.setId(-2L);
        tenant.setParentId(-1L);
        children.add(tenant);

        VueRouter globalUser = new VueRouter();
        globalUser.setPath("/defaults/globaluser");
        globalUser.setComponent("zuihou/defaults/globaluser/Index");
        globalUser.setName("全局用户");
        globalUser.setHidden(false);
        globalUser.setMeta(RouterMeta.builder()
                .title("全局用户").breadcrumb(true).icon("")
                .build());
        globalUser.setId(-3L);
        globalUser.setParentId(-1L);
        children.add(globalUser);

        VueRouter defaults = new VueRouter();
        defaults.setPath("/defaults");
        defaults.setComponent("Layout");
        defaults.setHidden(false);
        defaults.setName("系统设置");
        defaults.setAlwaysShow(true);
        defaults.setMeta(RouterMeta.builder()
                .title("系统设置").icon("el-icon-coin").breadcrumb(true)
                .build());
        defaults.setId(-1L);
        defaults.setChildren(children);

        tree.add(defaults);
        return tree;
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     *
     * @return
     */
    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/tree")
    @SysLog("查询系统所有的菜单")
    public R<List<Menu>> allTree() {
        List<Menu> list = baseService.list(Wraps.<Menu>lbQ().orderByAsc(Menu::getSortValue));
        return success(TreeUtil.buildTree(list));
    }
}
