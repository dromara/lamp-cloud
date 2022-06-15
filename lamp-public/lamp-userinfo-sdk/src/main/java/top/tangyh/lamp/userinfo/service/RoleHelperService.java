package top.tangyh.lamp.userinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.utils.ArgumentAssert;
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
        ContextUtil.setDatabaseBase();
        ArgumentAssert.notEmpty(codes, "请传递角色编码");
        long count = roleHelperMapper.countRoleFormRole(Arrays.asList(codes), employeeId);
        return count > 0;
    }

    public List<Long> listResourceId(Long employeeId) {
        ContextUtil.setDatabaseBase();
        return roleHelperMapper.selectResourceIdFromRoleByUserId(employeeId);
    }

    public List<String> findRoleCodeByUserId(Long userId) {
        ContextUtil.setDatabaseBase();
        return roleHelperMapper.selectRoleCodeFromRoleByUserId(userId);
    }
}
