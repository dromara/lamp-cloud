package com.github.zuihou.authority.service.auth;

import com.github.zuihou.authority.entity.auth.UserToken;
import com.github.zuihou.base.service.SuperService;

/**
 * <p>
 * 业务接口
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
public interface UserTokenService extends SuperService<UserToken> {
    /**
     * 重置用户登录
     *
     * @return
     */
    boolean reset();
}
