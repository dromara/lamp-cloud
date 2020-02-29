package com.github.zuihou.msgs.api.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.msgs.api.SmsApi;
import com.github.zuihou.sms.dto.SmsSendTaskDTO;
import com.github.zuihou.sms.dto.VerificationCodeDTO;
import com.github.zuihou.sms.entity.SmsTask;
import org.springframework.stereotype.Component;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class SmsApiFallback implements SmsApi {
    @Override
    public R<SmsTask> send(SmsSendTaskDTO smsTaskDTO) {
        return R.timeout();
    }

    @Override
    public R<Boolean> sendCode(VerificationCodeDTO data) {
        return R.timeout();
    }

    @Override
    public R<Boolean> verification(VerificationCodeDTO data) {
        return R.timeout();
    }
}
