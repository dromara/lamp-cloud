package com.github.zuihou.authority.service.common.impl;

import com.github.zuihou.authority.dao.common.OptLogMapper;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.utils.BeanPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(BeanPlusUtil.toBean(entity, OptLog.class));
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
