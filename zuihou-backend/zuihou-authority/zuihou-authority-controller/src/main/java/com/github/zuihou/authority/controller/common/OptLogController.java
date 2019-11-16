package com.github.zuihou.authority.controller.common;


import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.log.entity.OptLogDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "系统日志")
public class OptLogController extends BaseController {

    @Autowired
    private OptLogService optLogService;

    /**
     * 分页查询系统日志
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @ApiOperation(value = "分页查询系统日志", notes = "分页查询系统日志")
    @GetMapping("/page")
    public R<IPage<OptLog>> page(OptLog data) {
        IPage<OptLog> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<OptLog> query = Wraps.lbQ(data)
                .leFooter(OptLog::getCreateTime, getEndCreateTime())
                .geHeader(OptLog::getCreateTime, getStartCreateTime())
                .orderByDesc(OptLog::getId);
        optLogService.page(page, query);
        return success(page);
    }

    /**
     * 查询系统日志
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询系统日志", notes = "查询系统日志")
    @GetMapping("/{id}")
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
    public R<OptLogDTO> save(@RequestBody OptLogDTO data) {
        optLogService.save(data);
        return success(data);
    }

    /**
     * 删除系统日志
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除系统日志", notes = "根据id物理删除系统日志")
    @DeleteMapping
    @SysLog("删除系统日志")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        optLogService.removeByIds(ids);
        return success(true);
    }

}
