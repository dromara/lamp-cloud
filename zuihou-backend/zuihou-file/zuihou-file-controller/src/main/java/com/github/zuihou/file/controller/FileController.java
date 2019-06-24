package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.FileDTO;
import com.github.zuihou.file.entity.File;
import com.github.zuihou.file.service.FileService;
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
 * 文件表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/file")
@Api(value = "File", description = "文件表")
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    /**
     * 分页查询文件表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询文件表", notes = "分页查询文件表")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<File>> page(@Valid FileDTO data) {
        IPage<File> page = getPage();
        // 构建查询条件
        LbqWrapper<File> query = Wraps.lbQ();
        fileService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询文件表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询文件表", notes = "查询文件表")
    @GetMapping("/{id}")
    public Result<File> get(@PathVariable Long id) {
        return success(fileService.getById(id));
    }

    /**
     * 保存文件表
     *
     * @param file 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存文件表", notes = "保存文件表不为空的字段")
    @PostMapping
    public Result<File> save(@RequestBody @Valid File file) {
        fileService.save(file);
        return success(file);
    }

    /**
     * 修改文件表
     *
     * @param file 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改文件表", notes = "修改文件表不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<File> update(@RequestBody @Valid File file) {
        fileService.updateById(file);
        return success(file);
    }

    /**
     * 删除文件表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除文件表", notes = "根据id物理删除文件表")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        fileService.removeById(id);
        return success(true);
    }

}
