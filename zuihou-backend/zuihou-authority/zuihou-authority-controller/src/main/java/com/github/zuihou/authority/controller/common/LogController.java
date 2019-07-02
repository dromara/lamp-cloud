package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.LogDTO;
import com.github.zuihou.authority.entity.common.Log;
import com.github.zuihou.authority.service.common.LogService;
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
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/log")
@Api(value = "Log", description = "系统日志")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询系统日志
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询系统日志", notes = "分页查询系统日志")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Log>> page(@Valid LogDTO data) {
        IPage<Log> page = getPage();
        // 构建查询条件
        LbqWrapper<Log> query = Wraps.lbQ();
        logService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询系统日志
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询系统日志", notes = "查询系统日志")
    @GetMapping("/{id}")
    public R<Log> get(@PathVariable Long id) {
        return success(logService.getById(id));
    }

    /**
     * 保存系统日志
     *
     * @param log 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存系统日志", notes = "保存系统日志不为空的字段")
    @PostMapping
    public R<Log> save(@RequestBody @Valid Log log) {
        logService.save(log);
        return success(log);
    }

    /**
     * 修改系统日志
     *
     * @param log 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改系统日志", notes = "修改系统日志不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Log> update(@RequestBody @Valid Log log) {
        logService.updateById(log);
        return success(log);
    }

    /**
     * 删除系统日志
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除系统日志", notes = "根据id物理删除系统日志")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        logService.removeById(id);
        return success(true);
    }

}
