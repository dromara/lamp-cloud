package com.tangyh.lamp.authority.service.common.impl;


import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.log.entity.OptLogDTO;
import com.tangyh.basic.utils.BeanPlusUtil;
import com.tangyh.lamp.authority.dao.common.OptLogExtMapper;
import com.tangyh.lamp.authority.dao.common.OptLogMapper;
import com.tangyh.lamp.authority.dto.common.OptLogResult;
import com.tangyh.lamp.authority.entity.common.OptLog;
import com.tangyh.lamp.authority.entity.common.OptLogExt;
import com.tangyh.lamp.authority.service.common.OptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum) > 0;
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
