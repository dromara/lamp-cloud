package com.github.zuihou.authority.service.common.impl;

import com.github.zuihou.authority.dao.common.ParameterMapper;
import com.github.zuihou.authority.entity.common.Parameter;
import com.github.zuihou.authority.service.common.ParameterService;
import com.github.zuihou.base.service.SuperCacheServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import static com.github.zuihou.common.constant.CacheKey.PARAMETER;

/**
 * <p>
 * 业务实现类
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @date 2020-02-05
 */
@Slf4j
@Service
@CacheConfig(cacheNames = PARAMETER)
public class ParameterServiceImpl extends SuperCacheServiceImpl<ParameterMapper, Parameter> implements ParameterService {

    @Override
    protected String getRegion() {
        return PARAMETER;
    }

    protected ParameterService currentProxy() {
        return ((ParameterService) AopContext.currentProxy());
    }
}
