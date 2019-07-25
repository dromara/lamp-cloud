package com.xxl.job.admin.core.trigger;

/**
 * 任务类型
 *
 * @author zuihou
 * @date 2018/11/23
 */
public enum JobTypeEnum {
    /**
     * 表达式定时
     */
    CRON(1, "CRON"),

    /**
     * 非表达式-普通指定时间定时
     */
    TIMES(2, "定时");
    private int code;
    private String title;

    JobTypeEnum(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public boolean eq(Integer type) {
        if (type != null && type == this.code) {
            return true;
        }
        return false;
    }
}
