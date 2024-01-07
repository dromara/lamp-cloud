package top.tangyh.lamp.base.mapper.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.base.entity.user.BaseEmployeeOrgRel;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 员工所在部门
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Repository
public interface BaseEmployeeOrgRelMapper extends SuperMapper<BaseEmployeeOrgRel> {

    /**
     * 查询员工拥有的机构
     *
     * @param employeeId employeeId
     * @return java.util.List<java.lang.Long>
     * @author tangyh
     * @date 2022/10/20 3:44 PM
     * @create [2022/10/20 3:44 PM ] [tangyh] [初始创建]
     */
    List<Long> selectOrgByEmployeeId(@Param("employeeId") Long employeeId);
}
