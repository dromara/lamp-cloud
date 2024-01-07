package top.tangyh.lamp.base.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.mapper.BaseEmployeeTestMapper;
import top.tangyh.lamp.common.cache.base.user.EmployeeCacheKeyBuilder;


/**
 * 仅测试使用
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/9/20 11:31 AM
 * @create [2022/9/20 11:31 AM ] [tangyh] [初始创建]
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BaseEmployeeTestServiceImpl extends SuperCacheManagerImpl<BaseEmployeeTestMapper, BaseEmployee> implements BaseEmployeeTestService {
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new EmployeeCacheKeyBuilder();
    }

    @Override
    public BaseEmployee get(Long id) {
        return baseMapper.get(id);
    }
}
