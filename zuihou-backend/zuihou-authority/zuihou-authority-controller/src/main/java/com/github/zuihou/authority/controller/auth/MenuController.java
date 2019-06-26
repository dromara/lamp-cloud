package com.github.zuihou.authority.controller.auth;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.MenuDTO;
import com.github.zuihou.authority.dto.auth.MenuTreeDTO;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.common.utils.TreeUtil;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.mybatis.conditions.Wraps;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", description = "菜单")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private DozerUtils dozerUtils;

    /**
     * 分页查询菜单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<Menu>> page(@Valid MenuDTO data) {
        IPage<Menu> page = getPage();
        // 构建查询条件
        LbqWrapper<Menu> query = Wraps.lbQ();
        menuService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询菜单
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询菜单", notes = "查询菜单")
    @GetMapping("/{id}")
    public Result<Menu> get(@PathVariable Long id) {
        return success(menuService.getById(id));
    }

    /**
     * 保存菜单
     *
     * @param menu 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存菜单", notes = "保存菜单不为空的字段")
    @PostMapping
    public Result<Menu> save(@RequestBody @Valid Menu menu) {
        menuService.save(menu);
        return success(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改菜单", notes = "修改菜单不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<Menu> update(@RequestBody @Valid Menu menu) {
        menuService.updateById(menu);
        return success(menu);
    }

    /**
     * 删除菜单
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除菜单", notes = "根据id物理删除菜单")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        menuService.removeById(id);
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
    public Result<List<MenuTreeDTO>> myMenus(@RequestParam(value = "group", required = false) String group, @RequestParam(value = "userId", required = false) Long userId) {
        if (userId == null || userId <= 0) {
            userId = getUserId();
        }
        List<Menu> list = menuService.findVisibleMenu(group, userId);
        List<MenuTreeDTO> treeList = dozerUtils.mapList(list, MenuTreeDTO.class);

        return Result.success(TreeUtil.builderTreeOrdered(treeList));
    }

    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/all")
    public Result<List<Menu>> all() {
        return Result.success(menuService.list());
    }
}
