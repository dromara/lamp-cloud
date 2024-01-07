package top.tangyh.lamp.base.manager.user.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.base.entity.user.BaseEmployeeOrgRel;
import top.tangyh.lamp.base.manager.user.BaseEmployeeOrgRelManager;
import top.tangyh.lamp.base.mapper.user.BaseEmployeeOrgRelMapper;
import top.tangyh.lamp.base.mapper.user.BaseOrgMapper;
import top.tangyh.lamp.common.cache.base.user.EmployeeOrgCacheKeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 员工所在部门
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseEmployeeOrgRelManagerImpl extends SuperManagerImpl<BaseEmployeeOrgRelMapper, BaseEmployeeOrgRel> implements BaseEmployeeOrgRelManager {
    private final CacheOps cacheOps;
    private final BaseOrgMapper orgMapper;

    @Override
    public List<Long> findOrgIdByEmployeeId(Long employeeId) {
        CacheKey eoKey = EmployeeOrgCacheKeyBuilder.build(employeeId);
        CacheResult<List<Long>> orgIdResult = cacheOps.get(eoKey, k -> orgMapper.selectOrgByEmployeeId(employeeId));
        return orgIdResult.asList();
    }

    @Override
    public boolean removeByEmployeeIds(Collection<Long> employeeIds) {
        ArgumentAssert.notEmpty(employeeIds, "员工ID不能为空");

        boolean remove = remove(Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getEmployeeId, employeeIds));
        cacheOps.del(employeeIds.stream().map(EmployeeOrgCacheKeyBuilder::build).toList());
        return remove;
    }

    @Override
    public void deleteByOrg(Collection<Long> orgIdList) {
        if (CollUtil.isEmpty(orgIdList)) {
            return;
        }
        LbQueryWrap<BaseEmployeeOrgRel> wrap = Wraps.<BaseEmployeeOrgRel>lbQ().in(BaseEmployeeOrgRel::getOrgId, orgIdList);
        List<BaseEmployeeOrgRel> list = list(wrap);
        remove(wrap);
        List<CacheKey> keys = list.stream()
                .map(BaseEmployeeOrgRel::getEmployeeId)
                .distinct()
                .map(EmployeeOrgCacheKeyBuilder::build)
                .toList();
        cacheOps.del(keys);
    }
}
