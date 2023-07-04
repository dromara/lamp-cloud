package top.tangyh.lamp.authority.service.core;

import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.basic.interfaces.echo.LoadService;
import top.tangyh.lamp.authority.entity.core.Org;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
public interface OrgService extends SuperCacheService<Org>, LoadService {
    /**
     * 查询指定id集合下的所有子集
     *
     * @param ids id
     * @return 所有的子组织
     */
    List<Org> findChildren(List<Long> ids);

    /**
     * 批量删除以及删除其子节点
     *
     * @param ids id
     * @return 是否成功
     */
    boolean remove(List<Long> ids);

    /**
     * 检测名称是否存在
     *
     * @param id       机构id
     * @param parentId 父id
     * @param name     机构名称
     * @return boolean
     * @author zuihou
     * @date 2021/5/23 9:37 下午
     * @create [2021/5/23 9:37 下午 ] [tangyh] [初始创建]
     */
    boolean check(Long id, Long parentId, String name);


    /**
     * 查询员工主部门ID
     *
     * @param userId 员工ID
     * @return
     */
    Long getMainDeptIdByUserId(Long userId);

    /**
     * 查询员工主部门及其所有的子部门ID
     *
     * @param userId 员工ID
     * @return
     */
    List<Long> findDeptAndChildrenIdByUserId(Long userId);

    /**
     * 查询员工主部门
     *
     * @param userId 员工ID
     * @return
     */
    Org getMainCompanyByUserId(Long userId);

    /**
     * 查询员工主部门的所属单位 ID
     *
     * @param userId 员工ID
     * @return
     */
    Long getMainCompanyIdByUserId(Long userId);

    /**
     * 查询员工主部门所属单位以及单位的所有子组织 ID
     *
     * @param userId 员工ID
     * @return
     */
    List<Long> findCompanyAndChildrenIdByUserId(Long userId);
}
