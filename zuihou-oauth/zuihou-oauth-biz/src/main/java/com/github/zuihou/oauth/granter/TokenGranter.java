package com.github.zuihou.oauth.granter;


import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.jwt.model.AuthInfo;

/**
 * 授权认证统一接口.
 *
 * @author zuihou
 * @date 2020年03月31日10:21:21
 */
public interface TokenGranter {

    /**
     * 获取用户信息
     *
     * @param loginParam 授权参数
     * @return LoginDTO
     */
    R<AuthInfo> grant(LoginParamDTO loginParam);

}
