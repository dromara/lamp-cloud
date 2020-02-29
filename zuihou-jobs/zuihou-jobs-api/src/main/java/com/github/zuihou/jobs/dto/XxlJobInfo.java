package com.github.zuihou.jobs.dto;

import com.github.zuihou.jobs.utils.Assert;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务
 *
 * @author xuxueli  2016-1-12 18:25:49
 */
@Data
@Accessors(chain = true)
public class XxlJobInfo {
    public static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();
    /**
     * 执行器主键ID	(JobKey.group)
     */
    private String jobGroupName;
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

    private XxlJobInfo(String jobGroupName, Date startExecuteTime, String executorHandler) {
        this.jobGroupName = jobGroupName;
        this.startExecuteTime = startExecuteTime;
        this.executorHandler = executorHandler;
    }

    private XxlJobInfo(String jobGroupName, Date startExecuteTime, String executorHandler, String executorParam) {
        this(jobGroupName, startExecuteTime, executorHandler);
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
        return (String) map.getOrDefault("name", "");
    }

    public static XxlJobInfo build(String jobGroupName, Date startExecuteTime, String executorHandler) {
        return new XxlJobInfo(jobGroupName, startExecuteTime, executorHandler)
                .setAuthor(getCurAuthor());
    }

    public static XxlJobInfo build(String jobGroupName, Date startExecuteTime, String executorHandler, String executorParam) {
        Assert.assertNotNull("发送时间能为空", startExecuteTime);
        Assert.assertNotNull("处理类不能为空", executorHandler);
        return new XxlJobInfo(jobGroupName, startExecuteTime, executorHandler, executorParam)
                .setAuthor(getCurAuthor());
    }

    public static XxlJobInfo build(String jobGroupName, LocalDateTime startExecuteTime, String executorHandler, String executorParam) {
        Assert.assertNotNull("发送时间能为空", startExecuteTime);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = startExecuteTime.atZone(zoneId);
        return build(jobGroupName, Date.from(zdt.toInstant()), executorHandler, executorParam);
    }

}
