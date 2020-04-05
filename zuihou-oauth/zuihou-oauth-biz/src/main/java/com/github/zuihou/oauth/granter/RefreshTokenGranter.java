package com.github.zuihou.oauth.granter;

import com.github.zuihou.authority.dto.auth.LoginParamDTO;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.event.LoginEvent;
import com.github.zuihou.authority.event.model.LoginStatusDTO;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.jwt.model.AuthInfo;
import com.github.zuihou.utils.SpringUtils;
import com.github.zuihou.utils.StrHelper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.github.zuihou.oauth.granter.RefreshTokenGranter.GRANT_TYPE;

/**
 * RefreshTokenGranter
 *
 * @author zuihou
 * @date 2020年03月31日10:23:53
 */
@Component(GRANT_TYPE)
public class RefreshTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "refresh_token";

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        String grantType = loginParam.getGrantType();
        String refreshTokenStr = loginParam.getRefreshToken();
        if (StrHelper.isAnyBlank(grantType, refreshTokenStr) || !GRANT_TYPE.equals(grantType)) {
            return R.fail("加载用户信息失败");
        }

        AuthInfo authInfo = tokenUtil.parseJWT(refreshTokenStr);

        if (!BaseContextConstants.REFRESH_TOKEN_KEY.equals(authInfo.getTokenType())) {
            return R.fail("refreshToken无效，无法加载用户信息");
        }

        User user = userService.getByIdCache(authInfo.getUserId());

        if (user == null || !user.getStatus()) {
            String msg = "您已被禁用！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        return R.success(createToken(user));

    }
}
