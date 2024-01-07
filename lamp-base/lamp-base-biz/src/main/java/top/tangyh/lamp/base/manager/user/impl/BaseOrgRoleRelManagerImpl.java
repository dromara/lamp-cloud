package top.tangyh.lamp.base.manager.user.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.lamp.base.entity.user.BaseOrgRoleRel;
import top.tangyh.lamp.base.manager.user.BaseOrgRoleRelManager;
import top.tangyh.lamp.base.mapper.user.BaseOrgRoleRelMapper;
import top.tangyh.lamp.common.cache.base.user.OrgRoleCacheKeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 组织的角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseOrgRoleRelManagerImpl extends SuperManagerImpl<BaseOrgRoleRelMapper, BaseOrgRoleRel> implements BaseOrgRoleRelManager {
    private final CacheOps cacheOps;

    @Override
    public void deleteByOrg(Collection<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        super.remove(Wraps.<BaseOrgRoleRel>lbQ().in(BaseOrgRoleRel::getOrgId, idList));
        cacheOps.del(idList.stream().distinct().map(OrgRoleCacheKeyBuilder::build).toList());
    }

    @Override
    public void deleteByRole(Collection<Long> idList) {
        if (CollUtil.isEmpty(idList)) {
            return;
        }
        LbQueryWrap<BaseOrgRoleRel> wrap = Wraps.<BaseOrgRoleRel>lbQ()
                .in(BaseOrgRoleRel::getRoleId, idList);
        List<BaseOrgRoleRel> list = list(wrap);
        remove(wrap);
        cacheOps.del(list.stream().map(BaseOrgRoleRel::getOrgId).distinct().map(OrgRoleCacheKeyBuilder::build).toList());
    }
}
