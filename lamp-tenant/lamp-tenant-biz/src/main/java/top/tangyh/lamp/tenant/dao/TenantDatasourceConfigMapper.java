package top.tangyh.lamp.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.lamp.tenant.entity.TenantDatasourceConfig;
import org.springframework.stereotype.Repository;

/**
 * 租户数据源关系 Mapper
 *
 * @author zuihou
 * @date 2020/8/27 下午4:48
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantDatasourceConfigMapper extends SuperMapper<TenantDatasourceConfig> {
}
