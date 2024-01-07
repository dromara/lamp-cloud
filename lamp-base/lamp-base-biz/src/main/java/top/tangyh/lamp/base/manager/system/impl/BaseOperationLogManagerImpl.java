package top.tangyh.lamp.base.manager.system.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.lamp.base.entity.system.BaseOperationLog;
import top.tangyh.lamp.base.manager.system.BaseOperationLogManager;
import top.tangyh.lamp.base.mapper.system.BaseOperationLogExtMapper;
import top.tangyh.lamp.base.mapper.system.BaseOperationLogMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 * @create [2021-11-08] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseOperationLogManagerImpl extends SuperManagerImpl<BaseOperationLogMapper, BaseOperationLog> implements BaseOperationLogManager {
    private final BaseOperationLogExtMapper baseOperationLogExtMapper;

    @Override
    public Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        List<Long> idList = Collections.emptyList();
        if (clearBeforeNum != null) {
            Page<BaseOperationLog> page = super.page(new Page<>(0, clearBeforeNum), Wraps.<BaseOperationLog>lbQ().select(BaseOperationLog::getId).orderByDesc(BaseOperationLog::getCreatedTime));
            idList = page.getRecords().stream().map(BaseOperationLog::getId).toList();
        }
        baseOperationLogExtMapper.clearLog(clearBeforeTime, idList);
        return baseMapper.clearLog(clearBeforeTime, idList);
    }

}
