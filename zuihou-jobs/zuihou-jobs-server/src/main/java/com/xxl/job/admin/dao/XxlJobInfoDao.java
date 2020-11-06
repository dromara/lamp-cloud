package com.xxl.job.admin.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.xxl.job.admin.core.model.XxlJobInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * job info
 *
 * @author xuxueli 2016-1-12 18:03:45
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface XxlJobInfoDao {

    List<XxlJobInfo> pageList(@Param("offset") Integer offset,
                              @Param("pagesize") Integer pagesize,
                              @Param("jobGroup") Integer jobGroup,
                              @Param("jobDesc") String jobDesc,
                              @Param("executorHandler") String executorHandler,
                              @Param("type") Integer type);

    int pageListCount(@Param("offset") Integer offset,
                      @Param("pagesize") Integer pagesize,
                      @Param("jobGroup") Integer jobGroup,
                      @Param("jobDesc") String jobDesc,
                      @Param("executorHandler") String executorHandler,
                      @Param("type") Integer type);

    int save(XxlJobInfo info);

    XxlJobInfo loadById(@Param("id") Integer id);

    int update(XxlJobInfo item);

    int delete(@Param("id") Integer id);

    List<XxlJobInfo> getJobsByGroup(@Param("jobGroup") Integer jobGroup);

    int findAllCount();

}
