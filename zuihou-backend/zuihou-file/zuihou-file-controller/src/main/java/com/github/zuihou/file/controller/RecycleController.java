package com.github.zuihou.file.controller;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.file.entity.Recycle;
import com.github.zuihou.file.service.RecycleService;
import com.github.zuihou.mybatis.conditions.query.LbqWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文件回收站 前端控制器
 * </p>
 *
 * @author luosh
 * @since 2019-05-06
 */
@Slf4j
@RestController
@RequestMapping("/recycle")
@Api(value = "Recycle", description = "文件回收站")
public class RecycleController extends BaseController {

    @Autowired
    private RecycleService recycleService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Recycle>> page(@Valid Recycle recycle) {
        IPage<Recycle> page = getPage();
        LbqWrapper<Recycle> query = LbqWrapper.lambdaQuery();
        query.eq(Recycle::getCreateUser, getUserId())
                .eq(Recycle::getFolderId, recycle.getFolderId())
                .eq(Recycle::getDataType, recycle.getDataType())
                .like(Recycle::getSubmittedFileName, recycle.getSubmittedFileName())
                .orderByDesc(Recycle::getUpdateTime);
        recycleService.page(page, query);
        return success(page);
    }

    @ApiOperation(value = "单体查询", notes = "单体查询")
    @GetMapping("/{id}")
    public R<Recycle> get(@PathVariable Long id) {
        return success(recycleService.getById(id));
    }

    @ApiOperation(value = "删除回收站数据", notes = "批量删除回收站数据")
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public R<Boolean> remove(@RequestParam(value = "ids[]") Long[] ids) {
        recycleService.deleteBatch(getUserId(), ids);
        return success(true);
    }

    @ApiOperation(value = "还原", notes = "还原回收站数据")
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public R<Boolean> reset(@RequestParam(value = "ids[]") Long[] ids) {
        recycleService.reset(getUserId(), ids);
        return success(true);
    }

    @ApiOperation(value = "清空回收站", notes = "清空回收站")
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public R<Boolean> clear() {
        recycleService.clear(getUserId());
        return success(true);
    }
}
