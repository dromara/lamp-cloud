package top.tangyh.lamp.system.service.system;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.system.entity.system.DefLoginLog;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务接口
 * 登录日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-12
 */
public interface DefLoginLogService extends SuperService<Long, DefLoginLog> {
    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
