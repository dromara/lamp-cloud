package com.github.zuihou.authority.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuihou.authority.entity.common.LoginLog;

/**
 * <p>
 * 业务接口
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 记录登录日志
     *
     * @param account 账号
     * @param ua 浏览器
     * @param ip 客户端IP
     * @param location 客户端地址
     * @return
     */
    LoginLog save(String account, String ua, String ip, String location);
}
