package com.github.zuihou.sms.service;

import com.github.zuihou.base.service.SuperService;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.enumeration.TemplateCodeType;

/**
 * <p>
 * 业务接口
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
public interface SmsTaskService extends SuperService<SmsTask> {
    /**
     * 保存任务
     *
     * @param smsTask
     * @return
     */
    void saveTask(SmsTask smsTask, TemplateCodeType type);

    /**
     * 修改短信任务
     *
     * @param smsTask
     */
    void update(SmsTask smsTask);
}
