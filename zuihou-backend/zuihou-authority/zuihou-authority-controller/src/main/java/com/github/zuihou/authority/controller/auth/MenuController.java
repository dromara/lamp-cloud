package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.MenuDTO;
import com.github.zuihou.authority.entity.auth.Menu;
import com.github.zuihou.authority.service.auth.MenuService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.mybatis.conditions.Wraps;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;

import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", description = "菜单")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 分页查询菜单
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询菜单", notes = "分页查询菜单")
    @PostMapping("/page")
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

}
