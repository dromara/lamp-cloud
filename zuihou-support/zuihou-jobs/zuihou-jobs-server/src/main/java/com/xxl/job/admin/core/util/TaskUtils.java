//package com.xxl.job.admin.core.util;
//
//import com.alibaba.fastjson.JSONObject;
//import com.xxl.job.core.biz.model.ReturnT;
//import com.xxl.job.core.biz.model.TriggerParam;
//import com.xxl.job.core.handler.IJobHandler;
//
//import lombok.extern.slf4j.Slf4j;
//
//
//@Slf4j
//public abstract class TaskUtils {
//
//    private TaskUtils() {
//        super();
//    }
//
//    public static ReturnT<String> invokeMethod(TriggerParam triggerParam) {
//        log.debug("triggerParam=" + JSONObject.toJSONString(triggerParam));
//
//        IJobHandler handler = SpringUtil.getBean(triggerParam.getExecutorHandler(), IJobHandler.class);
//
//        if (handler == null) {
//            log.error("任务名称 = [" + triggerParam.getExecutorHandler() + "]----未启动成功，请检查是否配置正确！！！");
//            return new ReturnT(-1, "执行器尚未实例化");
//        }
//
//        return handler.execute(triggerParam.getExecutorParams());
//    }
//
//}
