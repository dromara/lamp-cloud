package com.github.zuihou.authority.service.common;

import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.log.entity.OptLogDTO;

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
}
