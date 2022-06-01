package top.tangyh.lamp.tenant.service.impl;


import top.tangyh.basic.base.service.SuperServiceImpl;

import top.tangyh.lamp.tenant.dao.TenantDatasourceConfigMapper;
import top.tangyh.lamp.tenant.entity.TenantDatasourceConfig;
import top.tangyh.lamp.tenant.service.TenantDatasourceConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 租户数据源关系
 *
 * @author zuihou
 * @date 2020/8/27 下午4:51
 */
@Slf4j
@Service
public class TenantDatasourceConfigServiceImpl extends SuperServiceImpl<TenantDatasourceConfigMapper, TenantDatasourceConfig> implements TenantDatasourceConfigService {
}
