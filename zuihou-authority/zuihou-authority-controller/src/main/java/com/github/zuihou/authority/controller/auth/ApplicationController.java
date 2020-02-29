package com.github.zuihou.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.auth.ApplicationSaveDTO;
import com.github.zuihou.authority.dto.auth.ApplicationUpdateDTO;
import com.github.zuihou.authority.entity.auth.Application;
import com.github.zuihou.authority.service.auth.ApplicationService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询应用")
    public R<IPage<Application>> page(Application data) {
        IPage<Application> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Application> query = Wraps.lbQ(data);
        query.orderByDesc(Application::getId);
        applicationService.page(page, query);
        return success(page);
    }

    /**
     * 查询应用
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询应用", notes = "查询应用")
    @GetMapping("/{id}")
    @SysLog("查询应用")
    public R<Application> get(@PathVariable Long id) {
        return success(applicationService.getById(id));
    }

    /**
     * 新增应用
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增应用", notes = "新增应用不为空的字段")
    @PostMapping
    @SysLog("新增应用")
    public R<Application> save(@RequestBody @Validated ApplicationSaveDTO data) {
        Application application = BeanPlusUtil.toBean(data, Application.class);
        application.setAppKey(RandomUtil.randomString(24));
        application.setAppSecret(RandomUtil.randomString(32));
        applicationService.save(application);
        return success(application);
    }

    /**
     * 修改应用
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改应用", notes = "修改应用不为空的字段")
    @PutMapping
    @SysLog("修改应用")
    public R<Application> update(@RequestBody @Validated(SuperEntity.Update.class) ApplicationUpdateDTO data) {
        Application application = BeanPlusUtil.toBean(data, Application.class);
        applicationService.updateById(application);
        return success(application);
    }

    /**
     * 删除应用
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除应用", notes = "根据id物理删除应用")
    @DeleteMapping
    @SysLog("删除应用")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        applicationService.removeByIds(ids);
        return success(true);
    }

}
