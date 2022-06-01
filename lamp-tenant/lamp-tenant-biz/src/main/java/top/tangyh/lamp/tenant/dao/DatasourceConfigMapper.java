package top.tangyh.lamp.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.tenant.entity.DatasourceConfig;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2020-08-21
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DatasourceConfigMapper extends SuperMapper<DatasourceConfig> {

}
