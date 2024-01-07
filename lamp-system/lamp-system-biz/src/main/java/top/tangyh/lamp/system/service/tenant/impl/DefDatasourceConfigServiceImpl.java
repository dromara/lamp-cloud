package top.tangyh.lamp.system.service.tenant.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.lamp.system.entity.tenant.DefDatasourceConfig;
import top.tangyh.lamp.system.manager.tenant.DefDatasourceConfigManager;
import top.tangyh.lamp.system.service.tenant.DefDatasourceConfigService;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Slf4j
@Service

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefDatasourceConfigServiceImpl extends SuperServiceImpl<DefDatasourceConfigManager, Long, DefDatasourceConfig>
        implements DefDatasourceConfigService {

    @Override
    public Boolean testConnection(Long id) {
        return true;
    }
}
