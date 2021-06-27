package com.tangyh.lamp.msg.api;


import com.tangyh.basic.base.R;
import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.lamp.msg.api.fallback.SmsApiFallback;
import com.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import com.tangyh.lamp.sms.dto.VerificationCodeDTO;
import com.tangyh.lamp.sms.entity.SmsTask;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文件接口
 *
 * @author zuihou
 * @date 2019/06/21
 */
@FeignClient(name = "${lamp.feign.msg-server:lamp-msg-server}", fallback = SmsApiFallback.class)
public interface SmsApi {
    /**
     * 短信发送
     *
     * @param smsTaskDTO 短信参数
     * @return 短信任务
     */
    @RequestMapping(value = "/smsTask", method = RequestMethod.POST)
    R<SmsTask> send(@RequestBody SmsTaskSaveDTO smsTaskDTO);

    /**
     * 发送验证码
     *
     * @param data 验证码参数
     * @return 是否执行发送
     */
    @PostMapping(value = "/verification/send")
    R<Boolean> sendCode(@Validated @RequestBody VerificationCodeDTO data);

    /**
     * 验证
     *
     * @param data 验证码参数
     * @return 是否验证成功
     */
    @PostMapping("/verification")
    R<Boolean> verification(@Validated(SuperEntity.Update.class) @RequestBody VerificationCodeDTO data);
}
