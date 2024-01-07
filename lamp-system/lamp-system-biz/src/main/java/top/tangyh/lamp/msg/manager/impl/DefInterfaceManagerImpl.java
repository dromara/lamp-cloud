package top.tangyh.lamp.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.lamp.msg.entity.DefInterface;
import top.tangyh.lamp.msg.manager.DefInterfaceManager;
import top.tangyh.lamp.msg.mapper.DefInterfaceMapper;

/**
 * <p>
 * 通用业务实现类
 * 接口
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 16:45:45
 * @create [2022-07-04 16:45:45] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DefInterfaceManagerImpl extends SuperManagerImpl<DefInterfaceMapper, DefInterface> implements DefInterfaceManager {
    @Override
    public DefInterface getByType(String type) {
        return getOne(Wraps.<DefInterface>lbQ().eq(DefInterface::getCode, type));
    }
}


