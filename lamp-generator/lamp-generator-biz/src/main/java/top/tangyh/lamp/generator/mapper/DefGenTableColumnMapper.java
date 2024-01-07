package top.tangyh.lamp.generator.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.generator.entity.DefGenTableColumn;

/**
 * <p>
 * Mapper 接口
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-01
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefGenTableColumnMapper extends SuperMapper<DefGenTableColumn> {

}
