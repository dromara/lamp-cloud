package com.github.zuihou.authority.service.common;

import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.log.entity.OptLogDTO;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务接口
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-02
 */
public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return
     */
    boolean save(OptLogDTO entity);

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
