package com.github.zuihou.authority.service.common;

import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.base.service.SuperService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务接口
 * 系统日志
 *
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
public interface LoginLogService extends SuperService<LoginLog> {

//    /**
//     * 记录登录日志
//     *
//     * @param account     账号
//     * @param ua          浏览器
//     * @param ip          客户端IP
//     * @param location    客户端地址
//     * @param description 登陆描述消息
//     * @return
//     */
//    LoginLog save(String account, String ua, String ip, String location, String description);

    /**
     * 记录登录日志
     *
     * @param userId      用户id
     * @param account     账号
     * @param ua          浏览器
     * @param ip          客户端IP
     * @param location    客户端地址
     * @param description 登陆描述消息
     * @return
     */
    LoginLog save(Long userId, String account, String ua, String ip, String location, String description);

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @return Long
     */
    Long findTodayVisitCount();

    /**
     * 获取系统今日访问 IP数
     *
     * @return Long
     */
    Long findTodayIp();


    /**
     * 获取系统近十天来的访问记录
     *
     * @param account 账号
     * @return 系统近十天来的访问记录
     */
    List<Map<String, Object>> findLastTenDaysVisitCount(String account);


    /**
     * 按浏览器来统计数量
     *
     * @return
     */
    List<Map<String, Object>> findByBrowser();

    /**
     * 按造作系统内统计数量
     *
     * @return
     */
    List<Map<String, Object>> findByOperatingSystem();

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
