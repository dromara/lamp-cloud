package top.tangyh.lamp.base.mapper.user;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 员工
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Repository
public interface BaseEmployeeMapper extends SuperMapper<BaseEmployee> {
    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param wrapper 查询条件
     * @return 分页用户数据
     */
    IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page, @Param(Constants.WRAPPER) Wrapper<BaseEmployee> wrapper);

    /**
     * 根据用户id查询员工
     *
     * @param userId 用户id
     * @return
     */
    List<BaseEmployeeResultVO> listEmployeeByUserId(@Param("userId") Long userId);
}
