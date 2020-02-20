package com.github.zuihou.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.github.zuihou.base.mapper.SuperMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基于MP的 IService 新增了4个方法： saveBatchSomeColumn、updateAllById、getByIdCache、removeByIdsCache
 * 其中：
 * 1，updateAllById 执行后，会清除缓存
 * 2，getByIdCache 方法 会先从缓存查询，后从DB查询
 * 3，removeByIdsCache 方法支持清除缓存
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
     * 根据id集合删除数据
     *
     * @param idList
     * @return
     */
    boolean removeByIdsCache(Collection<? extends Serializable> idList);

    /**
     * 根据id 先查缓存，再查db
     *
     * @param id
     * @return
     */
    T getByIdCache(Serializable id);
}
