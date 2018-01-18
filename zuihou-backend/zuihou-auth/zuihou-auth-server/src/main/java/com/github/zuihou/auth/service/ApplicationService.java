package com.github.zuihou.auth.service;


import com.github.zuihou.auth.common.jwt.TokenVo;

/**
 * @author zuihou
 * @createTime 2017-12-13 23:04
 */
public interface ApplicationService {
    TokenVo applyToken(String appId, String appSecret) ;
}
