package top.tangyh.lamp.base.manager.user.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.base.entity.user.BasePosition;
import top.tangyh.lamp.base.manager.user.BasePositionManager;
import top.tangyh.lamp.base.mapper.user.BasePositionMapper;
import top.tangyh.lamp.common.cache.base.user.PositionCacheKeyBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 通用业务实现类
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BasePositionManagerImpl extends SuperCacheManagerImpl<BasePositionMapper, BasePosition> implements BasePositionManager {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new PositionCacheKeyBuilder();
    }

    @Transactional(readOnly = true)
    @Override
    
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        List<BasePosition> list = findByIds(ids, null).stream().filter(Objects::nonNull).toList();
        return CollHelper.uniqueIndex(list, BasePosition::getId, BasePosition::getName);
    }
}
