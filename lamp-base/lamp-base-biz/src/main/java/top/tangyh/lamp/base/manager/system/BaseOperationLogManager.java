package top.tangyh.lamp.base.manager.system;

import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.lamp.base.entity.system.BaseOperationLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 通用业务接口
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
public interface BaseOperationLogManager extends SuperManager<BaseOperationLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
