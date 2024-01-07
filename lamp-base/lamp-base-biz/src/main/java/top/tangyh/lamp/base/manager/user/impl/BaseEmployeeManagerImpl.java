package top.tangyh.lamp.base.manager.user.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.model.cache.CacheKeyBuilder;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.lamp.base.entity.user.BaseEmployee;
import top.tangyh.lamp.base.manager.user.BaseEmployeeManager;
import top.tangyh.lamp.base.mapper.user.BaseEmployeeMapper;
import top.tangyh.lamp.base.vo.result.user.BaseEmployeeResultVO;
import top.tangyh.lamp.common.cache.base.user.EmployeeCacheKeyBuilder;

import java.util.List;

/**
 * <p>
 * 通用业务实现类
 * 员工
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 * @create [2021-10-18] [zuihou] [代码生成器生成]
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BaseEmployeeManagerImpl extends SuperCacheManagerImpl<BaseEmployeeMapper, BaseEmployee> implements BaseEmployeeManager {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new EmployeeCacheKeyBuilder();
    }

    @Override
    public List<BaseEmployeeResultVO> listEmployeeByUserId(Long userId) {
        return baseMapper.listEmployeeByUserId(userId);
    }

    @Override
    public IPage<BaseEmployeeResultVO> selectPageResultVO(IPage<BaseEmployee> page, Wrapper<BaseEmployee> wrapper) {
        return baseMapper.selectPageResultVO(page, wrapper);
    }

    @Override
    public BaseEmployee getEmployeeByUser(Long userId) {
        ArgumentAssert.notNull(userId, "用户id为空");
        return baseMapper.selectOne(Wraps.<BaseEmployee>lbQ().eq(BaseEmployee::getUserId, userId));
    }
}
