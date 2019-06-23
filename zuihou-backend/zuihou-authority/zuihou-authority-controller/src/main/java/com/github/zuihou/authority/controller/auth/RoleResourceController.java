package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.RoleResourceDTO;
import com.github.zuihou.authority.entity.auth.RoleResource;
import com.github.zuihou.authority.service.auth.RoleResourceService;
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
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleResource")
@Api(value = "RoleResource", description = "角色的资源")
public class RoleResourceController extends BaseController {

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 分页查询角色的资源
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色的资源", notes = "分页查询角色的资源")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<RoleResource>> page(@Valid RoleResourceDTO data) {
        IPage<RoleResource> page = getPage();
        // 构建查询条件
        LbqWrapper<RoleResource> query = Wraps.lbQ();
        roleResourceService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询角色的资源
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询角色的资源", notes = "查询角色的资源")
    @GetMapping("/{id}")
    public Result<RoleResource> get(@PathVariable Long id) {
        return success(roleResourceService.getById(id));
    }

    /**
     * 保存角色的资源
     *
     * @param roleResource 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存角色的资源", notes = "保存角色的资源不为空的字段")
    @PostMapping
    public Result<RoleResource> save(@RequestBody @Valid RoleResource roleResource) {
        roleResourceService.save(roleResource);
        return success(roleResource);
    }

    /**
     * 修改角色的资源
     *
     * @param roleResource 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色的资源", notes = "修改角色的资源不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<RoleResource> update(@RequestBody @Valid RoleResource roleResource) {
        roleResourceService.updateById(roleResource);
        return success(roleResource);
    }

    /**
     * 删除角色的资源
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色的资源", notes = "根据id物理删除角色的资源")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        roleResourceService.removeById(id);
        return success(true);
    }

}
