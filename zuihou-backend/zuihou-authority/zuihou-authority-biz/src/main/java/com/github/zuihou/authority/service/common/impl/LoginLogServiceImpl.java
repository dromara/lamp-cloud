package com.github.zuihou.authority.service.common.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.authority.dao.common.LoginLogMapper;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.common.LoginLog;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.authority.service.common.LoginLogService;

import lombok.extern.slf4j.Slf4j;
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

    @Override
    public LoginLog save(String account, String ua, String ip, String location) {
        User user = userService.getByAccount(account);

        LoginLog loginLog = LoginLog.builder()
                .account(account).location(location)
                .loginTime(LocalDateTime.now())
                .requestIp(ip).ua(ua).userName(account)
                .build();
        if (user != null) {
            loginLog.setUserId(user.getId()).setUserName(user.getName());
        }
        super.save(loginLog);
        return loginLog;
    }
}
