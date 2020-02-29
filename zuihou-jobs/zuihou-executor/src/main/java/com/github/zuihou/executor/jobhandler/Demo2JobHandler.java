package com.github.zuihou.executor.jobhandler;

import com.github.zuihou.authority.dao.auth.UserMapper;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.context.BaseContextHandler;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
@JobHandler(value = "demo2JobHandler")
@Component
@Slf4j
public class Demo2JobHandler extends IJobHandler {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnT<String> execute2(String param) throws Exception {

        BaseContextHandler.setTenant("0000");

        User user = userMapper.selectById(3);
        //记录日志的方法推荐使用这个:XxlJobLogger.log ，因为这个记录的日志，可以在zuihou-jobs-server管理后台查看
        XxlJobLogger.log("执行结果--->param={}, user={} ", param, user);
        log.info("执行结果--->hello:{}, user:[{}] ", "hello local", user);


        return SUCCESS;
    }

}
