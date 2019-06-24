package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.DictionaryItemDTO;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.Result;
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
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItem", description = "字典项")
public class DictionaryItemController extends BaseController {

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 分页查询字典项
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询字典项", notes = "分页查询字典项")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<DictionaryItem>> page(@Valid DictionaryItemDTO data) {
        IPage<DictionaryItem> page = getPage();
        // 构建查询条件
        LbqWrapper<DictionaryItem> query = Wraps.lbQ();
        dictionaryItemService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询字典项
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询字典项", notes = "查询字典项")
    @GetMapping("/{id}")
    public Result<DictionaryItem> get(@PathVariable Long id) {
        return success(dictionaryItemService.getById(id));
    }

    /**
     * 保存字典项
     *
     * @param dictionaryItem 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存字典项", notes = "保存字典项不为空的字段")
    @PostMapping
    public Result<DictionaryItem> save(@RequestBody @Valid DictionaryItem dictionaryItem) {
        dictionaryItemService.save(dictionaryItem);
        return success(dictionaryItem);
    }

    /**
     * 修改字典项
     *
     * @param dictionaryItem 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改字典项", notes = "修改字典项不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public Result<DictionaryItem> update(@RequestBody @Valid DictionaryItem dictionaryItem) {
        dictionaryItemService.updateById(dictionaryItem);
        return success(dictionaryItem);
    }

    /**
     * 删除字典项
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除字典项", notes = "根据id物理删除字典项")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {
        dictionaryItemService.removeById(id);
        return success(true);
    }

}
