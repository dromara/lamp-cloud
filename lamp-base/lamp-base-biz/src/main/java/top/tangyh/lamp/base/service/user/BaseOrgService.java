package top.tangyh.lamp.base.service.user;

import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.lamp.base.entity.user.BaseOrg;
import top.tangyh.lamp.base.vo.query.user.BaseOrgPageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseOrgResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseOrgRoleRelSaveVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BaseOrgService extends SuperCacheService<Long, BaseOrg> {
    /**
     * 根据id查询待回显参数
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    Map<Serializable, Object> findByIds(Set<Serializable> ids);

    /**
     * 检测机构名称是否存在
     *
     * @param name     机构名称
     * @param parentId 父ID
     * @param id       机构id
     * @return
     */
    boolean check(String name, Long parentId, Long id);

    /**
     * 查询机构树
     *
     * @param query 参数
     * @return 机构树
     */
    List<BaseOrgResultVO> tree(BaseOrgPageQuery query);

    /**
     * 给机构分配角色
     *
     * @param orgRoleSaveVO 参数
     * @return 新增结果
     */
    List<Long> saveOrgRole(BaseOrgRoleRelSaveVO orgRoleSaveVO);

    /**
     * 查询机构的角色
     *
     * @param orgId 员工id
     * @return 新增结果
     */
    List<Long> findOrgRoleByOrgId(Long orgId);

    /**
     * 查询员工{employeeId}的在指定公司{companyId}下的所有部门
     *
     * @param employeeId 员工ID
     * @param companyId  公司ID
     * @return java.util.List<top.tangyh.lamp.base.entity.user.BaseOrg>
     * @author tangyh
     * @date 2022/10/26 10:59 PM
     * @create [2022/10/26 10:59 PM ] [tangyh] [初始创建]
     */
    List<BaseOrg> findDeptByEmployeeId(Long employeeId, Long companyId);


    /**
     * 查询员工的公司
     *
     * @param employeeId 员工ID
     * @return java.util.List<top.tangyh.lamp.model.entity.base.SysOrg>
     * @author tangyh
     * @date 2022/10/26 10:29 PM
     * @create [2022/10/26 10:29 PM ] [tangyh] [初始创建]
     */
    List<BaseOrg> findCompanyByEmployeeId(Long employeeId);

    /**
     * 查询 {companyList} 中id等于 {lastCompanyId} 的公司 或 部门
     *
     * @param orgList   公司 或 部门列表
     * @param lastOrgId 最后一次登录的公司ID 或 部门Id
     * @return top.tangyh.lamp.base.entity.user.BaseOrg
     * @author tangyh
     * @date 2022/10/26 10:27 PM
     * @create [2022/10/26 10:27 PM ] [tangyh] [初始创建]
     */
    BaseOrg getDefaultOrg(List<BaseOrg> orgList, Long lastOrgId);
}
