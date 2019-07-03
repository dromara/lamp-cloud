package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.OptLogSaveDTO;
import com.github.zuihou.authority.dto.common.OptLogUpdateDTO;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.service.common.OptLogService;
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
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", description = "系统日志")
public class OptLogController extends BaseController {

    @Autowired
    private OptLogService optLogService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询系统日志
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询系统日志", notes = "分页查询系统日志")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    @SysLog("分页查询系统日志")
    public R<IPage<OptLog>> page(@Valid OptLog data) {
        IPage<OptLog> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<OptLog> query = Wraps.lbQ(data);
        optLogService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询系统日志
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询系统日志", notes = "单体查询系统日志")
    @GetMapping("/{id}")
    @SysLog("单体查询系统日志")
    public R<OptLog> get(@PathVariable Long id) {
        return success(optLogService.getById(id));
    }

    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存系统日志", notes = "保存系统日志不为空的字段")
    @PostMapping
    @SysLog("保存系统日志")
    public R<OptLog> save(@RequestBody @Valid OptLogSaveDTO data) {
        OptLog optLog = dozer.map(data, OptLog.class);
        optLogService.save(optLog);
        return success(optLog);
    }

    /**
     * 修改系统日志
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改系统日志", notes = "修改系统日志不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改系统日志")
    public R<OptLog> update(@RequestBody @Valid OptLogUpdateDTO data) {
        OptLog optLog = dozer.map(data, OptLog.class);
        optLogService.updateById(optLog);
        return success(optLog);
    }

    /**
     * 删除系统日志
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除系统日志", notes = "根据id物理删除系统日志")
    @DeleteMapping(value = "/{id}")
    @SysLog("删除系统日志")
    public R<Boolean> delete(@PathVariable Long id) {
        optLogService.removeById(id);
        return success(true);
    }

}
