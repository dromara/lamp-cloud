package com.github.zuihou.authority.service.auth.impl;


import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.zuihou.authority.dao.auth.UserTokenMapper;
import com.github.zuihou.authority.entity.auth.UserToken;
import com.github.zuihou.authority.service.auth.UserTokenService;
import com.github.zuihou.authority.service.common.ParameterService;
import com.github.zuihou.base.service.SuperServiceImpl;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.common.constant.CacheKey;
import com.github.zuihou.common.constant.ParameterKey;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
@Slf4j
@Service

public class UserTokenServiceImpl extends SuperServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

    @Autowired
    private CacheChannel channel;
    @Autowired
    private ParameterService parameterService;

    /**
     * 1，ONLY_ONE_CLIENT: 一个用户在一个应用 只能登录一次（如一个用户只能在一个APP上登录，也只能在一个PC端登录，但能同时登录PC和APP）
     * 2，MANY: 用户可以任意登录： token -> userid
     * 3，ONLY_ONE: 一个用户只能登录一次
     *
     * @param model
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(UserToken model) {
        boolean bool = SqlHelper.retBool(baseMapper.insert(model));
        String loginPolicy = parameterService.getValue(ParameterKey.LOGIN_POLICY, ParameterKey.LoginPolicy.MANY.name());

        if (ParameterKey.LoginPolicy.ONLY_ONE.eq(loginPolicy)) {
            String userIdKey = CacheKey.buildKey(model.getCreateUser());
            CacheObject user = channel.get(CacheKey.USER_TOKEN, userIdKey);

            evictPreviousToken(user);
            channel.set(CacheKey.USER_TOKEN, userIdKey, model.getToken());
        } else if (ParameterKey.LoginPolicy.ONLY_ONE_CLIENT.eq(loginPolicy)) {
            String userIdKey = CacheKey.buildKey(model.getCreateUser(), model.getClientId());
            CacheObject user = channel.get(CacheKey.USER_CLIENT_TOKEN, userIdKey);

            evictPreviousToken(user);
            channel.set(CacheKey.USER_CLIENT_TOKEN, userIdKey, model.getToken());
        }

        // 设置新的Token
        String tokenKey = CacheKey.buildKey(model.getToken());
        channel.set(CacheKey.TOKEN_USER_ID, tokenKey, String.valueOf(model.getCreateUser()));
        return bool;
    }

    /**
     * 标记上一个token为被T状态
     *
     * @param user
     */
    private void evictPreviousToken(CacheObject user) {
        if (user.getValue() != null) {
            String previousToken = (String) user.getValue();
            channel.set(CacheKey.TOKEN_USER_ID, CacheKey.buildKey(previousToken), BizConstant.LOGIN_STATUS);
            super.remove(Wraps.<UserToken>lbQ().eq(UserToken::getToken, previousToken));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reset() {
        boolean bool = super.remove(null);
        channel.clear(CacheKey.USER_TOKEN);
        channel.clear(CacheKey.USER_CLIENT_TOKEN);
        channel.clear(CacheKey.TOKEN_USER_ID);
        return bool;
    }

}
