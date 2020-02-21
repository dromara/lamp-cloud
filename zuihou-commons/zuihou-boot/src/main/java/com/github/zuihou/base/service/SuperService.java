package com.github.zuihou.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.zuihou.base.mapper.SuperMapper;

import java.io.Serializable;
import java.util.List;

/**
 * 基于MP的 IService 新增了3个方法： saveBatchSomeColumn、updateAllById、getByIdCache
 * 其中：
 * 1，updateAllById 执行后，会清除缓存
 * 2，getByIdCache 方法 会先从缓存查询，后从DB查询
 *
 * @param <T> 实体
 */
public interface SuperService<T> extends IService<T> {

    /**
     * 批量保存数据
     *
     * @param entityList
     * @return
     */
    default boolean saveBatchSomeColumn(List<T> entityList) {
        if(entityList.isEmpty()){
            return true;
        }
        return SqlHelper.retBool(((SuperMapper) getBaseMapper()).insertBatchSomeColumn(entityList));
    }

    /**
     * 根据id修改数据
     *
     * @param entity
     * @return
     */
    boolean updateAllById(T entity);

    /**
     * 根据id 先查缓存，再查db
     *
     * @param id
     * @return
     */
    T getByIdCache(Serializable id);
}
