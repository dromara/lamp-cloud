package com.github.zuihou.base.dao;


import com.github.zuihou.base.entity.BaseEntity;
import com.github.zuihou.example.BaseExample;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author zuihou
 * @createTime 2017-12-08 17:37
 */
public interface BaseDao<I extends Serializable, T extends BaseEntity<I>, TE extends BaseExample> {

    int countByExample(TE example);//1

    int deleteByExample(TE example);//1

    int deleteByAppIdAndId(@Param("appId") String appId, @Param("id") I id);//1

    /**
     * add
     *
     * @param list
     * @return
     */
    int deleteByAppIdAndIds(@Param("appId") String appId, @Param("list") Collection<I> list);//1

    /**
     * add
     *
     * @param example
     * @return
     */
    int removeByExample(TE example);//1

    /**
     * add
     *
     * @param list
     * @return
     */
    int removeByAppIdAndIds(@Param("appId") String appId, @Param("list") Collection<I> list);//1

    /**
     * add
     *
     * @param id
     * @return
     */
    int removeByAppIdAndId(@Param("appId") String appId, @Param("id") I id);//1

    int insert(T record);//1

    int insertSelective(T record); //1

    void batchInsert(List<T> list);//1

    List<T> selectByExample(TE example); //1

    /**
     * 查询单一实体
     *
     * @param example
     * @return
     */
    T selectEntity(TE example);//1

    T selectByPrimaryKey(I id);//1

    /**
     * add
     *
     * @param appId
     * @param id
     * @return
     */
    T selectByAppIdAndId(@Param("appId") String appId, @Param("id") I id); //1

    int updateByExampleSelective(@Param("record") T record, @Param("example") TE example);//1

    int updateByExample(@Param("record") T record, @Param("example") TE example);//1

    int updateByAppIdAndIdSelective(T record);//1

    int updateByAppIdAndId(T record);//1


}
