package com.tangyh.lamp.authority.service.common.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.repository.CacheOps;
import com.tangyh.basic.context.ContextUtil;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.utils.BizAssert;
import com.tangyh.basic.utils.SpringUtils;
import com.tangyh.lamp.authority.dao.common.ParameterMapper;
import com.tangyh.lamp.authority.entity.common.Parameter;
import com.tangyh.lamp.authority.event.ParameterUpdateEvent;
import com.tangyh.lamp.authority.event.model.ParameterUpdate;
import com.tangyh.lamp.authority.service.common.ParameterService;
import com.tangyh.lamp.common.cache.common.ParameterKeyCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class ParameterServiceImpl extends SuperServiceImpl<ParameterMapper, Parameter> implements ParameterService {

    private final CacheOps cacheOps;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Parameter model) {
        BizAssert.isFalse(check(model.getKey()), "参数key重复");

        boolean bool = SqlHelper.retBool(baseMapper.insert(model));
        if (bool) {
            CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(model.getKey());
            cacheOps.set(cacheKey, model.getValue());
        }
        return bool;
    }

    @Transactional(readOnly = true)
    public boolean check(String key) {
        return count(Wraps.<Parameter>lbQ().eq(Parameter::getKey, key)) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Parameter model) {
        int count = count(Wraps.<Parameter>lbQ().eq(Parameter::getKey, model.getKey()).ne(Parameter::getId, model.getId()));
        BizAssert.isFalse(count > 0, StrUtil.format("参数key[{}]已经存在，请勿重复创建", model.getKey()));

        boolean bool = SqlHelper.retBool(getBaseMapper().updateById(model));
        if (bool) {

            CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(model.getKey());
            cacheOps.set(cacheKey, model.getValue());

            SpringUtils.publishEvent(new ParameterUpdateEvent(
                    new ParameterUpdate(model.getKey(), model.getValue(), null, ContextUtil.getTenant())
            ));
        }
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return true;
        }
        List<Parameter> parameterList = super.listByIds(idList);
        if (parameterList.isEmpty()) {
            return true;
        }
        boolean bool = SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
        CacheKey[] keys = parameterList.stream()
                .map(item -> new ParameterKeyCacheKeyBuilder().key(item.getKey()))
                .toArray(CacheKey[]::new);
        cacheOps.del(keys);

        parameterList.forEach(model ->
                SpringUtils.publishEvent(new ParameterUpdateEvent(
                        new ParameterUpdate(model.getKey(), model.getValue(), null, ContextUtil.getTenant())
                ))
        );
        return bool;
    }

    @Override
    public String getValue(String key, String defVal) {
        if (StrUtil.isEmpty(key)) {
            return defVal;
        }

        Function<CacheKey, String> loader = k -> {
            Parameter parameter = getOne(Wraps.<Parameter>lbQ().eq(Parameter::getKey, key));
            return parameter == null ? defVal : parameter.getValue();
        };
        CacheKey cacheKey = new ParameterKeyCacheKeyBuilder().key(key);
        return cacheOps.get(cacheKey, loader);
    }

    @Override
    public Map<String, String> findParams(List<String> keys) {
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptyMap();
        }
        List<Parameter> list = list(Wraps.<Parameter>lbQ().in(Parameter::getKey, keys));
        Map<String, String> map = new HashMap<>();
        list.forEach(item -> map.put(item.getKey(), item.getValue()));
        return map;
    }
}
