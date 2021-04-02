package com.tangyh.lamp.common.dto;

import com.tangyh.basic.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zuihou
 * @date 2021/1/5 9:23 上午
 */
public class XxlJobInfoVO implements Serializable {

    /**
     * 执行器 名
     */
    private String jobGroupName;
    private String jobDesc;

    /**
     * 负责人
     */
    private String author;
    /**
     * 报警邮件
     */
    private String alarmEmail;
    /**
     * 调度类型
     */
    private String scheduleType;
    /**
     * 调度配置，值含义取决于调度类型
     */
    private String scheduleConf;
    private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern(DateUtils.DEFAULT_DATE_TIME_FORMAT);
    /**
     * 调度过期策略
     */
    private String misfireStrategy;
    /**
     * 执行器路由策略
     */
    private String executorRouteStrategy;
    /**
     * 执行器，任务Handler名称
     */
    private String executorHandler;
    /**
     * 执行器，任务参数
     */
    private String executorParam;
    /**
     * 阻塞处理策略
     */
    private String executorBlockStrategy;
    /**
     * 任务执行超时时间，单位秒
     */
    private Integer executorTimeout;
    /**
     * 失败重试次数
     */
    private Integer executorFailRetryCount;

    /**
     * GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
     */
    private String glueType;
    /**
     * GLUE源代码
     */
    private String glueSource;
    /**
     * GLUE备注
     */
    private String glueRemark;
    /**
     * GLUE更新时间
     */
    private Date glueUpdatetime;
    /**
     * 子任务ID，多个逗号分隔
     */
    private String childJobId;
    /**
     * 调度时间
     */
    private String scheduleTime;

    public static XxlJobInfoVO create(String jobGroupName, String jobDesc,
                                      LocalDateTime scheduleTime, String executorHandler,
                                      String executorParam) {
        XxlJobInfoVO xxlJobInfo = new XxlJobInfoVO();
        xxlJobInfo.setJobGroupName(jobGroupName);
        xxlJobInfo.setJobDesc(jobDesc);
        xxlJobInfo.setAuthor("admin");
        xxlJobInfo.setAlarmEmail("");
        xxlJobInfo.setScheduleType("CRON");
        xxlJobInfo.setScheduleTime(scheduleTime.format(DTF));
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorRouteStrategy("FIRST");
        xxlJobInfo.setExecutorHandler(executorHandler);
        xxlJobInfo.setExecutorParam(executorParam);
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorTimeout(-1);
        xxlJobInfo.setExecutorFailRetryCount(-1);
        xxlJobInfo.setGlueType("BEAN");
        return xxlJobInfo;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public XxlJobInfoVO setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
        return this;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public XxlJobInfoVO setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
        return this;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public XxlJobInfoVO setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public XxlJobInfoVO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getAlarmEmail() {
        return alarmEmail;
    }

    public XxlJobInfoVO setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
        return this;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public XxlJobInfoVO setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
        return this;
    }

    public String getScheduleConf() {
        return scheduleConf;
    }

    public XxlJobInfoVO setScheduleConf(String scheduleConf) {
        this.scheduleConf = scheduleConf;
        return this;
    }

    public String getMisfireStrategy() {
        return misfireStrategy;
    }

    public XxlJobInfoVO setMisfireStrategy(String misfireStrategy) {
        this.misfireStrategy = misfireStrategy;
        return this;
    }

    public String getExecutorRouteStrategy() {
        return executorRouteStrategy;
    }

    public XxlJobInfoVO setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy;
        return this;
    }

    public String getExecutorHandler() {
        return executorHandler;
    }

    public XxlJobInfoVO setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
        return this;
    }

    public String getExecutorParam() {
        return executorParam;
    }

    public XxlJobInfoVO setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
        return this;
    }

    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    public XxlJobInfoVO setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
        return this;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public XxlJobInfoVO setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
        return this;
    }

    public Integer getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    public XxlJobInfoVO setExecutorFailRetryCount(Integer executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
        return this;
    }

    public String getGlueType() {
        return glueType;
    }

    public XxlJobInfoVO setGlueType(String glueType) {
        this.glueType = glueType;
        return this;
    }

    public String getGlueSource() {
        return glueSource;
    }

    public XxlJobInfoVO setGlueSource(String glueSource) {
        this.glueSource = glueSource;
        return this;
    }

    public String getGlueRemark() {
        return glueRemark;
    }

    public XxlJobInfoVO setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
        return this;
    }

    public Date getGlueUpdatetime() {
        return glueUpdatetime;
    }

    public XxlJobInfoVO setGlueUpdatetime(Date glueUpdatetime) {
        this.glueUpdatetime = glueUpdatetime;
        return this;
    }

    public String getChildJobId() {
        return childJobId;
    }

    public XxlJobInfoVO setChildJobId(String childJobId) {
        this.childJobId = childJobId;
        return this;
    }

}
