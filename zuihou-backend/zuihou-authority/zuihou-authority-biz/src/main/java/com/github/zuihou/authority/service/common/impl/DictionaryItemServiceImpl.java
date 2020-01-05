package com.github.zuihou.authority.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.DictionaryItemMapper;
import com.github.zuihou.authority.entity.common.DictionaryItem;
import com.github.zuihou.authority.service.common.DictionaryItemService;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemMapper, DictionaryItem> implements DictionaryItemService {
    @Override
    public Map<String, Map<String, String>> map(String[] codes) {
        LbqWrapper<DictionaryItem> query = Wraps.<DictionaryItem>lbQ()
                .in(DictionaryItem::getDictionaryCode, codes)
                .eq(DictionaryItem::getStatus, true)
                .orderByAsc(DictionaryItem::getSortValue);
        List<DictionaryItem> list = super.list(query);

        //key 是字典编码
        Map<String, List<DictionaryItem>> typeMap = list.stream().collect(groupingBy(DictionaryItem::getDictionaryCode, LinkedHashMap::new, toList()));

        //需要返回的map
        Map<String, Map<String, String>> typeCodeNameMap = new LinkedHashMap<>(typeMap.size());

        typeMap.forEach((key, items) -> {
            ImmutableMap<String, String> itemCodeMap = MapHelper.uniqueIndex(items, DictionaryItem::getCode, DictionaryItem::getName);
            typeCodeNameMap.put(key, itemCodeMap);
        });
        return typeCodeNameMap;
    }
}
