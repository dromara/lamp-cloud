package com.github.zuihou.authority.controller.common;


import com.github.zuihou.authority.dto.common.DictionaryItemSaveDTO;
import com.github.zuihou.authority.dto.common.DictionaryItemUpdateDTO;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperCacheController;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.database.mybatis.conditions.query.QueryWrap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

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
public class DictionaryItemController extends SuperCacheController<DictionaryItemService, Long, DictionaryItem, DictionaryItem, DictionaryItemSaveDTO, DictionaryItemUpdateDTO> {
    @Override
    public void handlerWrapper(QueryWrap<DictionaryItem> wrapper, PageParams<DictionaryItem> params) {
        super.handlerWrapper(wrapper, params);
        DictionaryItem model = params.getModel();
        wrapper.lambda().ignore(DictionaryItem::setDictionaryType)
                .eq(DictionaryItem::getDictionaryType, model.getDictionaryType());
    }

    /**
     * 由于类型本身就是String 类型，所以不会出现mysql 的隐式转换问题，所以无需转换
     *
     * <p>
     * 接口和实现类的类型不一致，但也能调用，归功于 SpingBoot 的自动转换功能
     * {@link com.github.zuihou.authority.api.DictionaryItemApi#findDictionaryItem} 方法的实现类
     *
     * @param codes 字典项编码
     * @return
     */
    @ApiOperation(value = "查询字典项", notes = "根据id查询字典项")
    @GetMapping("/findDictionaryItem")
    public Map<Serializable, Object> findDictionaryItem(@RequestParam Set<Serializable> codes) {
        return this.baseService.findDictionaryItem(codes);
    }

    @ApiOperation(value = "根据类型查询字典条目", notes = "根据类型查询字典条目")
    @GetMapping("codes")
    public R<Map<String, Map<String, String>>> list(@RequestParam("codes[]") String[] codes) {
        return this.success(this.baseService.map(codes));
    }

}
