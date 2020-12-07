package com.tangyh.lamp.sms.service.impl;


import com.tangyh.basic.base.service.SuperServiceImpl;
import com.tangyh.lamp.sms.dao.SmsSendStatusMapper;
import com.tangyh.lamp.sms.entity.SmsSendStatus;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service

public class SmsSendStatusServiceImpl extends SuperServiceImpl<SmsSendStatusMapper, SmsSendStatus> implements SmsSendStatusService {

}
