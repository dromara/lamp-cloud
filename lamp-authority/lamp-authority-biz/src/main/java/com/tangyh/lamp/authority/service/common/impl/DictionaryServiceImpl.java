package com.tangyh.lamp.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.ImmutableMap;
import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.basic.base.service.SuperCacheServiceImpl;
import com.tangyh.basic.cache.model.CacheHashKey;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.cache.repository.CachePlusOps;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.basic.exception.BizException;
import com.tangyh.basic.injection.properties.InjectionProperties;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.basic.utils.BizAssert;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.lamp.authority.dao.common.DictionaryMapper;
import com.tangyh.lamp.authority.dto.common.DictionaryPageQuery;
import com.tangyh.lamp.authority.dto.common.DictionaryTypeSaveDTO;
import com.tangyh.lamp.authority.entity.common.Dictionary;
import com.tangyh.lamp.authority.service.common.DictionaryService;
import com.tangyh.lamp.common.cache.common.DictionaryCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.DictionaryTypeCacheKeyBuilder;
import com.tangyh.lamp.common.constant.DefValConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class DictionaryServiceImpl extends SuperCacheServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    private final InjectionProperties ips;
    private final CachePlusOps cachePlusOps;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new DictionaryCacheKeyBuilder();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dictionary saveType(DictionaryTypeSaveDTO dictType) {
        int typeCount = count(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, dictType.getType()));
        if (typeCount > 0) {
            throw BizException.validFail("字典类型[%s]已存在", dictType.getType());
        }
        Dictionary dict = BeanPlusUtil.toBean(dictType, Dictionary.class);
        dict.setName(DefValConstants.DICT_PLACEHOLDER);
        dict.setCode(DefValConstants.DICT_PLACEHOLDER);
        dict.setSortValue(DefValConstants.SORT_VALUE);
        dict.setReadonly(false);
        baseMapper.insert(dict);
        return dict;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteType(List<String> types) {
        LbqWrapper<Dictionary> typeWrapper = Wraps.<Dictionary>lbQ().in(Dictionary::getType, types);
        List<Dictionary> list = list(typeWrapper);

        List<Long> ids = list.stream().map(Dictionary::getId).collect(toList());
        boolean bool = removeByIds(ids);

        delCache(ids);
        types.forEach(type -> {
            CacheKey typeKey = new DictionaryTypeCacheKeyBuilder().key(type);
            cachePlusOps.del(typeKey);
        });
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateType(DictionaryTypeSaveDTO dictType) {
        LbqWrapper<Dictionary> wrapper = Wraps.<Dictionary>lbQ().eq(Dictionary::getType, dictType.getType());
        List<Dictionary> list = list(wrapper);
        if (list.isEmpty()) {
            return true;
        }
        list.forEach(item -> {
            item.setState(dictType.getState());
            item.setLabel(dictType.getLabel());
            item.setUpdateTime(null);
        });
        boolean bool = updateBatchById(list);

        delCache(list.stream().map(Dictionary::getId).collect(toList()));
        CacheKey typeKey = new DictionaryTypeCacheKeyBuilder().key(dictType.getType());
        cachePlusOps.del(typeKey);
        return bool;
    }

    @Override
    @Transactional(readOnly = true)
    public IPage<Dictionary> pageType(IPage<Dictionary> page, DictionaryPageQuery query) {
        return baseMapper.pageType(page, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Dictionary model) {
        int count = count(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, model.getType()).eq(Dictionary::getCode, model.getCode()));
        BizAssert.isFalse(count > 0, StrUtil.format("字典[{}]已经存在，请勿重复创建", model.getCode()));

        Dictionary type = getOne(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, model.getType()).eq(Dictionary::getCode, DefValConstants.DICT_PLACEHOLDER));
        boolean bool;
        if (type == null) {
            bool = super.save(model);
        } else {
            BeanPlusUtil.copyProperties(model, type, SuperEntity.FIELD_ID, SuperEntity.CREATE_TIME, SuperEntity.CREATED_BY);
            bool = super.updateById(type);
            setCache(type);
        }

        CacheHashKey typeKey = new DictionaryTypeCacheKeyBuilder().hashKey(model.getCode(), model.getType());
        cachePlusOps.hSet(typeKey, model.getId());
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllById(Dictionary model) {
        return update(model, super::updateAllById);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Dictionary model) {
        return update(model, super::updateById);
    }

    private boolean update(Dictionary model, Function<Dictionary, Boolean> function) {
        int count = count(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, model.getType())
                .eq(Dictionary::getCode, model.getCode()).ne(Dictionary::getId, model.getId()));
        BizAssert.isFalse(count > 0, StrUtil.format("字典[{}]已经存在，请勿重复创建", model.getCode()));
        Dictionary old = getById(model.getId());
        BizAssert.notNull(old, "字典不存在或已被删除！");
        boolean bool = function.apply(model);

        CacheHashKey typeKey = new DictionaryTypeCacheKeyBuilder().hashKey(old.getCode(), old.getType());
        cachePlusOps.hDel(typeKey);
        CacheHashKey newKey = new DictionaryTypeCacheKeyBuilder().hashKey(model.getCode(), model.getType());
        cachePlusOps.hSet(newKey, model.getId());
        return bool;
    }

    @Override
    public boolean removeById(Serializable id) {
        Dictionary model = getById(id);
        BizAssert.notNull(model, "字典项不存在");
        boolean remove = super.removeById(id);

        CacheHashKey typeKey = new DictionaryTypeCacheKeyBuilder().hashKey(model.getCode(), model.getType());
        cachePlusOps.hDel(typeKey);
        return remove;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (idList.isEmpty()) {
            return true;
        }
        List<Dictionary> list = listByIds(idList);
        boolean remove = super.removeByIds(idList);
        list.forEach(model -> {
            CacheHashKey typeKey = new DictionaryTypeCacheKeyBuilder().hashKey(model.getCode(), model.getType());
            cachePlusOps.hDel(typeKey);
        });
        return remove;
    }


    @Override
    public Map<String, List<Dictionary>> listByTypes(String[] types) {
        if (ArrayUtil.isEmpty(types)) {
            return Collections.emptyMap();
        }
        LbqWrapper<Dictionary> query = Wraps.<Dictionary>lbQ()
                .in(Dictionary::getType, (Object[]) types)
                .eq(Dictionary::getState, true)
                .orderByAsc(Dictionary::getSortValue);
        List<Dictionary> list = super.list(query);

        //key 是类型
        return list.stream().collect(groupingBy(Dictionary::getType, LinkedHashMap::new, toList()));
    }

    @Override
    public Map<String, Map<Dictionary, String>> mapInverse(String[] types) {
        Map<String, List<Dictionary>> typeMap = listByTypes(types);

        //需要返回的map
        Map<String, Map<Dictionary, String>> typeCodeNameMap = new LinkedHashMap<>(CollHelper.initialCapacity(typeMap.size()));

        typeMap.forEach((key, items) -> {
            ImmutableMap<Dictionary, String> itemCodeMap = CollHelper.uniqueIndex(items, i -> i, Dictionary::getName);
            typeCodeNameMap.put(key, itemCodeMap);
        });

        return typeCodeNameMap;
    }

    /**
     * 缓存方案
     * 2. 根据type+code，查所有 item name
     * {tenant}:dictionary_type:{type}  {code} -> itemId
     *
     * <p>
     * 3、简单存储
     * {tenant}:dictionary_item:{id} -> obj
     *
     * @param typeCodes 类型+编码 数组
     * @return 字典
     */
    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> typeCodes) {
        if (typeCodes.isEmpty()) {
            return Collections.emptyMap();
        }
        Set<String> types = typeCodes.parallelStream().filter(Objects::nonNull)
                .map(item -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[0]).collect(Collectors.toSet());
        Set<String> multiCodes = typeCodes.parallelStream().filter(Objects::nonNull)
                .map(item -> StrUtil.split(String.valueOf(item), ips.getDictSeparator())[1]).collect(Collectors.toSet());
        Set<String> codes = multiCodes.parallelStream()
                .map(item -> StrUtil.split(String.valueOf(item), ips.getDictItemSeparator()))
                .flatMap(Arrays::stream).collect(Collectors.toSet());

        // 1.1 根据 type+code 查询 item id
        List<Long> itemIdList = new ArrayList<>();
        for (String type : types) {
            for (String code : codes) {
                String itemId = cachePlusOps.hGet(new DictionaryTypeCacheKeyBuilder().hashKey(code, type), hk -> {
                    Dictionary one = getOne(Wraps.<Dictionary>lbQ().eq(Dictionary::getType, type).eq(Dictionary::getCode, code), false);
                    return one != null ? String.valueOf(one.getId()) : null;
                });
                if (itemId != null) {
                    itemIdList.add(Convert.toLong(itemId));
                }
            }
        }
        // 1.2 根据 item id 查询DictionaryItem
        List<Dictionary> list = findByIds(itemIdList, this::listByIds);

        // 2. 将 list 转换成 Map，Map的key是字典编码，value是字典名称
        ImmutableMap<String, String> typeMap = CollHelper.uniqueIndex(list,
                item -> StrUtil.join(ips.getDictSeparator(), item.getType(), item.getCode())
                , Dictionary::getName);

        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, Object> typeCodeNameMap = new HashMap<>(CollHelper.initialCapacity(typeMap.size()));
        typeMap.forEach(typeCodeNameMap::put);
        return typeCodeNameMap;
    }

    @Override
    public void refreshCache() {
        List<Dictionary> list = list();
        list.forEach(model -> {
            this.setCache(model);

            CacheHashKey typeKey = new DictionaryTypeCacheKeyBuilder().hashKey(model.getCode(), model.getType());
            cachePlusOps.hSet(typeKey, model.getId());
        });
    }
}
