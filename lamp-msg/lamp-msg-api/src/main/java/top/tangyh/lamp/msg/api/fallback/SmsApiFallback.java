package top.tangyh.lamp.msg.api.fallback;

import top.tangyh.basic.base.R;
import top.tangyh.lamp.msg.api.SmsApi;
import top.tangyh.lamp.sms.dto.SmsTaskSaveDTO;
import top.tangyh.lamp.sms.dto.VerificationCodeDTO;
import top.tangyh.lamp.sms.entity.SmsTask;
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
