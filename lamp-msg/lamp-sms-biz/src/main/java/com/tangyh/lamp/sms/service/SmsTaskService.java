package com.tangyh.lamp.sms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tangyh.basic.base.request.PageParams;
import com.tangyh.basic.base.service.SuperService;
import com.tangyh.lamp.sms.dto.SmsTaskPageQuery;
import com.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import com.tangyh.lamp.sms.dto.SmsTaskUpdateDTO;
import com.tangyh.lamp.sms.entity.SmsTask;

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
     * @param smsTask 短信任务
     * @return 短信任务
     */
    SmsTask saveTask(SmsTaskSaveDTO smsTask);

    /**
     * 修改短信任务
     *
     * @param smsTask 短信任务
     * @return 短信任务
     */
    SmsTask update(SmsTaskUpdateDTO smsTask);

    /**
     * 自定义分页
     *
     * @param page  page 分页参数
     * @param query query 条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.tangyh.lamp.sms.entity.SmsTask>
     * @author tangyh
     * @date 2021/6/23 11:23 下午
     * @create [2021/6/23 11:23 下午 ] [tangyh] [初始创建]
     */
    IPage<SmsTask> pageSmsTask(IPage<SmsTask> page, PageParams<SmsTaskPageQuery> query);
}
