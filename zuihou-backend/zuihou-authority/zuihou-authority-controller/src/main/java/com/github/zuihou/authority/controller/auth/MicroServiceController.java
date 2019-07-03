package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.MicroServiceSaveDTO;
import com.github.zuihou.authority.dto.auth.MicroServiceUpdateDTO;
import com.github.zuihou.authority.entity.auth.MicroService;
import com.github.zuihou.authority.service.auth.MicroServiceService;
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
 * 服务表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/microService")
@Api(value = "MicroService", description = "服务表")
public class MicroServiceController extends BaseController {

    @Autowired
    private MicroServiceService microServiceService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询服务表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询服务表", notes = "分页查询服务表")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    @SysLog("分页查询服务表")
    public R<IPage<MicroService>> page(@Valid MicroService data) {
        IPage<MicroService> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<MicroService> query = Wraps.lbQ(data);
        microServiceService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询服务表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询服务表", notes = "单体查询服务表")
    @GetMapping("/{id}")
    @SysLog("单体查询服务表")
    public R<MicroService> get(@PathVariable Long id) {
        return success(microServiceService.getById(id));
    }

    /**
     * 保存服务表
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存服务表", notes = "保存服务表不为空的字段")
    @PostMapping
    @SysLog("保存服务表")
    public R<MicroService> save(@RequestBody @Valid MicroServiceSaveDTO data) {
        MicroService microService = dozer.map(data, MicroService.class);
        microServiceService.save(microService);
        return success(microService);
    }

    /**
     * 修改服务表
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改服务表", notes = "修改服务表不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改服务表")
    public R<MicroService> update(@RequestBody @Valid MicroServiceUpdateDTO data) {
        MicroService microService = dozer.map(data, MicroService.class);
        microServiceService.updateById(microService);
        return success(microService);
    }

    /**
     * 删除服务表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除服务表", notes = "根据id物理删除服务表")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除服务表")
    public R<Boolean> delete(@PathVariable Long id) {
        microServiceService.removeById(id);
        return success(true);
    }

}
