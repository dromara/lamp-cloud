package com.github.zuihou.common.constant;

/**
 * 业务常量
 *
 * @author zuihou
 * @date 2019/08/06
 */
public interface BizConstant {
    /**
     * 演示用的组织ID
     */
    Long DEMO_ORG_ID = 101L;
    /**
     * 演示用的岗位ID
     */
    Long DEMO_STATION_ID = 101L;

    /**
     * 默认的定时任务组
     */
    String DEF_JOB_GROUP_NAME = "zuihou-jobs-server";
    /**
     * 短信发送处理器
     */
    String SMS_SEND_JOB_HANDLER = "smsSendJobHandler";

}
