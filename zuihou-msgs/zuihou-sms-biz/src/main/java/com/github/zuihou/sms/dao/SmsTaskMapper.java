package com.github.zuihou.sms.dao;

import com.github.zuihou.base.mapper.SuperMapper;
import com.github.zuihou.sms.entity.SmsTask;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Repository
public interface SmsTaskMapper extends SuperMapper<SmsTask> {

}
