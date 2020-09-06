package com.github.zuihou.authority.service.common.impl;

import cn.hutool.core.util.StrUtil;
import com.github.zuihou.authority.dao.common.LoginLogMapper;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.LoginLogService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.common.constant.CacheKey;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-10-20
 */
@Slf4j
@Service

public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Autowired
    private UserService userService;
    @Autowired
    private CacheChannel cache;

    private final static Supplier<Stream<String>> BROWSER = () -> Stream.of(
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    );
    private final static Supplier<Stream<String>> OPERATING_SYSTEM = () -> Stream.of(
            "Android", "Linux", "Mac OS X", "Ubuntu", "Windows 10", "Windows 8", "Windows 7", "Windows XP", "Windows Vista"
    );

    private static String simplifyOperatingSystem(String operatingSystem) {
        return OPERATING_SYSTEM.get().parallel().filter(b -> StrUtil.containsIgnoreCase(operatingSystem, b)).findAny().orElse(operatingSystem);
    }

    private static String simplifyBrowser(String browser) {
        return BROWSER.get().parallel().filter(b -> StrUtil.containsIgnoreCase(browser, b)).findAny().orElse(browser);
    }

    @Override
    public LoginLog save(Long userId, String account, String ua, String ip, String location, String description) {
        User user;
        if (userId != null) {
            user = this.userService.getByIdCache(userId);
        } else {
            user = this.userService.getByAccount(account);
        }

        LoginLog loginLog = LoginLog.builder()
                .location(location)
                .loginDate(LocalDate.now())
                .description(description)
                .requestIp(ip).ua(ua)
                .build();

        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        Version browserVersion = userAgent.getBrowserVersion();
        if (browser != null) {
            loginLog.setBrowser(simplifyBrowser(browser.getName()));
        }
        if (browserVersion != null) {
            loginLog.setBrowserVersion(browserVersion.getVersion());
        }
        if (operatingSystem != null) {
            loginLog.setOperatingSystem(simplifyOperatingSystem(operatingSystem.getName()));
        }
        if (user != null) {
            loginLog.setAccount(user.getAccount()).setUserId(user.getId()).setUserName(user.getName())
                    .setCreateUser(user.getId());
        }

        super.save(loginLog);
        LocalDate now = LocalDate.now();
        LocalDate tenDays = now.plusDays(-9);
        this.cache.evict(CacheKey.LOGIN_LOG_TOTAL, CacheKey.buildTenantKey());
        this.cache.evict(CacheKey.LOGIN_LOG_TODAY, CacheKey.buildTenantKey(now));
        this.cache.evict(CacheKey.LOGIN_LOG_TODAY_IP, CacheKey.buildTenantKey(now));
        this.cache.evict(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.buildTenantKey(tenDays, null));
        this.cache.evict(CacheKey.LOGIN_LOG_BROWSER, CacheKey.buildTenantKey());
        this.cache.evict(CacheKey.LOGIN_LOG_SYSTEM, CacheKey.buildTenantKey());
        if (user != null) {
            this.cache.evict(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.buildTenantKey(tenDays, user.getAccount()));
        }
        return loginLog;
    }

    @Override
    public Long findTotalVisitCount() {
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_TOTAL, CacheKey.buildTenantKey(), (key) -> this.baseMapper.findTotalVisitCount());
        return (Long) cacheObject.getValue();
    }

    @Override
    public Long findTodayVisitCount() {
        LocalDate now = LocalDate.now();
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_TODAY, CacheKey.buildTenantKey(now), (key) -> this.baseMapper.findTodayVisitCount(now));
        return (Long) cacheObject.getValue();
    }

    @Override
    public Long findTodayIp() {
        LocalDate now = LocalDate.now();
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_TODAY_IP, CacheKey.buildTenantKey(now), (key) -> this.baseMapper.findTodayIp(now));
        return (Long) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findLastTenDaysVisitCount(String account) {
        LocalDate tenDays = LocalDate.now().plusDays(-9);
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.buildTenantKey(tenDays, account), (key) -> this.baseMapper.findLastTenDaysVisitCount(tenDays, account));
        return (List<Map<String, Object>>) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_BROWSER, CacheKey.buildTenantKey(), (key) -> this.baseMapper.findByBrowser());
        return (List<Map<String, Object>>) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        CacheObject cacheObject = this.cache.get(CacheKey.LOGIN_LOG_SYSTEM, CacheKey.buildTenantKey(), (key) -> this.baseMapper.findByOperatingSystem());
        return (List<Map<String, Object>>) cacheObject.getValue();
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
