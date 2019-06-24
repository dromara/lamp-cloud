package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.DownWaterDTO;
import com.github.zuihou.file.entity.DownWater;
import com.github.zuihou.file.service.DownWaterService;
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
 * 文件下载流水
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/downWater")
@Api(value = "DownWater", description = "文件下载流水")
public class DownWaterController extends BaseController {

    @Autowired
    private DownWaterService downWaterService;

    /**
     * 分页查询文件下载流水
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询文件下载流水", notes = "分页查询文件下载流水")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<DownWater>> page(@Valid DownWaterDTO data) {
        IPage<DownWater> page = getPage();
        // 构建查询条件
        LbqWrapper<DownWater> query = Wraps.lbQ();
        downWaterService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询文件下载流水
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询文件下载流水", notes = "查询文件下载流水")
    @GetMapping("/{id}")
    public Result<DownWater> get(@PathVariable Long id) {
        return success(downWaterService.getById(id));
    }

    /**
     * 保存文件下载流水
     *
     * @param downWater 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存文件下载流水", notes = "保存文件下载流水不为空的字段")
    @PostMapping
    public Result<DownWater> save(@RequestBody @Valid DownWater downWater) {
        downWaterService.save(downWater);
        return success(downWater);
    }

    /**
     * 修改文件下载流水
     *
     * @param downWater 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改文件下载流水", notes = "修改文件下载流水不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<DownWater> update(@RequestBody @Valid DownWater downWater) {
        downWaterService.updateById(downWater);
        return success(downWater);
    }

    /**
     * 删除文件下载流水
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除文件下载流水", notes = "根据id物理删除文件下载流水")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        downWaterService.removeById(id);
        return success(true);
    }

}
