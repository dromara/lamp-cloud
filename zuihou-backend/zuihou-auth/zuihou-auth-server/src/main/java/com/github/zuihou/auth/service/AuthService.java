package com.github.zuihou.auth.service;


import com.github.zuihou.auth.common.jwt.TokenVo;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:41
 */
public interface AuthService {
    TokenVo login(String userName, String passWord) ;

    TokenVo refresh(String userName, String passWord) ;

    void validate(String token);

    void invalid(String token) ;
}
