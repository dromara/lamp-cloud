package com.github.zuihou.executor.jobhandler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.tenant.entity.Tenant;
import com.github.zuihou.tenant.enumeration.TenantStatusEnum;
import com.github.zuihou.tenant.service.TenantService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import groovy.util.logging.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public abstract class GlobalTenantJobHandler extends IJobHandler {

    @Autowired
    private TenantService tenantService;

    @Override
    public ReturnT<String> execute2(String param) throws Exception {
        //记录日志的方法推荐使用这个:XxlJobLogger.log ，因为这个记录的日志，可以在zuihou-jobs-server管理后台查看
        XxlJobLogger.log("执行结果--->param={} ", param);

        String traceId = IdUtil.fastSimpleUUID();
        MDC.put(BaseContextConstants.LOG_TRACE_ID, StrUtil.isEmpty(traceId) ? StrUtil.EMPTY : traceId);

        LbqWrapper<Tenant> wrapper = Wraps.<Tenant>lbQ()
                .eq(Tenant::getStatus, TenantStatusEnum.NORMAL);

        List<Tenant> list = tenantService.list(wrapper);

        list.forEach((tenant) -> {
            MDC.put(BaseContextConstants.JWT_KEY_TENANT, tenant.getCode());
            executeBiz(tenant, param);
        });

        return SUCCESS;
    }

    public abstract ReturnT<String> executeBiz(Tenant tenant, String param);
}
