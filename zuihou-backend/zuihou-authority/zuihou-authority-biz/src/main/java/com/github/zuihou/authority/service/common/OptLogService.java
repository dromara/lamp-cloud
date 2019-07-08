package com.github.zuihou.authority.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.common.OptLog;
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
public interface OptLogService extends IService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return
     */
    boolean save(OptLogDTO entity);
}
