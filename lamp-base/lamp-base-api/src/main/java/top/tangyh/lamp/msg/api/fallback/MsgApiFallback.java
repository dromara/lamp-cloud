package top.tangyh.lamp.msg.api.fallback;

import org.springframework.stereotype.Component;
import top.tangyh.basic.base.R;
import top.tangyh.lamp.msg.api.MsgApi;
import top.tangyh.lamp.msg.vo.update.ExtendMsgSendVO;

/**
 * 熔断
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Component
public class MsgApiFallback implements MsgApi {
    @Override
    public R<Boolean> sendByTemplate(ExtendMsgSendVO data) {
        return R.timeout();
    }
}
