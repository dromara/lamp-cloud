package com.xxl.job.admin.core.trigger;

import com.xxl.job.admin.core.util.I18nUtil;

/**
 * trigger type enum
 * 触发器类型枚举
 *
 * @author xuxueli 2018-09-16 04:56:41
 */
public enum TriggerTypeEnum {

    /**
     * jobconf_trigger_type_cron=Cron触发
     * jobconf_trigger_type_timing=定时触发
     * jobconf_trigger_type_manual=手动触发
     * jobconf_trigger_type_parent=父任务触发
     * jobconf_trigger_type_api=API触发
     * jobconf_trigger_type_retry=失败重试触发
     */
    MANUAL(I18nUtil.getString("jobconf_trigger_type_manual")),
    /**
     * 定时触发，by zuihou
     */
    TIMING(I18nUtil.getString("jobconf_trigger_type_timing")),
    CRON(I18nUtil.getString("jobconf_trigger_type_cron")),
    RETRY(I18nUtil.getString("jobconf_trigger_type_retry")),
    PARENT(I18nUtil.getString("jobconf_trigger_type_parent")),
    API(I18nUtil.getString("jobconf_trigger_type_api"));

    private String title;

    private TriggerTypeEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
