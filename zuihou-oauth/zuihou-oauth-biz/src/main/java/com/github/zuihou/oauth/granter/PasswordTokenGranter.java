package com.github.zuihou.oauth.granter;

import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.jwt.model.AuthInfo;
import org.springframework.stereotype.Component;

import static com.github.zuihou.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

/**
 * 账号密码登录获取token
 *
 * @author zuihou
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}
