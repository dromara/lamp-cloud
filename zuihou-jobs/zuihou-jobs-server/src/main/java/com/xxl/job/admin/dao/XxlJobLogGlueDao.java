package com.xxl.job.admin.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.xxl.job.admin.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * job log for glue
 *
 * @author xuxueli 2016-5-19 18:04:56
 */
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
@Repository
public interface XxlJobLogGlueDao {

    int save(XxlJobLogGlue xxlJobLogGlue);

    List<XxlJobLogGlue> findByJobId(@Param("jobId") Integer jobId);

    int removeOld(@Param("jobId") Integer jobId, @Param("limit") Integer limit);

    int deleteByJobId(@Param("jobId") Integer jobId);

}
