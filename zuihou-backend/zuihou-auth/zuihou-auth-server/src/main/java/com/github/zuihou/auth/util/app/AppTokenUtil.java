package com.github.zuihou.auth.util.app;


import com.github.zuihou.auth.common.jwt.IJWTInfo;
import com.github.zuihou.auth.common.jwt.JWTHelper;
import com.github.zuihou.auth.common.jwt.TokenVo;
import com.github.zuihou.exception.BizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zuihou
 * @createTime 2017-12-14 8:58
 */
@Configuration
public class AppTokenUtil {
    @Value("${jwt.expire}")
    private int expire;
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;

    public TokenVo generateToken(IJWTInfo jwtInfo) throws BizException {
        return JWTHelper.generateToken(jwtInfo, priKeyPath, expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws BizException {
        return JWTHelper.getInfoFromToken(token, pubKeyPath);
    }
}