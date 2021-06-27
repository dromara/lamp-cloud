package com.tangyh.lamp.msg.api.fallback;

import com.tangyh.basic.base.R;
import com.tangyh.lamp.msg.api.SmsApi;
import com.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import com.tangyh.lamp.sms.dto.VerificationCodeDTO;
import com.tangyh.lamp.sms.entity.SmsTask;
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
    public R<SmsTask> send(SmsTaskSaveDTO smsTaskDTO) {
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
