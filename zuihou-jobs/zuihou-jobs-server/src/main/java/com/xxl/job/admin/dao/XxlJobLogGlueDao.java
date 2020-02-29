package com.xxl.job.admin.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xxl.job.admin.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * job log for glue
 *
 * @author xuxueli 2016-5-19 18:04:56
 */
@SqlParser(filter = true)
@Repository
public interface XxlJobLogGlueDao {


    public int save(XxlJobLogGlue xxlJobLogGlue);

    public List<XxlJobLogGlue> findByJobId(@Param("jobId") Integer jobId);

    public int removeOld(@Param("jobId") Integer jobId, @Param("limit") Integer limit);

    public int deleteByJobId(@Param("jobId") Integer jobId);

}
