package com.github.zuihou.jobs.api.fallback;

import com.github.zuihou.base.R;
import com.github.zuihou.jobs.api.JobsTimingApi;
import com.github.zuihou.jobs.dto.XxlJobInfo;
import org.springframework.stereotype.Component;

/**
 * 定时API 熔断
 *
 * @author zuihou
 * @date 2019/07/16
 */
@Component
public class JobsTimingApiFallback implements JobsTimingApi {
    @Override
    public R<String> addTimingTask(XxlJobInfo xxlJobInfo) {
        return R.timeout();
    }
}
