package top.tangyh.lamp.common.api.fallback;

import top.tangyh.basic.base.R;
import top.tangyh.basic.model.log.OptLogDTO;
import org.springframework.stereotype.Component;
import top.tangyh.lamp.common.api.LogApi;

/**
 * 日志操作 熔断
 *
 * @author zuihou
 * @date 2019/07/02
 */
@Component
public class LogApiHystrix implements LogApi {
    @Override
    public R<OptLogDTO> save(OptLogDTO log) {
        return R.timeout();
    }
}
