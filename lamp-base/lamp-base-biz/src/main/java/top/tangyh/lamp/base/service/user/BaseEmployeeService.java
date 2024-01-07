package top.tangyh.lamp.base.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.vo.query.user.BaseEmployeePageQuery;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.base.vo.save.user.BaseEmployeeRoleRelSaveVO;
import top.tangyh.lamp.base.vo.save.user.BaseEmployeeSaveVO;
import top.tangyh.lamp.base.vo.update.user.BaseEmployeeUpdateVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 业务接口
 * 员工
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BaseEmployeeService extends SuperCacheService<Long, BaseEmployee> {
    /**
     * 批量保存
     *
     * @param entityList entityList
     * @return boolean
     * @author tangyh
     * @date 2022/10/28 4:38 PM
     * @create [2022/10/28 4:38 PM ] [tangyh] [初始创建]
     */
    boolean saveBatch(Collection<BaseEmployee> entityList);

    /**
     * 给员工分配角色
     *
     * @param employeeRoleSaveVO
     * @return
     */
    List<Long> saveEmployeeRole(BaseEmployeeRoleRelSaveVO employeeRoleSaveVO);

    /**
     * 根据员工id查询员工的角色
     *
     * @param employeeId 员工id
     * @return
     */
    List<Long> findEmployeeRoleByEmployeeId(Long employeeId);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    IPage<BaseEmployeeResultVO> findPageResultVO(PageParams<BaseEmployeePageQuery> params);


    /**
     * 批量保存 基础库员工和系统角色
     *
     * @param employeeList
     * @return
     */
    boolean saveBatchBaseEmployeeAndRole(List<BaseEmployee> employeeList);

    /**
     * 根据ID修改不为空的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author tangyh
     * @date 2022/10/28 9:20 AM
     * @create [2022/10/28 9:20 AM ] [tangyh] [初始创建]
     */
    boolean updateById(BaseEmployee baseEmployee);

    /**
     * 根据ID修改所有的字段
     *
     * @param baseEmployee baseEmployee
     * @return boolean
     * @author tangyh
     * @date 2022/10/28 9:20 AM
     * @create [2022/10/28 9:20 AM ] [tangyh] [初始创建]
     */
    boolean updateAllById(BaseEmployee baseEmployee);

    /**
     * 查询指定企业指定用户的员工信息
     *
     * @param userId 用户id
     * @return
     */
    BaseEmployee getEmployeeByUser(Long userId);


    /**
     * 根据用户id查询员工
     *
     * @param userId 用户id
     * @return
     */
    List<BaseEmployeeResultVO> listEmployeeByUserId(Long userId);
}
