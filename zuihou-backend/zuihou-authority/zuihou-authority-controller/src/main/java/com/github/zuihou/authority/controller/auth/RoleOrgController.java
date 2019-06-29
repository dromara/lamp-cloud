package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.RoleOrgDTO;
import com.github.zuihou.authority.entity.auth.RoleOrg;
import com.github.zuihou.authority.service.auth.RoleOrgService;
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
 * 角色部门关系
 * </p>
 *
 * @author zuihou
 * @date 2019-06-29
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/roleOrg")
@Api(value = "RoleOrg", description = "角色部门关系")
public class RoleOrgController extends BaseController {

    @Autowired
    private RoleOrgService roleOrgService;

    /**
     * 分页查询角色部门关系
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询角色部门关系", notes = "分页查询角色部门关系")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<RoleOrg>> page(@Valid RoleOrgDTO data) {
        IPage<RoleOrg> page = getPage();
        // 构建查询条件
        LbqWrapper<RoleOrg> query = Wraps.lbQ();
        roleOrgService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询角色部门关系
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询角色部门关系", notes = "查询角色部门关系")
    @GetMapping("/{id}")
    public R<RoleOrg> get(@PathVariable Long id) {
        return success(roleOrgService.getById(id));
    }

    /**
     * 保存角色部门关系
     *
     * @param roleOrg 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存角色部门关系", notes = "保存角色部门关系不为空的字段")
    @PostMapping
    public R<RoleOrg> save(@RequestBody @Valid RoleOrg roleOrg) {
        roleOrgService.save(roleOrg);
        return success(roleOrg);
    }

    /**
     * 修改角色部门关系
     *
     * @param roleOrg 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改角色部门关系", notes = "修改角色部门关系不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<RoleOrg> update(@RequestBody @Valid RoleOrg roleOrg) {
        roleOrgService.updateById(roleOrg);
        return success(roleOrg);
    }

    /**
     * 删除角色部门关系
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除角色部门关系", notes = "根据id物理删除角色部门关系")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        roleOrgService.removeById(id);
        return success(true);
    }

}
