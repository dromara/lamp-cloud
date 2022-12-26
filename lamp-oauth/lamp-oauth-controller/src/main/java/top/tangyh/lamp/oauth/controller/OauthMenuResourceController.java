package top.tangyh.lamp.oauth.controller;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.user.LoginUser;
import top.tangyh.basic.base.R;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.authority.dto.auth.AuthorityResourceDTO;
import top.tangyh.lamp.authority.dto.auth.RouterMeta;
import top.tangyh.lamp.authority.dto.auth.VueRouter;
import top.tangyh.lamp.authority.entity.auth.Menu;
import top.tangyh.lamp.authority.entity.auth.Role;
import top.tangyh.lamp.authority.service.auth.MenuService;
import top.tangyh.lamp.authority.service.auth.RoleService;
import top.tangyh.lamp.model.entity.base.SysResource;
import top.tangyh.lamp.model.entity.base.SysUser;
import top.tangyh.lamp.model.vo.query.ResourceQueryDTO;
import top.tangyh.lamp.security.properties.SecurityProperties;
import top.tangyh.lamp.userinfo.service.ResourceHelperService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 前端控制器
 * 资源 角色 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "资源")
public class OauthMenuResourceController {
    private final ResourceHelperService resourceHelperService;
    private final MenuService menuService;
    private final RoleService roleService;
    private final SecurityProperties securityProperties;

    /**
     * 查询用户可用的所有资源
     *
     * @param resource <br>
     *                 menuId 菜单 <br>
     *                 userId 当前登录人id
     */
    @Operation(summary = "查询用户可用的所有资源", description = "查询用户可用的所有资源")
    @GetMapping("/resource/visible")
    public R<AuthorityResourceDTO> visible(ResourceQueryDTO resource, @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (resource == null) {
            resource = new ResourceQueryDTO();
        }

        if (resource.getUserId() == null) {
            resource.setUserId(sysUser.getId());
        }
        List<SysResource> resourceList = resourceHelperService.findVisibleResource(resource);
        List<Role> roleList = roleService.findRoleByUserId(resource.getUserId());
        return R.success(AuthorityResourceDTO.builder()
                .roleList(roleList.parallelStream().filter(ObjectUtil::isNotEmpty).map(Role::getCode).distinct().collect(Collectors.toList()))
                .resourceList(CollHelper.split(resourceList, SysResource::getCode, StrPool.SEMICOLON))
                .caseSensitive(securityProperties.getCaseSensitive())
                .enabled(securityProperties.getEnabled())
                .build());
    }

    /**
     * 查询用户可用的所有菜单
     *
     * @param group  分组 <br>
     * @param userId 指定用户id
     */
    @Parameters({
            @Parameter(name = "group", description = "菜单组", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "userId", description = "用户id", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户可用的所有菜单", description = "查询用户可用的所有菜单")
    @GetMapping("/menu/menus")
    public R<List<Menu>> myMenus(@RequestParam(value = "group", required = false) String group,
                                 @RequestParam(value = "userId", required = false) Long userId,
                                 @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Menu> list = menuService.findVisibleMenu(group, userId);
        log.info("list={}", list.size());
        List<Menu> tree = TreeUtil.buildTree(list);
        return R.success(tree);
    }

    @Parameters({
            @Parameter(name = "group", description = "菜单组", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "userId", description = "用户id", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "查询用户可用的所有菜单路由树", description = "查询用户可用的所有菜单路由树")
    @GetMapping("/menu/router")
    public R<List<VueRouter>> myRouter(@RequestParam(value = "group", required = false) String group,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @Parameter(hidden = true) @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Menu> list = menuService.findVisibleMenu(group, userId);
//        List<VueRouter> treeList = dozer.mapList(list, VueRouter.class);
        List<VueRouter> treeList = new ArrayList<>();
        list.forEach(item -> {
            VueRouter vueRouter = BeanPlusUtil.toBean(item, VueRouter.class);
            vueRouter.setName(item.getLabel());
            vueRouter.setMeta(RouterMeta.builder()
                    .title(item.getLabel()).icon(item.getIcon()).build());
            treeList.add(vueRouter);
        });

        return R.success(TreeUtil.buildTree(treeList));
    }

    private List<VueRouter> test() {
        List<VueRouter> list = new ArrayList<>();
        VueRouter parent1 = new VueRouter();
        parent1.setName("用户管理");
        parent1.setComponent("lamp/org/user/index");
        parent1.setPath("/org/user");
        RouterMeta rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);
        list.add(parent1);

        parent1 = new VueRouter();
        parent1.setName("用户管理2");
        parent1.setComponent("lamp/org/user/index");
        parent1.setPath("/org/user2");
        rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);
        List<VueRouter> childrens = new ArrayList<>();
        VueRouter children = new VueRouter();
        children.setName("我是用户管理2的隐藏菜单");
        children.setComponent("lamp/org/user/index");
        children.setPath("/org/user2/hideMenu");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        rm.setCurrentActiveMenu("/system/user2");
        rm.setHideMenu(true);
        children.setMeta(rm);
        childrens.add(children);
        parent1.setChildren(childrens);
        list.add(parent1);


        parent1 = new VueRouter();
        parent1.setName("系统管理");
        parent1.setComponent("Layout");
        parent1.setPath("/system");
        rm = new RouterMeta();
        rm.setTitle(parent1.getName());
        parent1.setMeta(rm);

        childrens = new ArrayList<>();
        children = new VueRouter();
        children.setName("角色管理");
        children.setComponent("lamp/system/role/index");
        children.setPath("/system/role");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        children.setMeta(rm);

        VueRouter roleAdd = new VueRouter();
        roleAdd.setName("角色新增");
        roleAdd.setComponent("lamp/system/role/RoleResource");
        roleAdd.setPath("/system/role/add");
        rm = new RouterMeta();
        rm.setTitle(roleAdd.getName());
        rm.setHideMenu(true);
        rm.setCurrentActiveMenu("/system/role");
        roleAdd.setMeta(rm);
        List<VueRouter> roleAddChi = new ArrayList<>();
        roleAddChi.add(roleAdd);
        children.setChildren(roleAddChi);

        childrens.add(children);

        children = new VueRouter();
        children.setName("菜单管理");
        children.setComponent("lamp/system/menu/index");
        children.setPath("/system/menu");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        children.setMeta(rm);
        childrens.add(children);


        children = new VueRouter();
        children.setName("隐藏菜单");
        children.setComponent("lamp/system/parameter/index");
        children.setPath("/system/parameter");
        rm = new RouterMeta();
        rm.setTitle(children.getName());
        rm.setHideMenu(true);
        // 如果是隐藏菜单，一定要配置CurrentActiveMenu
        rm.setCurrentActiveMenu("/system/role");
        children.setMeta(rm);

        childrens.add(children);

        parent1.setChildren(childrens);
        list.add(parent1);


        return list;
    }

}
