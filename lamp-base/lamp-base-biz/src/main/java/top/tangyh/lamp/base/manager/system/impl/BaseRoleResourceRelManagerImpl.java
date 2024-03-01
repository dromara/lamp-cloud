package top.tangyh.lamp.base.manager.system.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.lamp.base.entity.system.BaseRoleResourceRel;
import top.tangyh.lamp.base.manager.system.BaseRoleResourceRelManager;
import top.tangyh.lamp.base.mapper.system.BaseRoleResourceRelMapper;
import top.tangyh.lamp.common.cache.base.system.RoleResourceCacheKeyBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseRoleResourceRelManagerImpl extends SuperManagerImpl<BaseRoleResourceRelMapper, BaseRoleResourceRel> implements BaseRoleResourceRelManager {
    private final CacheOps cacheOps;

    @Override
    public List<BaseRoleResourceRel> findByRoleIdAndCategory(Long roleId, String category) {
        return baseMapper.findByRoleIdAndCategory(roleId, category);
    }

    @Override
    public void deleteByRole(Collection<Long> roleIdList) {
        List<BaseRoleResourceRel> roleResourceRelList = list(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));
        super.remove(Wraps.<BaseRoleResourceRel>lbQ().in(BaseRoleResourceRel::getRoleId, roleIdList));

        List<CacheKey> keys = new ArrayList<>();
        for (BaseRoleResourceRel rr : roleResourceRelList) {
            keys.add(RoleResourceCacheKeyBuilder.build(rr.getApplicationId(), rr.getRoleId()));
            keys.add(RoleResourceCacheKeyBuilder.build(null, rr.getRoleId()));
        }
        cacheOps.del(keys);
    }
}
