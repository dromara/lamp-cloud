package com.xxl.job.admin.controller;

import com.xxl.job.admin.controller.annotation.PermessionLimit;
import com.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import com.xxl.job.core.biz.AdminBiz;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志回调服务
 * 调度模块的“调度中心”作为Web服务部署时，一方面承担调度中心功能，另一方面也为执行器提供API服务。
 * <p>
 * Created by xuxueli on 17/5/10.
 */
@Controller
public class JobApiController implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @RequestMapping(AdminBiz.MAPPING)
    @PermessionLimit(limit = false)
    public void api(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        XxlJobDynamicScheduler.invokeAdminService(request, response);
    }


}
