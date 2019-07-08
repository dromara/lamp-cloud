package com.xxl.job.admin.dao;

import java.util.List;

import com.xxl.job.admin.core.model.XxlJobGroup;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xuxueli on 16/9/30.
 */
@Repository
public interface XxlJobGroupDao {

    public List<XxlJobGroup> findAll();

    public List<XxlJobGroup> findByAddressType(@Param("addressType") Integer addressType);

    public int save(XxlJobGroup xxlJobGroup);

    public int update(XxlJobGroup xxlJobGroup);

    public int remove(@Param("id") Integer id);

    public XxlJobGroup load(@Param("id") Integer id);

}
