package com.github.zuihou.authority.service.core.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.github.zuihou.authority.dao.core.OrgMapper;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static com.github.zuihou.common.constant.CacheKey.ORG;

/**
 * <p>
 * 业务实现类
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Service
@CacheConfig(cacheNames = ORG)
public class OrgServiceImpl extends SuperCacheServiceImpl<OrgMapper, Org> implements OrgService {
    @Override
    protected String getRegion() {
        return ORG;
    }

    protected OrgService currentProxy() {
        return ((OrgService) AopContext.currentProxy());
    }

    @Override
    public List<Org> findChildren(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        // MySQL 全文索引
        String applySql = String.format(" MATCH(tree_path) AGAINST('%s' IN BOOLEAN MODE) ", StringUtils.join(ids, " "));

        return super.list(Wraps.<Org>lbQ().in(Org::getId, ids).or(query -> query.apply(applySql)));
    }

    @Override
    public boolean remove(List<Long> ids) {
        List<Org> list = this.findChildren(ids);
        List<Long> idList = list.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
        return !idList.isEmpty() ? super.removeByIds(idList) : true;
    }

    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        List<Org> list = getOrgs(ids);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, (org) -> org);
        return typeMap;
    }

    private List<Org> getOrgs(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> idList = ids.stream().mapToLong(Convert::toLong).boxed().collect(Collectors.toList());

        List<Org> list = null;
        if (idList.size() <= 1000) {
            list = idList.stream().map(currentProxy()::getByIdCache).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            LbqWrapper<Org> query = Wraps.<Org>lbQ()
                    .in(Org::getId, idList)
                    .eq(Org::getStatus, true);
            list = super.list(query);

            if (!list.isEmpty()) {
                list.forEach(item -> {
                    String itemKey = key(item.getId());
                    cacheChannel.set(getRegion(), itemKey, item);
                });
            }
        }
        return list;
    }

    @Override
    public Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids) {
        List<Org> list = getOrgs(ids);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, Org::getLabel);
        return typeMap;
    }

}
