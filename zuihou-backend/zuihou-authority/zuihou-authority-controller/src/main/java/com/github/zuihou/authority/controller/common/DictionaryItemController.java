package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.base.R;
import com.github.zuihou.common.utils.context.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.dto.common.DictionaryItemSaveDTO;
import com.github.zuihou.authority.dto.common.DictionaryItemUpdateDTO;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.github.zuihou.base.entity.SuperEntity;
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
import com.github.zuihou.base.BaseController;

/**
 * <p>
 * 前端控制器
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItem", description = "字典项")
public class DictionaryItemController extends BaseController {

    @Autowired
    private DictionaryItemService dictionaryItemService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 分页查询字典项
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询字典项", notes = "分页查询字典项")
    @GetMapping("/page")
    @SysLog("分页查询字典项")
    public R<IPage<DictionaryItem>> page(DictionaryItem data) {
        IPage<DictionaryItem> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<DictionaryItem> query = Wraps.lbQ(data);
        dictionaryItemService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询字典项
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "单体查询字典项", notes = "单体查询字典项")
    @GetMapping("/{id}")
    @SysLog("单体查询字典项")
    public R<DictionaryItem> get(@PathVariable Long id) {
        return success(dictionaryItemService.getById(id));
    }

    /**
     * 保存字典项
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存字典项", notes = "保存字典项不为空的字段")
    @PostMapping
    @SysLog("保存字典项")
    public R<DictionaryItem> save(@RequestBody @Valid DictionaryItemSaveDTO data) {
        DictionaryItem dictionaryItem = dozer.map(data, DictionaryItem.class);
        dictionaryItemService.save(dictionaryItem);
        return success(dictionaryItem);
    }

    /**
     * 修改字典项
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改字典项", notes = "修改字典项不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    @SysLog("修改字典项")
    public R<DictionaryItem> update(@RequestBody @Valid DictionaryItemUpdateDTO data) {
        DictionaryItem dictionaryItem = dozer.map(data, DictionaryItem.class);
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
    @SysLog("删除字典项")
    public R<Boolean> delete(@PathVariable Long id) {
        dictionaryItemService.removeById(id);
        return success(true);
    }

}
