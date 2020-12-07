package com.tangyh.lamp.authority.service.common.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.repository.CacheOps;
import com.tangyh.basic.utils.CollHelper;
import com.tangyh.basic.utils.DateUtils;
import com.tangyh.lamp.authority.dao.common.LoginLogMapper;
import com.tangyh.lamp.authority.entity.auth.User;
import com.tangyh.lamp.authority.entity.common.LoginLog;
import com.tangyh.lamp.authority.service.auth.UserService;
import com.tangyh.lamp.authority.service.common.LoginLogService;
import com.tangyh.lamp.common.cache.common.LoginLogBrowserCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.LoginLogSystemCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.LoginLogTenDayCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.LoginLogTodayCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.LoginLogTodayIpCacheKeyBuilder;
import com.tangyh.lamp.common.cache.common.LoginLogTotalCacheKeyBuilder;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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

@RequiredArgsConstructor
public class LoginLogServiceImpl extends SuperServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    private final UserService userService;
    private final CacheOps cacheOps;

    private static final Supplier<Stream<String>> BROWSER = () -> Stream.of(
            "Chrome", "Firefox", "Microsoft Edge", "Safari", "Opera"
    );
    private static final Supplier<Stream<String>> OPERATING_SYSTEM = () -> Stream.of(
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
                .loginDate(DateUtils.formatAsDate(LocalDateTime.now()))
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
                    .setCreatedBy(user.getId());
        }

        super.save(loginLog);
        LocalDate now = LocalDate.now();
        String nowStr = DateUtils.formatAsDate(now);
        String tenDaysAgo = DateUtils.formatAsDate(now.plusDays(-9));

        CacheKey loginLogTotalKey = new LoginLogTotalCacheKeyBuilder().key();
        CacheKey loginLogTodayKey = new LoginLogTodayCacheKeyBuilder().key(nowStr);
        CacheKey loginLogTodayIpKey = new LoginLogTodayIpCacheKeyBuilder().key(nowStr);
        CacheKey loginLogTenDayKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgo);
        CacheKey loginLogBrowserKey = new LoginLogBrowserCacheKeyBuilder().key();
        CacheKey loginLogSystemKey = new LoginLogSystemCacheKeyBuilder().key();
        cacheOps.del(loginLogTotalKey);
        cacheOps.del(loginLogTodayKey);
        cacheOps.del(loginLogTodayIpKey);
        cacheOps.del(loginLogTenDayKey);
        cacheOps.del(loginLogBrowserKey);
        cacheOps.del(loginLogSystemKey);
        if (user != null) {
            CacheKey loginLogTenDayUserKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgo, user.getAccount());
            cacheOps.del(loginLogTenDayUserKey);
        }
        return loginLog;
    }

    @Override
    public Long getTotalVisitCount() {
        CacheKey loginLogTotalKey = new LoginLogTotalCacheKeyBuilder().key();
        return Convert.toLong(cacheOps.get(loginLogTotalKey, key -> this.baseMapper.selectTotalVisitCount()), 0L);
    }

    @Override
    public Long getTodayVisitCount() {
        LocalDate now = LocalDate.now();
        CacheKey loginLogTodayKey = new LoginLogTodayCacheKeyBuilder().key(now);
        return Convert.toLong(cacheOps.get(loginLogTodayKey, k -> this.baseMapper.selectTodayVisitCount(now)), 0L);
    }


    @Override
    public Long getTodayIp() {
        LocalDate now = LocalDate.now();
        CacheKey loginLogTodayIpKey = new LoginLogTodayIpCacheKeyBuilder().key(now);
        return Convert.toLong(cacheOps.get(loginLogTodayIpKey, k -> this.baseMapper.selectTodayIp(now)), 0L);
    }

    @Override
    public List<Map<String, String>> findLastTenDaysVisitCount(String account) {
        LocalDateTime tenDaysAgo = LocalDateTime.of(LocalDate.now().plusDays(-9), LocalTime.MIN);
        String tenDaysAgoStr = DateUtils.formatAsDate(tenDaysAgo);
        CacheKey loginLogTenDayKey = new LoginLogTenDayCacheKeyBuilder().key(tenDaysAgoStr, account);
        return cacheOps.get(loginLogTenDayKey, k -> {
            List<Map<String, String>> map = baseMapper.findLastTenDaysVisitCount(tenDaysAgo, account);
            return map.stream().map(item -> {
                Map<String, String> kv = new HashMap<>(CollHelper.initialCapacity(map.size()));
                kv.put("login_date", item.get("login_date"));
                kv.put("count", item.get("count"));
                return kv;
            }).collect(Collectors.toList());
        });
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        CacheKey loginLogBrowserKey = new LoginLogBrowserCacheKeyBuilder().key();
        return cacheOps.get(loginLogBrowserKey, k -> baseMapper.findByBrowser());
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        CacheKey loginLogSystemKey = new LoginLogSystemCacheKeyBuilder().key();
        return cacheOps.get(loginLogSystemKey, k -> baseMapper.findByOperatingSystem());
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum) > 0;
    }
}
