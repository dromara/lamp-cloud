package com.github.zuihou.authority.controller.core;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.core.OrgSaveDTO;
import com.github.zuihou.authority.dto.core.OrgUpdateDTO;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;

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
 * @date 2019-07-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/org")
@Api(value = "Org", description = "")
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    @SysLog("分页查询")
    public R<IPage<Org>> page(@Valid Org data) {
        IPage<Org> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Org> query = Wraps.lbQ(data);
        orgService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询", notes = "单体查询")
    @GetMapping("/{id}")
    @SysLog("单体查询")
    public R<Org> get(@PathVariable Long id) {
        return success(orgService.getById(id));
    }

    /**
     * 保存
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存", notes = "保存不为空的字段")
    @PostMapping
    @SysLog("保存")
    public R<Org> save(@RequestBody @Valid OrgSaveDTO data) {
        Org org = dozer.map(data, Org.class);
        orgService.save(org);
        return success(org);
    }

    /**
     * 修改
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改", notes = "修改不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改")
    public R<Org> update(@RequestBody @Valid OrgUpdateDTO data) {
        Org org = dozer.map(data, Org.class);
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
    @SysLog("删除")
    public R<Boolean> delete(@PathVariable Long id) {
        orgService.removeById(id);
        return success(true);
    }

}
