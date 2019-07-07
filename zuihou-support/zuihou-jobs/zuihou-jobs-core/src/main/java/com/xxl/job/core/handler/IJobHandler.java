package com.xxl.job.core.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * job handler
 *
 * @author xuxueli 2015-12-19 19:06:38
 */
public abstract class IJobHandler {


    /**
     * success
     */
    public static final ReturnT<String> SUCCESS = new ReturnT<String>(200, null);
    /**
     * fail
     */
    public static final ReturnT<String> FAIL = new ReturnT<String>(500, null);
    /**
     * fail timeout
     */
    public static final ReturnT<String> FAIL_TIMEOUT = new ReturnT<String>(502, null);


    /**
     * execute handler, invoked when executor receives a scheduling request
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ReturnT<String> execute(String param) {
        try {
            return execute2(param);
        } catch (Exception e) {
            XxlJobLogger.log("任务处理器执行失败", e.getMessage());
            return new ReturnT<>(IJobHandler.FAIL.getCode(), e.getMessage());
        }
    }

    public abstract ReturnT<String> execute2(String param) throws Exception;

    /**
     * init handler, invoked when JobThread init
     */
    public void init() {
        // TODO
    }


    /**
     * destroy handler, invoked when JobThread destroy
     */
    public void destroy() {
        // TODO
    }


}
