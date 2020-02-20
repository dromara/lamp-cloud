package com.github.zuihou.base.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.zuihou.base.mapper.SuperMapper;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.exception.code.ExceptionCode;
import com.github.zuihou.utils.StrPool;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基于SpringCache + J2Cache 实现的 缓存实现
 * key规则： CacheConfig#cacheNames:root.targetClass.simpleName:id
 *
 * @param <M>
 * @param <T>
 */
@CacheConfig(cacheNames = SuperServiceCacheImpl.CACHE_NAMES)
public class SuperServiceCacheImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements SuperService<T> {

    protected final static String CACHE_NAMES = "default";

    @Autowired
    protected CacheChannel cacheChannel;

    /**
     * 构建没有租户信息的key
     *
     * @param args
     * @return
     */
    protected static String buildKey(Object... args) {
        if (args.length == 1) {
            return String.valueOf(args[0]);
        } else if (args.length > 0) {
            return StrUtil.join(StrPool.COLON, args);
        } else {
            return "";
        }
    }

    /**
     * 缓存的 region
     * 记得重写该类！
     *
     * @return
     */
    protected String getRegion() {
        return CACHE_NAMES;
    }

    /**
     * 获取当前执行类的简单名称
     * 记得重写该类！
     *
     * @return
     */
    protected String getClassTypeName() {
        return SuperServiceCacheImpl.class.getSimpleName();
    }

    /**
     * 构建key
     *
     * @param args
     * @return
     */
    protected String key(Object... args) {
        return buildKey(getClassTypeName(), args);
    }

    @Override
    public boolean save(T model) {
        boolean bool = super.save(model);
        if (bool) {
            if (!handlerSave(model)) {
                throw BizException.wrap(ExceptionCode.DATA_SAVE_ERROR);
            }
        }
        return bool;
    }

    /**
     * 处理新增相关处理
     *
     * @param model
     * @return
     */
    protected boolean handlerSave(T model) {
        return true;
    }


    @Override
    @Cacheable(key = "#root.targetClass.typeName + ':'+#id")
    public T getByIdCache(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(key = "#root.targetClass.typeName + ':'+#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIdsCache(Collection<? extends Serializable> idList) {
        if (CollUtil.isEmpty(idList)) {
            return true;
        }
        boolean flag = super.removeByIds(idList);

        String[] keys = idList.stream().map(id -> key(id)).toArray(String[]::new);
        cacheChannel.evict(getRegion(), keys);
        return flag;
    }


    @Override
    @CacheEvict(key = "#root.targetClass.typeName + ':'+#p0.id")
    public boolean updateAllById(T model) {
        boolean bool = SqlHelper.retBool(((SuperMapper) getBaseMapper()).updateAllById(model));

        if (bool) {
            if (!handlerUpdateAllById(model)) {
                throw BizException.wrap(ExceptionCode.DATA_UPDATE_ERROR);
            }
        }
        return bool;
    }

    @Override
    @CacheEvict(key = "#root.targetClass.typeName + ':'+#p0.id")
    public boolean updateById(T model) {
        boolean bool = super.updateById(model);
        if (bool) {
            if (!handlerUpdateById(model)) {
                throw BizException.wrap(ExceptionCode.DATA_UPDATE_ERROR);
            }
        }
        return bool;
    }

    /**
     * 处理修改相关处理
     *
     * @param model
     * @return
     */
    protected boolean handlerUpdateAllById(T model) {
        return true;
    }

    /**
     * 处理修改相关处理
     *
     * @param model
     * @return
     */
    protected boolean handlerUpdateById(T model) {
        return true;
    }

}
