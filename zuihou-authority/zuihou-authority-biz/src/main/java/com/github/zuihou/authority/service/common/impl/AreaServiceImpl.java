package com.github.zuihou.authority.service.common.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.AreaMapper;
import com.github.zuihou.authority.entity.common.Area;
import com.github.zuihou.authority.service.common.AreaService;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
    @Autowired
    private CacheChannel cache;

    @Override
    public List<Area> findAll() {
        String tenantKey = CacheKey.buildTenantKey();

        List<Area> dbList = new ArrayList<>();
        CacheObject cacheObject = cache.get(CacheKey.AREA_ALL, tenantKey, (k) -> {
            LbqWrapper<Area> query = Wraps.<Area>lbQ().orderByAsc(Area::getSortValue);
            dbList.addAll(super.list(query));
            return dbList.stream().mapToLong(Area::getId).boxed().collect(Collectors.toList());
        });

        if (cacheObject.getValue() == null) {
            return Collections.emptyList();
        }

        if (!dbList.isEmpty()) {
            // TODO 异步性能 更加
            dbList.forEach((area) -> {
                String areaKey = CacheKey.buildKey(area.getId());
                cache.set(CacheKey.AREA, areaKey, area);
            });
            return dbList;
        }

        // 数据量比较大时，建议定时同步数据到缓存，否则请直接根据ids 查询db， 否则for查询遇到缓存穿透 性能很差！
        List<Long> list = (List<Long>) cacheObject.getValue();

        List<Area> menuList = list.stream().map(((AreaService) AopContext.currentProxy())::getByIdWithCache)
                .filter(Objects::nonNull).collect(Collectors.toList());

        return menuList;
    }

    @Override
    @Cacheable(value = CacheKey.AREA, key = "#id")
    public Area getByIdWithCache(Long id) {
        return super.getById(id);
    }


    @Override
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        boolean result = super.removeByIds(ids);

        String[] keys = ids.stream().map(CacheKey::buildKey).toArray(String[]::new);
        cache.evict(CacheKey.AREA, keys);


        String tenantKey = CacheKey.buildTenantKey();
        cache.evict(CacheKey.AREA_ALL, tenantKey);
        return result;
    }

    @Override
    public boolean updateWithCache(Area area) {
        boolean result = super.updateById(area);
        if (result) {
            String menuKey = CacheKey.buildKey(area.getId());
            cache.evict(CacheKey.AREA, menuKey);

            String tenantKey = CacheKey.buildTenantKey();
            cache.evict(CacheKey.AREA_ALL, tenantKey);
        }
        return result;
    }

    @Override
    public boolean saveWithCache(Area area) {
        super.save(area);

        String key = CacheKey.buildKey(area.getId());
        cache.set(CacheKey.AREA, key, area);

        String tenantKey = CacheKey.buildTenantKey();
        cache.evict(CacheKey.AREA_ALL, tenantKey);

        return true;
    }
}
