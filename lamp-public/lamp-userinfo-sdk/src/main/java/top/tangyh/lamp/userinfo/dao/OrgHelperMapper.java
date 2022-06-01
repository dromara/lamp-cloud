package top.tangyh.lamp.userinfo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.model.entity.base.SysOrg;

import java.util.List;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/24 11:25 AM
 * @create [2022/4/24 11:25 AM ] [tangyh] [初始创建]
 */
@Repository
public interface OrgHelperMapper extends SuperMapper<SysOrg> {

    /**
     * 根据员工ID查找 所属部门
     *
     * @param employeeId 员工ID
     * @return
     */
    List<SysOrg> findOrgByEmployeeId(@Param("employeeId") Long employeeId);
}
