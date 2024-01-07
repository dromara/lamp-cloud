package top.tangyh.lamp.base.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.annotation.database.TenantLine;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.base.entity.user.BaseEmployee;


/**
 * 仅仅测试使用
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Repository
@TenantLine
public interface BaseEmployeeTestMapper extends SuperMapper<BaseEmployee> {
    /**
     * get
     *
     * @param id id
     * @return top.tangyh.lamp.base.entity.user.BaseEmployee
     * @author tangyh
     * @date 2022/10/28 4:38 PM
     */
    @TenantLine(false)
    @Select("select * from base_employee where id = #{id}")
    BaseEmployee get(Long id);

}
