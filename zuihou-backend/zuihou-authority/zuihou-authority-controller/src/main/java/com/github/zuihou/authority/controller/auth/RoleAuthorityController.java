package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.RoleAuthorityDTO;
import com.github.zuihou.authority.entity.auth.RoleAuthority;
import com.github.zuihou.authority.service.auth.RoleAuthorityService;
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
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleAuthority")
@Api(value = "RoleAuthority", description = "角色的资源")
public class RoleAuthorityController extends BaseController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 分页查询角色的资源
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色的资源", notes = "分页查询角色的资源")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<RoleAuthority>> page(@Valid RoleAuthorityDTO data) {
        IPage<RoleAuthority> page = getPage();
        // 构建查询条件
        LbqWrapper<RoleAuthority> query = Wraps.lbQ();
        roleAuthorityService.page(page, query);
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
    public R<RoleAuthority> get(@PathVariable Long id) {
        return success(roleAuthorityService.getById(id));
    }

    /**
     * 保存角色的资源
     *
     * @param roleAuthority 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存角色的资源", notes = "保存角色的资源不为空的字段")
    @PostMapping
    public R<RoleAuthority> save(@RequestBody @Valid RoleAuthority roleAuthority) {
        roleAuthorityService.save(roleAuthority);
        return success(roleAuthority);
    }

    /**
     * 修改角色的资源
     *
     * @param roleAuthority 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色的资源", notes = "修改角色的资源不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<RoleAuthority> update(@RequestBody @Valid RoleAuthority roleAuthority) {
        roleAuthorityService.updateById(roleAuthority);
        return success(roleAuthority);
    }

    /**
     * 删除角色的资源
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色的资源", notes = "根据id物理删除角色的资源")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        roleAuthorityService.removeById(id);
        return success(true);
    }

}
