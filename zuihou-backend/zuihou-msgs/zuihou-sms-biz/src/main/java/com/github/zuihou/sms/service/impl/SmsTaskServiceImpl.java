package com.github.zuihou.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.service.SmsTaskService;

import lombok.extern.slf4j.Slf4j;
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

}
