package com.tangyh.lamp.authority.dao.common;

import com.tangyh.basic.base.mapper.SuperMapper;
import com.tangyh.lamp.authority.entity.common.LoginLog;
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
     * @return 总访问次数
     */
    Long getTotalLoginPv();

    /**
     * 获取系统今日访问次数
     *
     * @param today 今天
     * @return 今日访问次数
     */
    Long getTodayLoginPv(@Param("today") LocalDate today);

    /**
     * 获取系统今日 登录IV
     *
     * @param today 今天
     * @return 今日访问IP数
     */
    Long getTodayLoginIv(@Param("today") LocalDate today);

    /**
     * 获取系统 登录IV
     *
     * @return 今日访问IP数
     */
    Long getTotalLoginIv();

    /**
     * 获取系统近十天来的访问记录
     *
     * @param tenDays 10天前
     * @param account 用户账号
     * @return 系统近十天来的访问记录
     */
    List<Map<String, String>> findLastTenDaysVisitCount(@Param("tenDays") LocalDateTime tenDays, @Param("account") String account);

    /**
     * 按浏览器来统计数量
     *
     * @return 浏览器的数量
     */
    List<Map<String, Object>> findByBrowser();

    /**
     * 按操作系统来统计数量
     *
     * @return 操作系统的数量
     */
    List<Map<String, Object>> findByOperatingSystem();

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return 是否成功
     */
    Long clearLog(@Param("clearBeforeTime") LocalDateTime clearBeforeTime, @Param("clearBeforeNum") Integer clearBeforeNum);
}
