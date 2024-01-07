package top.tangyh.lamp.system.service.application;

import top.tangyh.basic.base.service.SuperService;
import top.tangyh.lamp.system.entity.application.DefUserApplication;

/**
 * <p>
 * 业务接口
 * 用户的默认应用
 * </p>
 *
 * @author zuihou
 * @date 2022-03-06
 */
public interface DefUserApplicationService extends SuperService<Long, DefUserApplication> {

    /**
     * 查询用户设置的默认应用
     *
     * @param userId 用户id
     * @return
     */
    Long getMyDefAppByUserId(Long userId);
}
