package top.tangyh.lamp.base.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.base.entity.system.BaseRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Repository
public interface BaseRoleMapper extends SuperMapper<BaseRole> {

    /**
     * 根据角色id查询员工id
     *
     * @param roleIds roleIds
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2021/12/28 12:22 上午
     * @create [2021/12/28 12:22 上午 ] [tangyh] [初始创建]
     */
    List<Long> listEmployeeIdByRoleId(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据机构id查询对应的角色
     *
     * @param orgId orgId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 4:06 PM
     * @create [2022/10/20 4:06 PM ] [tangyh] [初始创建]
     */
    List<Long> selectRoleIdByOrgId(@Param("orgId") Long orgId);

    /**
     * 查询员工拥有的角色ID
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 3:44 PM
     * @create [2022/10/20 3:44 PM ] [tangyh] [初始创建]
     */
    List<Long> selectRoleByEmployeeId(@Param("employeeId") Long employeeId);

    /**
     * 查询员工拥有的角色
     *
     * @param employeeId 员工id
     * @param codes      角色编码
     * @return java.util.List<BaseRole> 角色
     * @author tangyh
     * @date 2022/10/20 3:44 PM
     * @create [2022/10/20 3:44 PM ] [tangyh] [初始创建]
     */
    List<BaseRole> selectRoleByEmployee(@Param("employeeId") Long employeeId, @Param("codes") String... codes);

}
