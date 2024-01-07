package top.tangyh.lamp.msg.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;

import top.tangyh.lamp.msg.entity.ExtendInterfaceLog;
import top.tangyh.lamp.msg.entity.ExtendInterfaceLogging;
import top.tangyh.lamp.msg.manager.ExtendInterfaceLogManager;
import top.tangyh.lamp.msg.manager.ExtendInterfaceLoggingManager;
import top.tangyh.lamp.msg.service.ExtendInterfaceLogService;

import java.util.Collection;

/**
 * <p>
 * 业务实现类
 * 接口执行日志
 * </p>
 *
 * @author zuihou
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [zuihou] [代码生成器生成]
 */

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExtendInterfaceLogServiceImpl extends SuperServiceImpl<ExtendInterfaceLogManager, Long, ExtendInterfaceLog> implements ExtendInterfaceLogService {
    private final ExtendInterfaceLoggingManager extendInterfaceLoggingManager;

    @Override
    public boolean removeByIds(Collection<Long> idList) {
        extendInterfaceLoggingManager.remove(Wraps.<ExtendInterfaceLogging>lbQ().in(
                ExtendInterfaceLogging::getLogId, idList
        ));
        return super.removeByIds(idList);
    }
}


