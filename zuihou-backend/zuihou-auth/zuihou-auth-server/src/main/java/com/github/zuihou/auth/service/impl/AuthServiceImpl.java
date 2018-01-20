package com.github.zuihou.auth.service.impl;


import com.github.zuihou.admin.entity.account.po.Admin;
import com.github.zuihou.admin.repository.account.service.AdminService;
import com.github.zuihou.auth.common.jwt.JWTInfo;
import com.github.zuihou.auth.common.jwt.TokenVo;
import com.github.zuihou.auth.service.AuthService;
import com.github.zuihou.auth.util.client.JwtTokenUtil;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:42
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AdminService adminService;

    @Override
    public TokenVo login(String userName, String passWord) throws BizException {
        if (userName == null || passWord == null
                || userName.isEmpty() || passWord.isEmpty()) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        Admin admin = adminService.get(userName, passWord);
        if (admin == null) {
            throw new BizException(ExceptionCode.USER_NAME_PWD_ERROR.getCode(), ExceptionCode.USER_NAME_PWD_ERROR.getMsg());
        }
        return jwtTokenUtil.generateToken(new JWTInfo(admin.getUsername(), admin.getId(), admin.getName(), admin.getAppId()));
    }

    @Override
    public TokenVo refresh(String userName, String passWord) throws BizException {
        return null;
    }

    @Override
    public void validate(String token) throws BizException {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public void invalid(String token) throws BizException {

    }
}
