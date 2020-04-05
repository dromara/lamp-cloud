package com.github.zuihou.oauth.api.hystrix;

import com.github.zuihou.base.R;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.oauth.api.LogApi;
import org.springframework.stereotype.Component;

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
