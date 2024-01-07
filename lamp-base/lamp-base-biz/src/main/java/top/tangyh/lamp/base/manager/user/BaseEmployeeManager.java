package top.tangyh.lamp.base.manager.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;

import java.util.List;

/**
 * <p>
 * 通用业务接口
 * 员工
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
public interface BaseEmployeeManager extends SuperCacheManager<BaseEmployee> {
    /**
     * 根据用户id查询员工
     *
     * @param userId 用户id
     * @return
     */
    List<BaseEmployeeResultVO> listEmployeeByUserId(Long userId);

    /**
     * 查询指定企业指定用户的员工信息
     *
     * @param userId 用户id
     * @return
     */
    BaseEmployee getEmployeeByUser(Long userId);


    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 查询条件
     * @return 分页用户数据
     */
    IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page, Wrapper<BaseEmployee> wrapper);

}
