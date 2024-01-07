package top.tangyh.lamp.base.mapper.system;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.base.entity.system.BaseRoleResourceRel;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色的资源
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Repository
public interface BaseRoleResourceRelMapper extends SuperMapper<BaseRoleResourceRel> {

    /**
     * 根据角色id和角色类别，查询角色拥有的权限
     *
     * @param roleId   角色ID
     * @param category 角色类别
     * @return
     */
    List<BaseRoleResourceRel> findByRoleIdAndCategory(@Param("roleId") Long roleId, @Param("category") String category);


    /**
     * 根据应用id和角色id查询对应的资源ID
     *
     * @param applicationId applicationId
     * @param roleId        roleId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 4:06 PM
     * @create [2022/10/20 4:06 PM ] [tangyh] [初始创建]
     */
    List<Long> selectResourceIdByRoleId(@Param("applicationId") Long applicationId, @Param("roleId") Long roleId);

    /**
     * 查询员工在某应用下拥有的资源
     *
     * @param applicationId 应用ID
     * @param employeeId    员工ID
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2023/5/17 5:34 PM
     * @create [2023/5/17 5:34 PM ] [tangyh] [初始创建]
     */
    List<Long> selectResourceIdByEmployeeId(@Param("applicationId") Long applicationId, @Param("employeeId") Long employeeId);
}
