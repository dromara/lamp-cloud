package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.dto.ShareDTO;
import com.github.zuihou.file.entity.Share;
import com.github.zuihou.file.service.ShareService;
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
 * 分享目录表
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/share")
@Api(value = "Share", description = "分享目录表")
public class ShareController extends BaseController {

    @Autowired
    private ShareService shareService;

    /**
     * 分页查询分享目录表
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询分享目录表", notes = "分页查询分享目录表")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<Share>> page(@Valid ShareDTO data) {
        IPage<Share> page = getPage();
        // 构建查询条件
        LbqWrapper<Share> query = Wraps.lbQ();
        shareService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询分享目录表
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询分享目录表", notes = "查询分享目录表")
    @GetMapping("/{id}")
    public Result<Share> get(@PathVariable Long id) {
        return success(shareService.getById(id));
    }

    /**
     * 保存分享目录表
     *
     * @param share 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存分享目录表", notes = "保存分享目录表不为空的字段")
    @PostMapping
    public Result<Share> save(@RequestBody @Valid Share share) {
        shareService.save(share);
        return success(share);
    }

    /**
     * 修改分享目录表
     *
     * @param share 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改分享目录表", notes = "修改分享目录表不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<Share> update(@RequestBody @Valid Share share) {
        shareService.updateById(share);
        return success(share);
    }

    /**
     * 删除分享目录表
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除分享目录表", notes = "根据id物理删除分享目录表")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        shareService.removeById(id);
        return success(true);
    }

}
