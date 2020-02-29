package com.github.zuihou.authority.controller.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.DictionarySaveDTO;
import com.github.zuihou.authority.dto.common.DictionaryUpdateDTO;
import com.github.zuihou.authority.entity.common.Dictionary;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.authority.service.common.DictionaryService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.BeanPlusUtil;
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
 * 字典目录
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionary")
@Api(value = "Dictionary", tags = "字典目录")
public class DictionaryController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 分页查询字典目录
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询字典目录", notes = "分页查询字典目录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页显示几条", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询字典目录")
    public R<IPage<Dictionary>> page(Dictionary data) {
        IPage<Dictionary> page = this.getPage();
        // 构建值不为null的查询条件
        LbqWrapper<Dictionary> query = Wraps.lbQ(data);
        this.dictionaryService.page(page, query);
        return this.success(page);
    }

    /**
     * 查询字典目录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询字典目录", notes = "查询字典目录")
    @GetMapping("/{id}")
    @SysLog("查询字典目录")
    public R<Dictionary> get(@PathVariable Long id) {
        return this.success(this.dictionaryService.getById(id));
    }

    /**
     * 新增字典目录
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增字典目录", notes = "新增字典目录不为空的字段")
    @PostMapping
    @SysLog("新增字典目录")
    public R<Dictionary> save(@RequestBody @Validated DictionarySaveDTO data) {
        Dictionary dictionary = BeanPlusUtil.toBean(data, Dictionary.class);
        this.dictionaryService.save(dictionary);
        return this.success(dictionary);
    }

    /**
     * 修改字典目录
     *
     * @param data 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改字典目录", notes = "修改字典目录不为空的字段")
    @PutMapping
    @SysLog("修改字典目录")
    public R<Dictionary> update(@RequestBody @Validated(SuperEntity.Update.class) DictionaryUpdateDTO data) {
        Dictionary dictionary = BeanPlusUtil.toBean(data, Dictionary.class);
        this.dictionaryService.updateById(dictionary);
        return this.success(dictionary);
    }

    /**
     * 删除字典目录
     *
     * @param ids 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除字典目录", notes = "根据id物理删除字典目录")
    @DeleteMapping
    @SysLog("删除字典目录")
    public R<Boolean> delete(@RequestParam("ids[]") List<Long> ids) {
        this.dictionaryService.removeByIds(ids);
        this.dictionaryItemService.remove(Wraps.<DictionaryItem>lbQ().in(DictionaryItem::getDictionaryId, ids));
        return this.success(true);
    }


}
