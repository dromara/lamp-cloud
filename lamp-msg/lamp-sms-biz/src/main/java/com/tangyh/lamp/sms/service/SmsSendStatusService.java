package com.tangyh.lamp.sms.service;

import com.tangyh.basic.base.service.SuperService;
import com.tangyh.lamp.sms.entity.SmsSendStatus;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
public interface SmsSendStatusService extends SuperService<SmsSendStatus> {

    /**
     * 根据任务id查询待发送数据
     *
     * @param id 任务id
     * @return java.util.List<com.tangyh.lamp.sms.entity.SmsSendStatus>
     * @author tangyh
     * @date 2021/6/23 9:28 下午
     * @create [2021/6/23 9:28 下午 ] [tangyh] [初始创建]
     */
    List<SmsSendStatus> listByTaskId(Long id);
}
