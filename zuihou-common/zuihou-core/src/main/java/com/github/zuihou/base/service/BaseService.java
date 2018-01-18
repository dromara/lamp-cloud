package com.github.zuihou.base.service;


import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;
import com.github.zuihou.exception.BizException;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-08 17:30
 */
public interface BaseService<I extends Serializable, T extends BaseEntity<I>,  TE extends BaseExample> {
    /**
     * id 生成器生成id
     * @return
     */
    I getId();

    /**
     * 保存
     * @param entity
     */
    T save(T entity);

    /**
     * 批量保存
     * @param list
     */
    Collection<T> save(Collection<T> list);

    /***
     * 有选择(不为null)的保存
     * @param entity
     * @return
     */
    T saveSelective(T entity);

    /**
     * 根据ID + AppId查找记录
     *
     * @param id
     */
    T getByAppIdAndId(String appId, I id);

    /**
     * 查询唯一结果
     * @param entity
     * @return
     */
    T getUnique(TE example);

    /**
     * 列表查询
     *
     * @param entity
     * @return
     */
    List<T> find(TE example);


    /**
     * 统计
     * @return
     */
    int count(TE example);


    /**
     * 更新
     *
     * @param entity
     * @return 影响行数
     */
    int updateByAppIdAndId(String appId, T entity);

    /**
     * 批量更新
     * @param list
     * @return 影响行数
     */
    int updateByAppIdAndId(String appId, Collection<T> entitys);

    /**
     * 更新非空字段的值
     * @param entity
     * @return 影响行数
     */
    int updateByAppIdAndIdSelective(String appId, T entity);

    /**
     * 批量更新非空字段的值
     * @param list
     * @return 影响行数
     */
    int updateByAppIdAndIdSelective(String appId, Collection<T> entitys);

    /**
     * 根据ID物理删除记录
     *
     * @param id
     */
    int deleteByAppIdAndId(String appId, I id);

    /**
     * 批量物理删除记录
     *
     * @param list
     * @return 影响行数
     */
    int deleteByAppIdAndIds(String appId, Collection<I> list);

    /**
     * 逻辑删除，[约定表中一定存在is_delete字段,不存在字段就报错]
     *
     * @param id
     * @return
     */
    int removeByAppIdAndId(String appId, I id) throws BizException;

    /**
     * 批量逻辑删除记录 [约定表中一定存在is_delete字段,不存在字段就报错]
     *
     * @param ids
     * @return 影响行数
     */
    int removeByAppIdAndIds(String appId, Collection<I> ids) throws BizException;
}
