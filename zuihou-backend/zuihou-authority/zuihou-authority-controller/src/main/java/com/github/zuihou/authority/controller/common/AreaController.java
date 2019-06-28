package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.AreaDTO;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.authority.service.common.AreaService;
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
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/area")
@Api(value = "Area", description = "地区表")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    /**
     * 分页查询地区表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询地区表", notes = "分页查询地区表")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Area>> page(@Valid AreaDTO data) {
        IPage<Area> page = getPage();
        // 构建查询条件
        LbqWrapper<Area> query = Wraps.lbQ();
        areaService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询地区表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询地区表", notes = "查询地区表")
    @GetMapping("/{id}")
    public R<Area> get(@PathVariable Long id) {
        return success(areaService.getById(id));
    }

    /**
     * 保存地区表
     *
     * @param area 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存地区表", notes = "保存地区表不为空的字段")
    @PostMapping
    public R<Area> save(@RequestBody @Valid Area area) {
        areaService.save(area);
        return success(area);
    }

    /**
     * 修改地区表
     *
     * @param area 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改地区表", notes = "修改地区表不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Area> update(@RequestBody @Valid Area area) {
        areaService.updateById(area);
        return success(area);
    }

    /**
     * 删除地区表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除地区表", notes = "根据id物理删除地区表")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        areaService.removeById(id);
        return success(true);
    }

}
