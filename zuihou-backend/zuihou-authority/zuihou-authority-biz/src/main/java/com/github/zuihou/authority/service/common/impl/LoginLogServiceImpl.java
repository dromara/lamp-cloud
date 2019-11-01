package com.github.zuihou.authority.service.common.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.LoginLogMapper;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.LoginLogService;
import com.github.zuihou.common.constant.CacheKey;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
    @Autowired
    private UserService userService;
    @Autowired
    private CacheChannel cache;

    @Override
    public LoginLog save(String account, String ua, String ip, String location) {
        User user = userService.getByAccount(account);
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        LoginLog loginLog = LoginLog.builder()
                .account(account).location(location)
                .loginDate(LocalDate.now())
                .requestIp(ip).ua(ua).userName(account)
                .browser(browser.getName()).browserVersion(userAgent.getBrowserVersion().getVersion())
                .operatingSystem(operatingSystem.getName())
                .build();
        if (user != null) {
            loginLog.setUserId(user.getId()).setUserName(user.getName());
        }

        super.save(loginLog);


        LocalDate now = LocalDate.now();
        LocalDate tenDays = now.plusDays(-9);
        cache.evict(CacheKey.LOGIN_LOG_TOTAL, CacheKey.build());
        cache.evict(CacheKey.LOGIN_LOG_TODAY, CacheKey.build(now));
        cache.evict(CacheKey.LOGIN_LOG_TODAY_IP, CacheKey.build(now));
        cache.evict(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.build(tenDays, null));
        cache.evict(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.build(tenDays, account));
        cache.evict(CacheKey.LOGIN_LOG_BROWSER, CacheKey.build());
        cache.evict(CacheKey.LOGIN_LOG_SYSTEM, CacheKey.build());

        return loginLog;
    }

    @Override
    public Long findTotalVisitCount() {
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_TOTAL, CacheKey.build(), (key) -> this.baseMapper.findTotalVisitCount());
        return (Long) cacheObject.getValue();
    }

    @Override
    public Long findTodayVisitCount() {
        LocalDate now = LocalDate.now();
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_TODAY, CacheKey.build(now), (key) -> this.baseMapper.findTodayVisitCount(now));
        return (Long) cacheObject.getValue();
    }

    @Override
    public Long findTodayIp() {
        LocalDate now = LocalDate.now();
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_TODAY_IP, CacheKey.build(now), (key) -> this.baseMapper.findTodayIp(now));
        return (Long) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findLastTenDaysVisitCount(String account) {
        LocalDate tenDays = LocalDate.now().plusDays(-9);
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_TEN_DAY, CacheKey.build(tenDays, account), (key) -> this.baseMapper.findLastTenDaysVisitCount(tenDays, account));
        return (List<Map<String, Object>>) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findByBrowser() {
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_BROWSER, CacheKey.build(), (key) -> this.baseMapper.findByBrowser());
        return (List<Map<String, Object>>) cacheObject.getValue();
    }

    @Override
    public List<Map<String, Object>> findByOperatingSystem() {
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_LOG_SYSTEM, CacheKey.build(), (key) -> this.baseMapper.findByOperatingSystem());
        return (List<Map<String, Object>>) cacheObject.getValue();
    }
}
