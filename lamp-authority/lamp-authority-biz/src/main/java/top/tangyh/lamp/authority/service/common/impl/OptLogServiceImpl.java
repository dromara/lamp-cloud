package top.tangyh.lamp.authority.service.common.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.log.OptLogDTO;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.authority.dao.common.OptLogExtMapper;
import top.tangyh.lamp.authority.dao.common.OptLogMapper;
import top.tangyh.lamp.authority.dto.common.OptLogResult;
import top.tangyh.lamp.authority.entity.common.OptLog;
import top.tangyh.lamp.authority.entity.common.OptLogExt;
import top.tangyh.lamp.authority.service.common.OptLogService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
@Slf4j
@Service

@RequiredArgsConstructor
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    private final OptLogExtMapper optLogExtMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(OptLogDTO entity) {
        OptLog optLog = BeanPlusUtil.toBean(entity, OptLog.class);
        OptLogExt optLogExt = BeanPlusUtil.toBean(entity, OptLogExt.class);
        boolean bool = super.save(optLog);
        optLogExt.setId(optLog.getId());
        optLogExtMapper.insert(optLogExt);
        return bool;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        if (clearBeforeNum != null) {
            Page<OptLog> page = super.page(new Page<>(0, clearBeforeNum), Wraps.<OptLog>lbQ().select(OptLog::getId).orderByDesc(OptLog::getCreateTime));
            List<Long> idList = page.getRecords().stream().map(OptLog::getId).collect(Collectors.toList());

            optLogExtMapper.clearLog(clearBeforeTime, idList);
            return baseMapper.clearLog(clearBeforeTime, idList) > 0;
        }
        optLogExtMapper.clearLog(clearBeforeTime, null);
        return baseMapper.clearLog(clearBeforeTime, null) > 0;
    }
    @Override
    @Transactional(readOnly = true)
    public OptLogResult getOptLogResultById(Long id) {
        OptLog opt = getById(id);
        OptLogResult result = BeanPlusUtil.toBean(opt, OptLogResult.class);
        OptLogExt optLogExt = optLogExtMapper.selectById(id);
        BeanPlusUtil.copyProperties(optLogExt, result);
        return result;
    }

}
