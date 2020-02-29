package com.xxl.job.admin.core.thread;

import com.xxl.job.admin.core.trigger.TriggerTypeEnum;
import com.xxl.job.admin.core.trigger.XxlJobTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * job trigger thread pool helper
 *
 * @author xuxueli 2018-07-03 21:08:07
 */
public class JobTriggerPoolHelper {
    private static Logger logger = LoggerFactory.getLogger(JobTriggerPoolHelper.class);


    // ---------------------- trigger pool ----------------------
    private static JobTriggerPoolHelper helper = new JobTriggerPoolHelper();
    private ThreadPoolExecutor triggerPool = new ThreadPoolExecutor(
            32,
            256,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    /**
     * @param jobId                 任务id
     * @param triggerType           任务类型
     * @param failRetryCount        重试次数
     *                              >=0: use this param
     *                              <0: use param from job info config
     * @param executorShardingParam
     * @param executorParam         执行参数
     *                              null: use job param
     *                              not null: cover job param
     */
    public static void trigger(int jobId, TriggerTypeEnum triggerType, int failRetryCount, String executorShardingParam, String executorParam) {
        helper.addTrigger(jobId, triggerType, failRetryCount, executorShardingParam, executorParam);
    }

    // ---------------------- helper ----------------------

    public static void toStop() {
        helper.stop();
    }

    public void addTrigger(final int jobId, final TriggerTypeEnum triggerType, final int failRetryCount, final String executorShardingParam, final String executorParam) {
        triggerPool.execute(new Runnable() {
            @Override
            public void run() {
                XxlJobTrigger.trigger(jobId, triggerType, failRetryCount, executorShardingParam, executorParam);
            }
        });
    }

    public void stop() {
        //triggerPool.shutdown();
        triggerPool.shutdownNow();
        logger.info(">>>>>>>>> xxl-job trigger thread pool shutdown success.");
    }

}
