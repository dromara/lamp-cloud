package com.github.zuihou.jobs.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.github.zuihou.jobs.utils.Assert;

import lombok.Data;

/**
 * 任务
 *
 * @author xuxueli  2016-1-12 18:25:49
 */
@Data
public class XxlJobInfo {
    public static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();
    /**
     * 执行器主键ID	(JobKey.group)
     */
    private Integer jobGroup = 1;
    /**
     * 开始执行时间
     */
    private Date startExecuteTime;
    /**
     * 结束执行时间
     */
    private Date endExecuteTime;
    /**
     * 执行间隔(s)，  在execTime指定的时间开始执行，并且每间隔【intervalInSeconds】秒，执行1次, 一直循环执行，总共只能执行【execCount】次
     */
    private Integer intervalSeconds = 0;
    /**
     * 重复次数 -1： 永不停息的执行（RepeatCount=-1）， 0：执行0次（RepeatCount=0），  N： 执行N次（RepeatCount=N）
     */
    private Integer repeatCount = 0;
    /**
     *
     */
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
     * 执行器，任务Handler名称
     */
    private String executorHandler;
    /**
     * 执行器，任务参数
     */
    private String executorParam;

    private XxlJobInfo(int jobGroup, Date startExecuteTime, String executorHandler) {
        this.jobGroup = jobGroup;
        this.startExecuteTime = startExecuteTime;
        this.executorHandler = executorHandler;
    }

    private XxlJobInfo(int jobGroup, Date startExecuteTime, String executorHandler, String executorParam) {
        this(jobGroup, startExecuteTime, executorHandler);
        this.executorParam = executorParam;
    }

    private static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    private static String getCurAuthor() {
        Map<String, Object> map = getLocalMap();
        return (String) map.getOrDefault("account", "");
    }

    public static XxlJobInfo build(int jobGroup, Date startExecuteTime, String executorHandler) {
        return new XxlJobInfo(jobGroup, startExecuteTime, executorHandler)
                .setAuthor(getCurAuthor());
    }

    public static XxlJobInfo build(int jobGroup, Date startExecuteTime, String executorHandler, String executorParam) {
        Assert.assertNotNull("发送时间能为空", startExecuteTime);
        Assert.assertNotNull("处理类不能为空", executorHandler);
        return new XxlJobInfo(jobGroup, startExecuteTime, executorHandler, executorParam)
                .setAuthor(getCurAuthor());
    }

    public static XxlJobInfo build(int jobGroup, LocalDateTime startExecuteTime, String executorHandler, String executorParam) {
        Assert.assertNotNull("发送时间能为空", startExecuteTime);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = startExecuteTime.atZone(zoneId);
        return build(jobGroup, Date.from(zdt.toInstant()), executorHandler, executorParam);
    }

    public XxlJobInfo setEndExecuteTime(Date endExecuteTime) {
        this.endExecuteTime = endExecuteTime;
        return this;
    }

    public XxlJobInfo setIntervalSeconds(Integer intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
        return this;
    }

    public XxlJobInfo setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
        return this;
    }

    public XxlJobInfo setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
        return this;
    }

    public XxlJobInfo setAuthor(String author) {
        this.author = author;
        return this;
    }

    public XxlJobInfo setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
        return this;
    }

    public XxlJobInfo setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
        return this;
    }


}
