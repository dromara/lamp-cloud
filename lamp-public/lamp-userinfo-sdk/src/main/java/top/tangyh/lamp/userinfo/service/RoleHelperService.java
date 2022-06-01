package top.tangyh.lamp.userinfo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.CollHelper;

import top.tangyh.lamp.userinfo.dao.RoleHelperMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/24 2:44 PM
 * @create [2022/4/24 2:44 PM ] [tangyh] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class RoleHelperService {
    private final RoleHelperMapper roleHelperMapper;

    /**
     * 检查某个员工是否拥有某角色
     *
     * @param employeeId employeeId
     * @param codes      codes
     * @return boolean
     * @author tangyh
     * @date 2022/4/24 2:49 PM
     * @create [2022/4/24 2:49 PM ] [tangyh] [初始创建]
     */
    
    public boolean checkRole(Long employeeId, String... codes) {
        ArgumentAssert.notEmpty(codes, "请传递角色编码");
        long count = roleHelperMapper.countRoleFormRole(Arrays.asList(codes), employeeId);
        if (count > 0) {
            return true;
        }
        long mainOrgCount = roleHelperMapper.countRoleFormMainOrg(Arrays.asList(codes), employeeId);
        return mainOrgCount > 0 ? true : roleHelperMapper.countRoleFormOrg(Arrays.asList(codes), employeeId) > 0;
    }

    
    public List<Long> listResourceId(Long employeeId, Long applicationId) {
        List<Long> resourceIdRoleList = roleHelperMapper.selectResourceIdFromRoleByUserId(employeeId, applicationId);
        List<Long> resourceIdMainOrgList = roleHelperMapper.selectResourceIdFromMainOrgByUserId(employeeId, applicationId);
        List<Long> resourceIdOrgList = roleHelperMapper.selectResourceIdFromOrgByUserId(employeeId, applicationId);
        return CollHelper.addAllUnique(resourceIdRoleList, resourceIdMainOrgList, resourceIdOrgList);
    }

    
    public List<String> findRoleCodeByUserId(Long userId) {
        List<String> resourceIdRoleList = roleHelperMapper.selectRoleCodeFromRoleByUserId(userId);
        List<String> resourceIdMainOrgList = roleHelperMapper.selectRoleCodeFromMainOrgByUserId(userId);
        List<String> resourceIdOrgList = roleHelperMapper.selectRoleCodeFromOrgByUserId(userId);
        return CollHelper.addAllUnique(resourceIdRoleList, resourceIdMainOrgList, resourceIdOrgList);
    }
}
