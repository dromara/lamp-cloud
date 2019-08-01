package com.github.zuihou.sms.service.impl;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.jobs.api.JobsTimingApi;
import com.github.zuihou.jobs.dto.XxlJobInfo;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.enumeration.TaskStatus;
import com.github.zuihou.sms.service.SmsTaskService;
import com.github.zuihou.sms.strategy.SmsContext;
import com.github.zuihou.utils.DateUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class SmsTaskServiceImpl extends ServiceImpl<SmsTaskMapper, SmsTask> implements SmsTaskService {
    @Resource
    private JobsTimingApi jobsTimingApi;
    @Autowired
    private SmsContext smsContext;

    @Override
    public SmsTask saveTask(SmsTask smsTask) {
        return send(smsTask, true);
    }

    @Override
    public void update(SmsTask smsTask) {
        send(smsTask, false);
    }

    /**
     * 具体的短信任务保存操作
     *
     * @param smsTask
     * @param isSave  保存/修改方法
     * @return
     */
    private SmsTask send(SmsTask smsTask, boolean isSave) {
        //0, 保存常用联系人
        if (StringUtils.isEmpty(smsTask.getTopic())) {
            smsTask.setTopic(StringUtils.substring(smsTask.getContext(), 0, 5));
        }
        //1， 初始化默认参数
        smsTask.setStatus(TaskStatus.WAITING);

        //2，保存or修改 短信任务
        if (isSave) {
            super.save(smsTask);
        } else {
            super.updateById(smsTask);
        }

        //3, 判断是否立即发送
        if (smsTask.getSendTime() == null) {
            smsContext.smsSend(smsTask.getId());
        } else {
            //推送定时任务
            jobsTimingApi.addTimingTask(XxlJobInfo.build(
                    "zuihou-jobs-server", DateUtils.localDateTime2Date(smsTask.getSendTime()), "smsSendJobHandler", String.valueOf(smsTask.getId())));
        }

        return smsTask;
    }
}
