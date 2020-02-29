package com.github.zuihou.authority.controller.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.MenuSaveDTO;
import com.github.zuihou.authority.dto.auth.MenuUpdateDTO;
import com.github.zuihou.authority.dto.auth.RouterMeta;
import com.github.zuihou.authority.dto.auth.VueRouter;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
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
import org.springframework.web.bind.annotation.*;

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
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询菜单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询菜单")
    public R<IPage<Menu>> page(Menu data) {
        IPage<Menu> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Menu> query = Wraps.lbQ(data).orderByDesc(Menu::getUpdateTime);
        menuService.page(page, query);
        return success(page);
    }

    /**
     * 查询菜单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @GetMapping("/{id}")
    @SysLog("查询菜单")
    public R<Menu> get(@PathVariable Long id) {
        return success(menuService.getByIdWithCache(id));
    }

    /**
     * 新增菜单
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增菜单", notes = "新增菜单不为空的字段")
    @PostMapping
    @SysLog("新增菜单")
    public R<Menu> save(@RequestBody @Validated MenuSaveDTO data) {
        Menu menu = BeanPlusUtil.toBean(data, Menu.class);

        menuService.saveWithCache(menu);
        return success(menu);
    }


    /**
     * 修改菜单
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单不为空的字段")
    @PutMapping
    @SysLog("修改菜单")
    public R<Menu> update(@RequestBody @Validated(SuperEntity.Update.class) MenuUpdateDTO data) {
        Menu menu = BeanPlusUtil.toBean(data, Menu.class);
        menuService.updateWithCache(menu);
        return success(menu);
    }

    /**
     * 删除菜单
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除菜单", notes = "根据id物理删除菜单")
    @DeleteMapping
    @SysLog("删除菜单")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        menuService.removeByIdWithCache(ids);
        return success(true);
    }

    /**
     * 查询用户可用的所有资源
     *
     * @param group  菜单分组 <br>
     * @param userId 指定用户id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "long", paramType = "query"),
    })
    @ApiOperation(value = "查询用户可用的所有菜单", notes = "查询用户可用的所有菜单")
    @GetMapping
    public R<List<Menu>> myMenus(@RequestParam(value = "group", required = false) String group,
                                 @RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null || userId <= 0) {
            userId = getUserId();
        }
        List<Menu> list = menuService.findVisibleMenu(group, userId);
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
        List<Menu> list = menuService.findVisibleMenu(group, userId);
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
        tenant.setMeta(RouterMeta.builder()
                .title("租户管理").breadcrumb(true).icon("")
                .build());
        tenant.setId(-2L);
        tenant.setParentId(-1L);
        children.add(tenant);

        VueRouter globalUser = new VueRouter();
        globalUser.setPath("/defaults/globaluser");
        globalUser.setComponent("zuihou/defaults/globaluser/Index");
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
        List<Menu> list = menuService.list(Wraps.<Menu>lbQ().orderByAsc(Menu::getSortValue));
        return success(TreeUtil.buildTree(list));
    }
}
