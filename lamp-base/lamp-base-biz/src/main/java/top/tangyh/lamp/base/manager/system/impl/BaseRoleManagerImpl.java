package top.tangyh.lamp.base.manager.system.impl;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.cache.redis2.CacheResult;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.cache.CacheKey;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.lamp.base.entity.system.BaseRole;
import top.tangyh.lamp.base.manager.system.BaseRoleManager;
import top.tangyh.lamp.base.manager.user.BaseEmployeeOrgRelManager;
import top.tangyh.lamp.base.mapper.system.BaseRoleMapper;
import top.tangyh.lamp.base.mapper.system.BaseRoleResourceRelMapper;
import top.tangyh.lamp.common.cache.base.system.RoleCacheKeyBuilder;
import top.tangyh.lamp.common.cache.base.system.RoleResourceCacheKeyBuilder;
import top.tangyh.lamp.common.cache.base.user.EmployeeRoleCacheKeyBuilder;
import top.tangyh.lamp.common.cache.base.user.OrgRoleCacheKeyBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * <p>
 * 通用业务实现类
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseRoleManagerImpl extends SuperCacheManagerImpl<BaseRoleMapper, BaseRole> implements BaseRoleManager {
    private final BaseRoleResourceRelMapper baseRoleResourceRelMapper;
    private final BaseEmployeeOrgRelManager baseEmployeeOrgRelManager;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new RoleCacheKeyBuilder();
    }

    @Override
    public BaseRole getRoleByCode(String code) {
        ArgumentAssert.notEmpty(code, "请传入角色编码");
        return getOne(Wraps.<BaseRole>lbQ().eq(BaseRole::getCode, code));
    }

    @Override
    public List<Long> listEmployeeIdByRoleId(List<Long> roleIds) {
        return baseMapper.listEmployeeIdByRoleId(roleIds);
    }


    /**
     * 查询员工拥有的资源
     *
     * @param employeeId    员工ID
     * @param applicationId 应用ID
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 11:25 AM
     * @create [2022/10/20 11:25 AM ] [tangyh] [初始创建]
     */
    @Override
    public List<Long> findResourceIdByEmployeeId(Long applicationId, Long employeeId) {

        List<BaseRole> roleList = findRoleByEmployeeId(employeeId);
        List<Long> roleIdList = roleList.stream().map(BaseRole::getId).toList();
        log.debug("roleIdList={}", roleIdList.size());

        if (CollUtil.isEmpty(roleIdList)) {
            return roleIdList;
        }

        // 新方法
        Function<Long, CacheKey> cacheBuilder = roleId -> RoleResourceCacheKeyBuilder.build(applicationId, roleId);
        // 缓存中不存在时，回调函数
        Function<Long, List<Long>> loader = roleId -> baseRoleResourceRelMapper.selectResourceIdByRoleId(applicationId, roleId);
        Set<Long> resourceIdSet = findCollectByIds(roleIdList, cacheBuilder, loader);
        // 新方法 end
        log.debug("resourceIdSet={}", resourceIdSet.size());

        return new ArrayList<>(resourceIdSet);
    }

    @Override
    public List<BaseRole> findRoleByEmployeeId(Long employeeId) {
        List<Long> roleIdList = findRoleIdByEmployeeId(employeeId);
        return findByIds(roleIdList, null).stream().filter(item -> item != null && item.getState()).toList();
    }

    /**
     * 查询员工拥有的角色
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 4:46 PM
     * @create [2022/10/20 4:46 PM ] [tangyh] [初始创建]
     */
    @Override
    public List<Long> findRoleIdByEmployeeId(Long employeeId) {
        // 员工 - 角色
        CacheKey erKey = EmployeeRoleCacheKeyBuilder.build(employeeId);
        CacheResult<List<Long>> roleIdList = cacheOps.get(erKey, k -> baseMapper.selectRoleByEmployeeId(employeeId));
        log.debug("roleIdList={}", roleIdList.asList().size());

        // 员工 - 机构
        List<Long> orgIdList = baseEmployeeOrgRelManager.findOrgIdByEmployeeId(employeeId);
        log.debug("orgIdList={}", orgIdList.size());

        // 机构 - 角色
        // 新旧方法
        // 旧方法
//        Set<Long> roleIdSet = new HashSet<>();
//        for (Long orgId : orgIdList) {
//            CacheKey orKey = OrgRoleCacheKeyBuilder.build(orgId);
//            CacheResult<List<Long>> roleIds = cacheOps.get(orKey, k -> baseMapper.selectRoleIdByOrgId(orgId));
//            roleIdSet.addAll(roleIds.asList());
//        }
        // 旧方法end

        // 新方法
        Function<Long, CacheKey> cacheBuilder = OrgRoleCacheKeyBuilder::build;
        // 缓存中不存在时，回调函数
        Function<Long, List<Long>> loader = baseMapper::selectRoleIdByOrgId;
        Set<Long> roleIdSet = findCollectByIds(orgIdList, cacheBuilder, loader);
        // 新方法 end

        return CollHelper.addAllUnique(new ArrayList<>(roleIdSet), roleIdList.asList());
    }

    @Override
    public boolean checkRole(Long employeeId, String... codes) {
        /*ArgumentAssert.notEmpty(codes, "请传递角色编码");

        List<Long> roleIds = findRoleIdByEmployeeId(employeeId);
        List<BaseRole> roleList = findByIds(roleIds, null);
        return roleList.stream()
                .filter(Objects::nonNull)
                .anyMatch(item -> item.getState() && ArrayUtil.contains(codes, item.getCode()));*/
        List<BaseRole> baseRoles = baseMapper.selectRoleByEmployee(employeeId, codes);
        return !baseRoles.isEmpty();
    }

}
