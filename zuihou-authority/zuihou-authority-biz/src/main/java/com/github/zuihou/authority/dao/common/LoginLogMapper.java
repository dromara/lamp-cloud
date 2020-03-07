package com.github.zuihou.authority.dao.common;

import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.base.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Repository
public interface LoginLogMapper extends SuperMapper<LoginLog> {
    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     *
     * @param today 今天
     * @return Long
     */
    Long findTodayVisitCount(@Param("today") LocalDate today);

    /**
     * 获取系统今日访问 IP数
     *
     * @param today 今天
     * @return Long
     */
    Long findTodayIp(@Param("today") LocalDate today);

    /**
     * 获取系统近十天来的访问记录
     *
     * @param tenDays 10天前
     * @param account 用户账号
     * @return 系统近十天来的访问记录
     */
    List<Map<String, Object>> findLastTenDaysVisitCount(@Param("tenDays") LocalDate tenDays, @Param("account") String account);

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
    boolean clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("clearBeforeNum") Integer clearBeforeNum);
}
