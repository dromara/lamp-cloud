package top.tangyh.lamp.base.biz.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.tangyh.lamp.base.entity.system.BaseRole;
import top.tangyh.lamp.base.service.system.BaseRoleService;
import top.tangyh.lamp.model.enumeration.base.RoleCategoryEnum;
import top.tangyh.lamp.model.enumeration.system.DataTypeEnum;
import top.tangyh.lamp.system.service.application.DefResourceService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @author zuihou
 * @date 2021/11/20 23:49
 */

@Service
@RequiredArgsConstructor
public class BaseRoleBiz {
    private final BaseRoleService baseRoleService;
    private final DefResourceService defResourceService;

    /**
     * 查询角色拥有的资源
     * 1. 系统管理员 拥有该租户下的所有资源
     * 2. 其他管理员 拥有分配给他的资源
     *
     * @param roleId 角色ID
     * @return java.util.Map<java.lang.Long, java.util.Collection < java.lang.Long>>
     * @author tangyh
     * @date 2022/10/19 5:29 PM
     * @create [2022/10/19 5:29 PM ] [tangyh] [初始创建]
     */
    public Map<Long, Collection<Long>> findResourceIdByRoleId(Long roleId) {
        BaseRole baseRole = baseRoleService.getById(roleId);
        if (baseRole == null) {
            return Collections.emptyMap();
        }
//        系统管理员有全部权限(管理员在 base_role_resource_rel 表无数据)
        if (DataTypeEnum.SYSTEM.eq(baseRole.getType())) {
            return defResourceService.findResource();
        }

        return baseRoleService.findResourceIdByRoleId(roleId, RoleCategoryEnum.FUNCTION);
    }
}
