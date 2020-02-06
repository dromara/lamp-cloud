package com.github.zuihou.authority.controller.common;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.ParameterPageDTO;
import com.github.zuihou.authority.dto.common.ParameterSaveDTO;
import com.github.zuihou.authority.dto.common.ParameterUpdateDTO;
import com.github.zuihou.authority.entity.common.Parameter;
import com.github.zuihou.authority.service.common.ParameterService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
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
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
public class ParameterController extends BaseController {

    @Autowired
    private ParameterService parameterService;

    /**
     * 分页查询参数配置
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询参数配置", notes = "分页查询参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询参数配置")
    public R<IPage<Parameter>> page(ParameterPageDTO data) {
        Parameter parameter = BeanUtil.toBean(data, Parameter.class);

        IPage<Parameter> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Parameter> query = Wraps.<Parameter>lbQ(parameter)
                .geHeader(Parameter::getCreateTime, data.getStartCreateTime())
                .leFooter(Parameter::getCreateTime, data.getEndCreateTime())
                .orderByDesc(Parameter::getCreateTime);
        parameterService.page(page, query);
        return success(page);
    }

    /**
     * 查询参数配置
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询参数配置", notes = "查询参数配置")
    @GetMapping("/{id}")
    @SysLog("查询参数配置")
    public R<Parameter> get(@PathVariable Long id) {
        return success(parameterService.getById(id));
    }

    /**
     * 新增参数配置
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增参数配置", notes = "新增参数配置不为空的字段")
    @PostMapping
    @SysLog("新增参数配置")
    public R<Parameter> save(@RequestBody @Validated ParameterSaveDTO data) {
        Parameter parameter = BeanUtil.toBean(data, Parameter.class);
        parameterService.save(parameter);
        return success(parameter);
    }

    /**
     * 修改参数配置
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改参数配置", notes = "修改参数配置不为空的字段")
    @PutMapping
    @SysLog("修改参数配置")
    public R<Parameter> update(@RequestBody @Validated(SuperEntity.Update.class) ParameterUpdateDTO data) {
        Parameter parameter = BeanUtil.toBean(data, Parameter.class);
        parameterService.updateById(parameter);
        return success(parameter);
    }

    /**
     * 删除参数配置
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除参数配置", notes = "根据id物理删除参数配置")
    @DeleteMapping
    @SysLog("删除参数配置")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        parameterService.removeByIds(ids);
        return success(true);
    }

}
