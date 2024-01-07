package top.tangyh.lamp.msg.manager.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.lamp.msg.entity.ExtendInterfaceLog;
import top.tangyh.lamp.msg.manager.ExtendInterfaceLogManager;
import top.tangyh.lamp.msg.mapper.ExtendInterfaceLogMapper;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务实现类
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
public class ExtendInterfaceLogManagerImpl extends SuperManagerImpl<ExtendInterfaceLogMapper, ExtendInterfaceLog> implements ExtendInterfaceLogManager {
    @Override
    public ExtendInterfaceLog getByInterfaceId(Long interfaceId) {
        return getOne(Wraps.<ExtendInterfaceLog>lbQ().eq(ExtendInterfaceLog::getInterfaceId, interfaceId));
    }

    @Override
    public void incrSuccessCount(Long id) {
        baseMapper.incrSuccessCount(id, LocalDateTime.now());
    }

    @Override
    public void incrFailCount(Long id) {
        baseMapper.incrFailCount(id, LocalDateTime.now());
    }
}


