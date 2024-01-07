package top.tangyh.lamp.system.service.tenant;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.system.entity.tenant.DefDatasourceConfig;

/**
 * <p>
 * 业务接口
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
public interface DefDatasourceConfigService extends SuperService<Long, DefDatasourceConfig> {
    /**
     * 测试数据源链接
     *
     * @param id 数据源信息
     * @return
     */
    Boolean testConnection(Long id);
}
