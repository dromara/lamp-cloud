package com.github.zuihou.authority.service.core.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.core.OrgMapper;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.service.auth.RoleOrgService;
import com.github.zuihou.authority.service.core.OrgService;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.utils.MapHelper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements OrgService {

    @Autowired
    private RoleOrgService roleOrgService;

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

    /**
     * TODO 这里应该做缓存
     *
     * @param ids
     * @return
     */
    @Override
    public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
        LbqWrapper<Org> query = Wraps.<Org>lbQ()
                .in(Org::getId, ids)
                .eq(Org::getStatus, true);
        List<Org> list = super.list(query);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, (org) -> org);
        return typeMap;
    }

    /**
     * TODO 这里应该做缓存
     *
     * @param ids
     * @return
     */
    @Override
    public Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids) {
        LbqWrapper<Org> query = Wraps.<Org>lbQ()
                .in(Org::getId, ids)
                .eq(Org::getStatus, true);
        List<Org> list = super.list(query);

        //key 是 组织id， value 是org 对象
        ImmutableMap<Serializable, Object> typeMap = MapHelper.uniqueIndex(list, Org::getId, Org::getName);
        return typeMap;
    }

}
