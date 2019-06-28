package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.RoleDTO;
import com.github.zuihou.authority.entity.auth.Role;
import com.github.zuihou.authority.service.auth.RoleService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
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
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/role")
@Api(value = "Role", description = "角色")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询角色
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色", notes = "分页查询角色")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Role>> page(@Valid RoleDTO data) {
        IPage<Role> page = getPage();
        // 构建查询条件
        LbqWrapper<Role> query = Wraps.lbQ();
        roleService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询角色
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询角色", notes = "查询角色")
    @GetMapping("/{id}")
    public R<Role> get(@PathVariable Long id) {
        return success(roleService.getById(id));
    }

    /**
     * 保存角色
     *
     * @param role 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存角色", notes = "保存角色不为空的字段")
    @PostMapping
    public R<Role> save(@RequestBody @Valid Role role) {
        roleService.save(role);
        return success(role);
    }

    /**
     * 修改角色
     *
     * @param role 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色", notes = "修改角色不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Role> update(@RequestBody @Valid Role role) {
        roleService.updateById(role);
        return success(role);
    }

    /**
     * 删除角色
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色", notes = "根据id物理删除角色")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        roleService.removeById(id);
        return success(true);
    }

}
