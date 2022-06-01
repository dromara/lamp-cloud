package top.tangyh.lamp.authority.dao.core;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.authority.entity.core.Station;
import top.tangyh.lamp.common.annotation.DataField;
import top.tangyh.lamp.common.annotation.DataScope;

/**
 * <p>
 * Mapper 接口
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Repository
public interface StationMapper extends SuperMapper<Station> {
    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page         分页
     * @param queryWrapper 参数包装器
     * @return 分页数据
     */
    @DataScope(value = {@DataField(alias = "s")})
    IPage<Station> findStationPage(IPage<Station> page, @Param(Constants.WRAPPER) Wrapper<Station> queryWrapper);
}
