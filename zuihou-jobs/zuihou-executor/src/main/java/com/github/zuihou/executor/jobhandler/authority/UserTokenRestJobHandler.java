package com.github.zuihou.executor.jobhandler.authority;

import com.github.zuihou.authority.entity.auth.UserToken;
import com.github.zuihou.authority.service.auth.UserTokenService;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.database.mybatis.conditions.query.LbqWrapper;
import com.github.zuihou.executor.jobhandler.GlobalTenantJobHandler;
import com.github.zuihou.tenant.entity.Tenant;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 任务Handler示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHandler(value = "userTokenRestJobHandler")
@Component
@Slf4j
public class UserTokenRestJobHandler extends GlobalTenantJobHandler {

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public ReturnT<String> executeBiz(Tenant tenant, String param) {

        BaseContextHandler.setTenant(tenant.getCode());
        LbqWrapper<UserToken> wrapper = Wraps.<UserToken>lbQ().le(UserToken::getExpireTime, LocalDateTime.now());
        boolean remove = userTokenService.remove(wrapper);

        XxlJobLogger.log("执行结果:{} ", remove);
        log.info("执行结果:{} ", remove);
        return SUCCESS;
    }

}
