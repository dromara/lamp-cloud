package com.tangyh.lamp.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;

import com.tangyh.basic.base.service.SuperCacheServiceImpl;
import com.tangyh.basic.cache.model.CacheKey;
import com.tangyh.basic.cache.model.CacheKeyBuilder;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.tangyh.lamp.authority.dao.auth.ApplicationMapper;
import com.tangyh.lamp.authority.entity.auth.Application;
import com.tangyh.lamp.authority.service.auth.ApplicationService;
import com.tangyh.lamp.common.cache.auth.ApplicationCacheKeyBuilder;
import com.tangyh.lamp.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
@Slf4j
@Service

public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }

    @Override
    public Application getByClient(String clientId, String clientSecret) {
        LbqWrapper<Application> wrapper = Wraps.<Application>lbQ()
                .select(Application::getId).eq(Application::getClientId, clientId).eq(Application::getClientSecret, clientSecret);
        Function<CacheKey, Object> loader = k -> super.getObj(wrapper, Convert::toLong);
        CacheKey cacheKey = new ApplicationClientCacheKeyBuilder().key(clientId, clientSecret);
        return getByKey(cacheKey, loader);
    }

}
