package com.xxl.job.admin.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.xxl.job.admin.core.model.XxlJobLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 *
 * @author xuxueli 2016-1-12 18:03:06
 */
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
@Repository
public interface XxlJobLogDao {

    List<XxlJobLog> pageList(@Param("offset") Integer offset,
                             @Param("pagesize") Integer pagesize,
                             @Param("jobGroup") Integer jobGroup,
                             @Param("jobId") Integer jobId,
                             @Param("triggerTimeStart") Date triggerTimeStart,
                             @Param("triggerTimeEnd") Date triggerTimeEnd,
                             @Param("logStatus") Integer logStatus);

    int pageListCount(@Param("offset") Integer offset,
                      @Param("pagesize") Integer pagesize,
                      @Param("jobGroup") Integer jobGroup,
                      @Param("jobId") Integer jobId,
                      @Param("triggerTimeStart") Date triggerTimeStart,
                      @Param("triggerTimeEnd") Date triggerTimeEnd,
                      @Param("logStatus") Integer logStatus);

    XxlJobLog load(@Param("id") Integer id);

    int save(XxlJobLog xxlJobLog);

    int updateTriggerInfo(XxlJobLog xxlJobLog);

    int updateHandleInfo(XxlJobLog xxlJobLog);

    int delete(@Param("jobId") Integer jobId);

    int triggerCountByHandleCode(@Param("handleCode") Integer handleCode);

    List<Map<String, Object>> triggerCountByDay(@Param("from") Date from,
                                                @Param("to") Date to);

    int clearLog(@Param("jobGroup") Integer jobGroup,
                 @Param("jobId") Integer jobId,
                 @Param("clearBeforeTime") Date clearBeforeTime,
                 @Param("clearBeforeNum") Integer clearBeforeNum);

}
