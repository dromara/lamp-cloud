package com.xxl.job.admin.service;


import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.Date;
import java.util.Map;

/**
 * core job action for xxl-job
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface XxlJobService {

    /**
     * page list
     *
     * @param start
     * @param length
     * @param jobGroup
     * @param jobDesc
     * @param executorHandler
     * @param filterTime
     * @return
     */
    public Map<String, Object> pageList(Integer start, Integer length, Integer jobGroup, String jobDesc, String executorHandler, String filterTime, Integer type);

    /**
     * add job, default quartz stop
     *
     * @param jobInfo
     * @return
     */
    public ReturnT<String> add(XxlJobInfo jobInfo);

    public ReturnT<String> addStart(XxlJobInfo jobInfo);

    /**
     * update job, update quartz-cron if started
     *
     * @param jobInfo
     * @return
     */
    public ReturnT<String> update(XxlJobInfo jobInfo);

    /**
     * remove job, unbind quartz
     *
     * @param id
     * @return
     */
    public ReturnT<String> remove(Integer id);

    /**
     * start job, bind quartz
     *
     * @param id
     * @return
     */
    public ReturnT<String> start(Integer id);

    /**
     * stop job, unbind quartz
     *
     * @param id
     * @return
     */
    public ReturnT<String> stop(Integer id);

    /**
     * dashboard info
     *
     * @return
     */
    public Map<String, Object> dashboardInfo();

    /**
     * chart info
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate);

}
