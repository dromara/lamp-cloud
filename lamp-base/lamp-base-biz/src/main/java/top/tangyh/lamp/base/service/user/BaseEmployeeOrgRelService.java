package top.tangyh.lamp.base.service.user;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.base.entity.user.BaseEmployeeOrgRel;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 员工所在部门
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BaseEmployeeOrgRelService extends SuperService<Long, BaseEmployeeOrgRel> {
    /**
     * 根据员工id查询员工的机构id
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findOrgIdListByEmployeeId(Long employeeId);
}
