package com.github.zuihou.executor.jobhandler.tenant;

import com.github.zuihou.tenant.strategy.InitSystemContext;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 重置 演示环境的 默认库数据
 *
 * @author zuihou
 * @date 2020年01月16日14:45:58
 */
@JobHandler(value = "restBase0000JobHandler")
@Component
@Slf4j
public class RestBase0000JobHandler extends IJobHandler {
    /**
     * 内置租户
     */
    private final static String DEF_TENANT = "0000";
    @Autowired
    private InitSystemContext initSystemContext;

    @Override
    public ReturnT<String> execute2(String param) throws Exception {
        XxlJobLogger.log("执行参数--->param={} ", param);

        initSystemContext.reset(DEF_TENANT);

        return SUCCESS;
    }
}
