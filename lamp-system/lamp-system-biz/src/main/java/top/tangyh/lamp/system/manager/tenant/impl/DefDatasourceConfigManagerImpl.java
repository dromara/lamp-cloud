package top.tangyh.lamp.system.manager.tenant.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.lamp.system.entity.tenant.DefDatasourceConfig;
import top.tangyh.lamp.system.manager.tenant.DefDatasourceConfigManager;
import top.tangyh.lamp.system.mapper.tenant.DefDatasourceConfigMapper;

/**
 * 应用管理
 *
 * @author tangyh
 * @version v1.0
 * @date 2021/9/29 1:26 下午
 * @create [2021/9/29 1:26 下午 ] [tangyh] [初始创建]
 */
@RequiredArgsConstructor
@Service
public class DefDatasourceConfigManagerImpl extends SuperManagerImpl<DefDatasourceConfigMapper, DefDatasourceConfig> implements DefDatasourceConfigManager {
    @Override
    public DefDatasourceConfig getByName(String dsName) {
        return getOne(Wraps.<DefDatasourceConfig>lbQ().eq(DefDatasourceConfig::getName, dsName), false);
    }
}
