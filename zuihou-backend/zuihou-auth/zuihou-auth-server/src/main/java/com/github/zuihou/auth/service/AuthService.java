package com.github.zuihou.auth.service;


import com.github.zuihou.auth.common.jwt.TokenVo;
import com.github.zuihou.auth.dto.TokenDTO;
import com.github.zuihou.exception.BizException;

/**
 * @author zuihou
 * @createTime 2017-12-15 13:41
 */
public interface AuthService {
    TokenVo login(String userName, String passWord) ;

    TokenDTO login(String userName) throws BizException;

    TokenVo refresh(String userName, String passWord) ;

    void validate(String token);

    void invalid(String token) ;
}
