package com.github.zuihou.authority.mananger;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.zuihou.auth.server.utils.JwtTokenServerUtils;
import com.github.zuihou.auth.utils.JwtUserInfo;
import com.github.zuihou.auth.utils.Token;
import com.github.zuihou.authority.dto.auth.LoginDTO;
import com.github.zuihou.authority.dto.auth.UserDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.service.auth.UserService;
import com.github.zuihou.dozer.DozerUtils;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.ExceptionCode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:42
 */
@Service
@Slf4j
public class AuthManager {
    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;
    @Autowired
    private UserService accountService;
    @Autowired
    private DozerUtils dozer;


    public LoginDTO login(String account, String password) {
        User user = getUser(account, password);
        Token token = getToken(user);
        log.info("account={}", account);
        return LoginDTO.builder().user(dozer.map(user, UserDTO.class)).token(token).build();
    }


    public Token generateToken(String account, String password) throws BizException {
        User user = getUser(account, password);

        Token token = getToken(user);
        return token;
    }

    private Token getToken(User user) {
        JwtUserInfo userInfo = null;

        userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), user.getOrgId(), user.getStationId());

        Token token = jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());
        return token;
    }

    private User getUser(String account, String password) {
        String passwordMd5 = DigestUtils.md5Hex(password);
        User user = accountService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getAccount, account)
                .eq(User::getIsDelete, false));
        if (user == null || !user.getPassword().equalsIgnoreCase(passwordMd5)) {
            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
        }
        if (user.getIsCanLogin() == null || !user.getIsCanLogin()) {
            throw new BizException(ExceptionCode.JWT_USER_ENABLED.getCode(), ExceptionCode.JWT_USER_ENABLED.getMsg());
        }
        return user;
    }

    public JwtUserInfo validateUserToken(String token) throws BizException {
        return jwtTokenServerUtils.getUserInfo(token);
    }

    public void invalidUserToken(String token) throws BizException {

    }

}
