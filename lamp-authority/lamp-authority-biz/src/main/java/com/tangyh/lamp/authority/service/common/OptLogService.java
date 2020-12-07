package com.tangyh.lamp.authority.service.common;

import com.tangyh.basic.base.service.SuperService;
import com.tangyh.basic.log.entity.OptLogDTO;
import com.tangyh.lamp.authority.dto.common.OptLogResult;
import com.tangyh.lamp.authority.entity.common.OptLog;

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
     * @param entity 操作日志
     * @return 是否成功
     */
    boolean save(OptLogDTO entity);

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);

    /**
     * 查询操作日志详情
     *
     * @param id id
     * @return 详情
     */
    OptLogResult getOptLogResultById(Long id);
}
