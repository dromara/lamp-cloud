package com.github.zuihou.authority.controller.auth;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.ApplicationDTO;
import com.github.zuihou.authority.entity.auth.Application;
import com.github.zuihou.authority.service.auth.ApplicationService;
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
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", description = "应用")
public class ApplicationController extends BaseController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 分页查询应用
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询应用", notes = "分页查询应用")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Application>> page(@Valid ApplicationDTO data) {
        IPage<Application> page = getPage();
        // 构建查询条件
        LbqWrapper<Application> query = Wraps.lbQ();
        applicationService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询应用
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询应用", notes = "查询应用")
    @GetMapping("/{id}")
    public R<Application> get(@PathVariable Long id) {
        return success(applicationService.getById(id));
    }

    /**
     * 保存应用
     *
     * @param application 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存应用", notes = "保存应用不为空的字段")
    @PostMapping
    public R<Application> save(@RequestBody @Valid Application application) {
        applicationService.save(application);
        return success(application);
    }

    /**
     * 修改应用
     *
     * @param application 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改应用", notes = "修改应用不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Application> update(@RequestBody @Valid Application application) {
        applicationService.updateById(application);
        return success(application);
    }

    /**
     * 删除应用
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除应用", notes = "根据id物理删除应用")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        applicationService.removeById(id);
        return success(true);
    }

}
