package com.github.zuihou.authority.controller.common;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zuihou.authority.dto.common.DictionaryItemSaveDTO;
import com.github.zuihou.authority.dto.common.DictionaryItemUpdateDTO;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.base.BaseController;
import com.github.zuihou.base.R;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 前端控制器
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItem", tags = "字典项")
public class DictionaryItemController extends BaseController {

    @Autowired
    private DictionaryItemService dictionaryItemService;
    @Autowired
    private DozerUtils dozer;

    /**
     * 根据字典编码查询字典条目的map集合
     * <p>
     * 将 List 转成 Map<String, Map<String, String>>
     * key 是字典编码  value 是字典编码下的所有 字典条目。  字典条目的 key是条目编码， value是条目名称
     * <p>
     * eg:
     * <p>
     * list:
     * <pre>
     * dictionaryCode   dictionaryItemCode  name
     * DIC_ZGXL         COLLEGE             本科
     * DIC_ZGXL         BOSHI               博士
     * ONLING_STATUS    WORKING             在职
     * ONLING_STATUS    LEAVE               离职
     * </pre>
     * <p>
     * 转成map：
     * {
     * "DIC_ZGXL": {
     * "COLLEGE", "本科",
     * "BOSHI", "博士",
     * }
     * "ONLING_STATUS": {
     * "WORKING", "在职",
     * "LEAVE", "离职",
     * }
     * }
     *
     * </p>
     *
     * @param codes 字典类型
     * @return 查询结果
     */
    @ApiOperation(value = "根据字典编码查询字典条目的map集合", notes = "根据字典编码查询字典条目的map集合")
    @GetMapping("/codes")
    public R<Map<String, Map<String, String>>> map(@RequestParam("codes") String[] codes) {
        LbqWrapper<DictionaryItem> query = Wraps.<DictionaryItem>lbQ()
                .in(DictionaryItem::getDictionaryCode, codes)
                .eq(DictionaryItem::getIsDelete, false)
                .eq(DictionaryItem::getIsEnable, true)
                .orderByAsc(DictionaryItem::getSortValue);
        List<DictionaryItem> list = dictionaryItemService.list(query);

        //key 是字典编码
        Map<String, List<DictionaryItem>> typeMap = list.stream().collect(groupingBy(DictionaryItem::getDictionaryCode, LinkedHashMap::new, toList()));

        //需要返回的map
        Map<String, Map<String, String>> typeCodeNameMap = new LinkedHashMap<>(typeMap.size());

        typeMap.forEach((key, items) -> {
            ImmutableMap<String, String> itemCodeMap = MapHelper.uniqueIndex(items, DictionaryItem::getCode, DictionaryItem::getName);
            typeCodeNameMap.put(key, itemCodeMap);
        });
        return success(typeCodeNameMap);
    }

    /**
     * 分页查询字典项
     *
     * @param data 分页查询对象
     * @return 查询结果
     */
    @ApiOperation(value = "分页查询字典项", notes = "分页查询字典项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "long", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "分页条数", dataType = "long", paramType = "query", defaultValue = "10"),
    })
    @GetMapping("/page")
    @SysLog("分页查询字典项")
    public R<IPage<DictionaryItem>> page(DictionaryItem data) {
        IPage<DictionaryItem> page = getPage();
        // 构建值不为null的查询条件
        LbqWrapper<DictionaryItem> query = Wraps.lbQ(data)
                //忽略lbQ 默认的like拼接， 然后将DictionaryCode改成 = 查询
                .ignore(DictionaryItem::setDictionaryCode)
                .eq(DictionaryItem::getDictionaryCode, data.getDictionaryCode())
                .eq(DictionaryItem::getIsDelete, false);
        dictionaryItemService.page(page, query);
        return success(page);
    }

    /**
     * 查询字典项
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiOperation(value = "查询字典项", notes = "查询字典项")
    @GetMapping("/{id}")
    @SysLog("查询字典项")
    public R<DictionaryItem> get(@PathVariable Long id) {
        return success(dictionaryItemService.getById(id));
    }

    /**
     * 新增字典项
     *
     * @param data 新增对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增字典项", notes = "新增字典项不为空的字段")
    @PostMapping
    @SysLog("新增字典项")
    public R<DictionaryItem> save(@RequestBody @Validated DictionaryItemSaveDTO data) {
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
    @SysLog("修改字典项")
    public R<DictionaryItem> update(@RequestBody @Validated(SuperEntity.Update.class) DictionaryItemUpdateDTO data) {
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
