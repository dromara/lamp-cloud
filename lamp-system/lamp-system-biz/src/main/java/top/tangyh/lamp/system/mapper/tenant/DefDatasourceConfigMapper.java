package top.tangyh.lamp.system.mapper.tenant;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.springframework.stereotype.Repository;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.system.entity.tenant.DefDatasourceConfig;

/**
 * <p>
 * Mapper 接口
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DefDatasourceConfigMapper extends SuperMapper<DefDatasourceConfig> {

}
