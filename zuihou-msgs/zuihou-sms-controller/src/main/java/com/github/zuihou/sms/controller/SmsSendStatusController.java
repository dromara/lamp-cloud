package com.github.zuihou.sms.controller;


import com.github.zuihou.base.controller.SuperController;
import com.github.zuihou.sms.dto.SmsSendStatusSaveDTO;
import com.github.zuihou.sms.dto.SmsSendStatusUpdateDTO;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.service.SmsSendStatusService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsSendStatus")
@Api(value = "SmsSendStatus", tags = "短信发送状态")
public class SmsSendStatusController extends SuperController<SmsSendStatusService, Long, SmsSendStatus, SmsSendStatus, SmsSendStatusSaveDTO, SmsSendStatusUpdateDTO> {

}
