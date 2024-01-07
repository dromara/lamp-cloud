package top.tangyh.lamp.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.lamp.msg.entity.ExtendInterfaceLogging;
import top.tangyh.lamp.msg.manager.ExtendInterfaceLoggingManager;
import top.tangyh.lamp.msg.mapper.ExtendInterfaceLoggingMapper;

/**
 * <p>
 * 通用业务实现类
 * 接口执行日志记录
 * </p>
 *
 * @author zuihou
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ExtendInterfaceLoggingManagerImpl extends SuperManagerImpl<ExtendInterfaceLoggingMapper, ExtendInterfaceLogging> implements ExtendInterfaceLoggingManager {

}


