package com.github.zuihou.authority.controller.core;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.core.OrgDTO;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
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
 *
 * </p>
 *
 * @author zuihou
 * @date 2019-06-29
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/org")
@Api(value = "Org", description = "")
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 分页查询
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Org>> page(@Valid OrgDTO data) {
        IPage<Org> page = getPage();
        // 构建查询条件
        LbqWrapper<Org> query = Wraps.lbQ();
        orgService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询", notes = "查询")
    @GetMapping("/{id}")
    public R<Org> get(@PathVariable Long id) {
        return success(orgService.getById(id));
    }

    /**
     * 保存
     *
     * @param org 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @PostMapping
    public R<Org> save(@RequestBody @Valid Org org) {
        orgService.save(org);
        return success(org);
    }

    /**
     * 修改
     *
     * @param org 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Org> update(@RequestBody @Valid Org org) {
        orgService.updateById(org);
        return success(org);
    }

    /**
     * 删除
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "根据id物理删除")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        orgService.removeById(id);
        return success(true);
    }

}
