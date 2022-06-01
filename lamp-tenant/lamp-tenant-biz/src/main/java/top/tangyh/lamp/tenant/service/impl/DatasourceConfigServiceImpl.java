package top.tangyh.lamp.tenant.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.service.SuperServiceImpl;
import top.tangyh.lamp.tenant.dao.DatasourceConfigMapper;
import top.tangyh.lamp.tenant.entity.DatasourceConfig;
import top.tangyh.lamp.tenant.service.DatasourceConfigService;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2020-08-21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigService {


}
