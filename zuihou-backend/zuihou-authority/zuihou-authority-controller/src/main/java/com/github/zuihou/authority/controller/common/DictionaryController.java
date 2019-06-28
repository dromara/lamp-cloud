package com.github.zuihou.authority.controller.common;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.DictionaryDTO;
import com.github.zuihou.authority.entity.common.Dictionary;
import com.github.zuihou.authority.service.common.DictionaryService;
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
 * 字典目录
 * </p>
 *
 * @author zuihou
 * @date 2019-06-24
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionary")
@Api(value = "Dictionary", description = "字典目录")
public class DictionaryController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 分页查询字典目录
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询字典目录", notes = "分页查询字典目录")
    @GetMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public R<IPage<Dictionary>> page(@Valid DictionaryDTO data) {
        IPage<Dictionary> page = getPage();
        // 构建查询条件
        LbqWrapper<Dictionary> query = Wraps.lbQ();
        dictionaryService.page(page, query);
        return success(page);
    }

    /**
     * 单体查询字典目录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询字典目录", notes = "查询字典目录")
    @GetMapping("/{id}")
    public R<Dictionary> get(@PathVariable Long id) {
        return success(dictionaryService.getById(id));
    }

    /**
     * 保存字典目录
     *
     * @param dictionary 保存对象
     * @return 保存结果
     */
    @ApiOperation(value = "保存字典目录", notes = "保存字典目录不为空的字段")
    @PostMapping
    public R<Dictionary> save(@RequestBody @Valid Dictionary dictionary) {
        dictionaryService.save(dictionary);
        return success(dictionary);
    }

    /**
     * 修改字典目录
     *
     * @param dictionary 修改对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改字典目录", notes = "修改字典目录不为空的字段")
    @PutMapping
    @Validated(SuperEntity.Update.class)
    public R<Dictionary> update(@RequestBody @Valid Dictionary dictionary) {
        dictionaryService.updateById(dictionary);
        return success(dictionary);
    }

    /**
     * 删除字典目录
     *
     * @param id 主键id
     * @return 删除结果
     */
    @ApiOperation(value = "删除字典目录", notes = "根据id物理删除字典目录")
    @DeleteMapping(value = "/{id}")
    public R<Boolean> delete(@PathVariable Long id) {
        dictionaryService.removeById(id);
        return success(true);
    }

}
