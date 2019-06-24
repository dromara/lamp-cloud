package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.RecycleDTO;
import com.github.zuihou.file.entity.Recycle;
import com.github.zuihou.file.service.RecycleService;
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
 * 文件回收站
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/recycle")
@Api(value = "Recycle", description = "文件回收站")
public class RecycleController extends BaseController {

    @Autowired
    private RecycleService recycleService;

    /**
     * 分页查询文件回收站
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询文件回收站", notes = "分页查询文件回收站")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<Recycle>> page(@Valid RecycleDTO data) {
        IPage<Recycle> page = getPage();
        // 构建查询条件
        LbqWrapper<Recycle> query = Wraps.lbQ();
        recycleService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询文件回收站
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询文件回收站", notes = "查询文件回收站")
    @GetMapping("/{id}")
    public Result<Recycle> get(@PathVariable Long id) {
        return success(recycleService.getById(id));
    }

    /**
     * 保存文件回收站
     *
     * @param recycle 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存文件回收站", notes = "保存文件回收站不为空的字段")
    @PostMapping
    public Result<Recycle> save(@RequestBody @Valid Recycle recycle) {
        recycleService.save(recycle);
        return success(recycle);
    }

    /**
     * 修改文件回收站
     *
     * @param recycle 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改文件回收站", notes = "修改文件回收站不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<Recycle> update(@RequestBody @Valid Recycle recycle) {
        recycleService.updateById(recycle);
        return success(recycle);
    }

    /**
     * 删除文件回收站
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除文件回收站", notes = "根据id物理删除文件回收站")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        recycleService.removeById(id);
        return success(true);
    }

}
