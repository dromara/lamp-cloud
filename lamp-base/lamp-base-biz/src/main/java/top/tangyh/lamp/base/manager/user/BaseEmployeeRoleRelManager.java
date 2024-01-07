package top.tangyh.lamp.base.manager.user;

import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.lamp.base.entity.user.BaseEmployeeRoleRel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 员工的角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BaseEmployeeRoleRelManager extends SuperManager<BaseEmployeeRoleRel> {
    /**
     * 根据员工id删除 员工的角色
     *
     * @param employeeIds 员工
     * @return
     */
    boolean removeByEmployeeIds(Collection<Long> employeeIds);

    /**
     * 根据员工id删除 员工的角色
     *
     * @param employeeId 员工
     * @return
     */
    default boolean removeByEmployeeId(Long employeeId) {
        return removeByEmployeeIds(Collections.singletonList(employeeId));
    }

    /**
     * 给员工绑定指定的角色
     *
     * @param employeeIdList 员工
     * @param code           角色编码
     * @return
     */
    boolean bindRole(List<Long> employeeIdList, String code);

    /**
     * 解绑指定角色
     *
     * @param employeeIdList
     * @param code
     * @return
     */
    boolean unBindRole(List<Long> employeeIdList, String code);

    /**
     * 根据角色id 删除员工有用的角色
     *
     * @param idList idList
     * @author tangyh
     * @date 2022/10/25 11:17 PM
     * @create [2022/10/25 11:17 PM ] [tangyh] [初始创建]
     */
    void deleteByRole(Collection<Long> idList);
}
