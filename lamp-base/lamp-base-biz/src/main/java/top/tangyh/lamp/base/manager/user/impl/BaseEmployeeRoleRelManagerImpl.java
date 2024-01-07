package top.tangyh.lamp.base.manager.user.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.cache.repository.CacheOps;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.base.entity.system.BaseRole;
import top.tangyh.lamp.base.entity.user.BaseEmployeeRoleRel;
import top.tangyh.lamp.base.manager.system.BaseRoleManager;
import top.tangyh.lamp.base.manager.user.BaseEmployeeRoleRelManager;
import top.tangyh.lamp.base.mapper.user.BaseEmployeeRoleRelMapper;
import top.tangyh.lamp.common.cache.base.user.EmployeeRoleCacheKeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 员工的角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseEmployeeRoleRelManagerImpl extends SuperManagerImpl<BaseEmployeeRoleRelMapper, BaseEmployeeRoleRel> implements BaseEmployeeRoleRelManager {
    private final BaseRoleManager baseRoleManager;
    private final CacheOps cacheOps;

    @Override
    public boolean removeByEmployeeIds(Collection<Long> employeeIds) {
        ArgumentAssert.notEmpty(employeeIds, "员工ID不能为空");
        boolean remove = remove(Wraps.<BaseEmployeeRoleRel>lbQ().in(BaseEmployeeRoleRel::getEmployeeId, employeeIds));
        cacheOps.del(employeeIds.stream().map(EmployeeRoleCacheKeyBuilder::build).toList());
        return remove;
    }

    @Override
    public boolean bindRole(List<Long> employeeIdList, String code) {
        BaseRole role = baseRoleManager.getRoleByCode(code);
        ArgumentAssert.notNull(role, "请先配置{}管理员", code);
        List<BaseEmployeeRoleRel> erList = employeeIdList.stream().map(employeeId -> {
            BaseEmployeeRoleRel employeeRole = new BaseEmployeeRoleRel();
            employeeRole.setEmployeeId(employeeId);
            employeeRole.setRoleId(role.getId());
            return employeeRole;
        }).toList();

        boolean flag = saveBatch(erList);

        cacheOps.del(employeeIdList.stream().map(EmployeeRoleCacheKeyBuilder::build).toList());
        return flag;
    }

    @Override
    public boolean unBindRole(List<Long> employeeIdList, String code) {
        ArgumentAssert.notEmpty(employeeIdList, "请传递员工");
        BaseRole role = baseRoleManager.getRoleByCode(code);
        ArgumentAssert.notNull(role, "请先配置{}管理员", code);
        boolean flag = remove(Wraps.<BaseEmployeeRoleRel>lbQ().eq(BaseEmployeeRoleRel::getRoleId, role.getId())
                .in(BaseEmployeeRoleRel::getEmployeeId, employeeIdList));
        cacheOps.del(employeeIdList.stream().map(EmployeeRoleCacheKeyBuilder::build).toList());
        return flag;
    }

    @Override
    public void deleteByRole(Collection<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return;
        }
        LbQueryWrap<BaseEmployeeRoleRel> wrap = Wraps.<BaseEmployeeRoleRel>lbQ().in(BaseEmployeeRoleRel::getRoleId, roleIdList);
        List<BaseEmployeeRoleRel> list = list(wrap);
        remove(wrap);
        cacheOps.del(list.stream().map(BaseEmployeeRoleRel::getEmployeeId).distinct().map(EmployeeRoleCacheKeyBuilder::build).toList());
    }
}
