package com.xxl.job.admin.core.schedule;

import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.jobbean.RemoteHttpJobBean;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.thread.JobFailMonitorHelper;
import com.xxl.job.admin.core.thread.JobRegistryMonitorHelper;
import com.xxl.job.admin.core.thread.JobTriggerPoolHelper;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.core.biz.AdminBiz;
import com.xxl.job.core.biz.ExecutorBiz;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.rpc.remoting.invoker.XxlRpcInvokerFactory;
import com.xxl.rpc.remoting.invoker.call.CallType;
import com.xxl.rpc.remoting.invoker.reference.XxlRpcReferenceBean;
import com.xxl.rpc.remoting.net.NetEnum;
import com.xxl.rpc.remoting.net.impl.jetty.server.JettyServerHandler;
import com.xxl.rpc.remoting.provider.XxlRpcProviderFactory;
import com.xxl.rpc.serialize.Serializer;
import org.eclipse.jetty.server.Request;
import org.quartz.*;
import org.quartz.Trigger.TriggerState;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态调度器工具
 * base quartz scheduler util
 *
 * @author xuxueli 2015-12-19 16:13:53
 */
public final class XxlJobDynamicScheduler {
    private static final Logger logger = LoggerFactory.getLogger(XxlJobDynamicScheduler.class);

    // ---------------------- param ----------------------

    // scheduler
    private static Scheduler scheduler;
    // ---------------------- admin rpc provider (no server version) ----------------------
    private static JettyServerHandler jettyServerHandler;
    /**
     * 执行器列表
     * key 是地址， value 是具体的执行器， 类似FeignClient
     */
    private static ConcurrentHashMap<String, ExecutorBiz> executorBizRepository = new ConcurrentHashMap<String, ExecutorBiz>();

    public static void invokeAdminService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        jettyServerHandler.handle(null, new Request(null, null), request, response);
    }


    // ---------------------- I18n ----------------------

    public static ExecutorBiz getExecutorBiz(String address) throws Exception {
        // valid
        if (address == null || address.trim().length() == 0) {
            return null;
        }

        // load-cache
        address = address.trim();
        ExecutorBiz executorBiz = executorBizRepository.get(address);
        if (executorBiz != null) {
            return executorBiz;
        }

        // set-cache
        executorBiz = (ExecutorBiz) new XxlRpcReferenceBean(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), CallType.SYNC,
                ExecutorBiz.class, null, 10000, address, XxlJobAdminConfig.getAdminConfig().getAccessToken(), null).getObject();

        executorBizRepository.put(address, executorBiz);
        return executorBiz;
    }

    /**
     * fill job info
     *
     * @param jobInfo
     */
    public static void fillJobInfo(XxlJobInfo jobInfo) {

        String group = String.valueOf(jobInfo.getJobGroup());
        String name = String.valueOf(jobInfo.getId());

        // trigger key
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        try {

            // trigger cron
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger != null && trigger instanceof CronTriggerImpl) {
                String cronExpression = ((CronTriggerImpl) trigger).getCronExpression();
                jobInfo.setJobCron(cronExpression);
            }

            // trigger state
            TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            if (triggerState != null) {
                jobInfo.setJobStatus(triggerState.name());
            }

        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * add trigger + job
     *
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @return
     * @throws SchedulerException
     */
    public static boolean addJob(String jobName, String jobGroup, String cronExpression) throws SchedulerException {
        // 1、job key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);

        // 2、valid
        if (scheduler.checkExists(triggerKey)) {
            return true;
        }

        // 3、corn trigger
        /*
        Misfire规则：
        withMisfireHandlingInstructionDoNothing：不触发立即执行，等待下次调度；
        withMisfireHandlingInstructionIgnoreMisfires：以错过的第一个频率时间立刻开始执行；
        withMisfireHandlingInstructionFireAndProceed：以当前时间为触发频率立刻触发一次执行；
         */
        // withMisfireHandlingInstructionDoNothing 忽略掉调度终止过程中忽略的调度
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        // 4、job detail
        Class<? extends Job> jobclass = RemoteHttpJobBean.class;
        JobDetail jobDetail = JobBuilder.newJob(jobclass).withIdentity(jobKey).build();


        // 5、schedule job
        Date date = scheduler.scheduleJob(jobDetail, cronTrigger);

        logger.info(">>>>>>>>>>> addJob success, jobDetail:{}, cronTrigger:{}, date:{}", jobDetail, cronTrigger, date);
        return true;
    }

    public static boolean addJob(String jobName, String jobGroup, Date startExecuteTime, Date endExecuteTime, Integer intervalSeconds, Integer execCount) throws SchedulerException {
        // 1、job key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        JobKey jobKey = new JobKey(jobName, jobGroup);

        // 2、valid
        if (scheduler.checkExists(triggerKey)) {
            return true;
        }

        /*
        https://blog.csdn.net/u010648555/article/details/53672738

            ssb.withMisfireHandlingInstructionFireNow();//1
            ssb.withMisfireHandlingInstructionIgnoreMisfires();//2
            ssb.withMisfireHandlingInstructionNextWithExistingCount();//3
            ssb.withMisfireHandlingInstructionNextWithRemainingCount();//4
            ssb.withMisfireHandlingInstructionNowWithExistingCount();//5
            ssb.withMisfireHandlingInstructionNowWithRemainingCount();//6


            //1
            withMisfireHandlingInstructionFireNow  ---> misfireInstruction == 1
            ——以当前时间为触发频率立即触发执行
            ——执行至FinalTIme的剩余周期次数
            ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
            ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值

            //2
            withMisfireHandlingInstructionIgnoreMisfires ---> misfireInstruction == -1
            —以错过的第一个频率时间立刻开始执行
            ——重做错过的所有频率周期
            ——当下一次触发频率发生时间大于当前时间以后，按照Interval的依次执行剩下的频率
            ——共执行RepeatCount+1次

            //3
            withMisfireHandlingInstructionNextWithExistingCount ---> misfireInstruction == 5
            ——不触发立即执行
            ——等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
            ——以startTime为基准计算周期频率，并得到FinalTime
            ——即使中间出现pause，resume以后保持FinalTime时间不变

            //4
            withMisfireHandlingInstructionNextWithRemainingCount ---> misfireInstruction = 4
            ——不触发立即执行
            ——等待下次触发频率周期时刻，执行至FinalTime的剩余周期次数
            ——以startTime为基准计算周期频率，并得到FinalTime
            ——即使中间出现pause，resume以后保持FinalTime时间不变

            //5
            withMisfireHandlingInstructionNowWithExistingCount ---> misfireInstruction = 2
            ——以当前时间为触发频率立即触发执行
            ——执行至FinalTIme的剩余周期次数
            ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
            ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值

            //6
            withMisfireHandlingInstructionNowWithRemainingCount --- >misfireInstruction = 3
            ——以当前时间为触发频率立即触发执行
            ——执行至FinalTIme的剩余周期次数
            ——以调度或恢复调度的时刻为基准的周期频率，FinalTime根据剩余次数和当前时间计算得到
            ——调整后的FinalTime会略大于根据starttime计算的到的FinalTime值


            MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT 值为 3
            ——此指令导致trigger忘记原始设置的starttime和repeat-count
            ——触发器的repeat-count将被设置为剩余的次数
            ——这样会导致后面无法获得原始设定的starttime和repeat-count值

         */


        SimpleScheduleBuilder timingScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                // 间隔时间 0
                .withIntervalInSeconds(intervalSeconds)
                // 重复次数 实际上执行了3次 0
                .withRepeatCount(execCount);


        SimpleTrigger singleTrigger = null;
        if (endExecuteTime != null) {
            singleTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    // 开始执行时间
                    .startAt(startExecuteTime)
                    // 结束执行时间
                    .endAt(endExecuteTime)
                    .withSchedule(timingScheduleBuilder)
                    .build();
        } else {
            singleTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    // 开始执行时间
                    .startAt(startExecuteTime)
                    .withSchedule(timingScheduleBuilder)
                    .build();
        }

        Class<? extends Job> jobclass = RemoteHttpJobBean.class;
        JobDetail jobDetail = JobBuilder.newJob(jobclass).withIdentity(jobKey).build();


        // 5、schedule job
        Date date = scheduler.scheduleJob(jobDetail, singleTrigger);

        logger.info(">>>>>>>>>>> addJob success, jobDetail:{}, cronTrigger:{}, date:{}", jobDetail, singleTrigger, date);
        return true;
    }

    /**
     * remove trigger + job
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean removeJob(String jobName, String jobGroup) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        if (scheduler.checkExists(triggerKey)) {
            scheduler.unscheduleJob(triggerKey);
        }

        logger.info(">>>>>>>>>>> removeJob success, triggerKey:{}", triggerKey);
        return true;
    }


    // ---------------------- executor-client ----------------------

    /**
     * updateJobCron
     *
     * @param jobGroup
     * @param jobName
     * @param cronExpression
     * @return
     * @throws SchedulerException
     */
    public static boolean updateJobCron(String jobGroup, String jobName, String cronExpression) throws SchedulerException {

        // 1、job key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        // 2、valid
        if (!scheduler.checkExists(triggerKey)) {
            return true;
        }

        CronTrigger oldTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 3、avoid repeat cron
        String oldCron = oldTrigger.getCronExpression();
        if (oldCron.equals(cronExpression)) {
            return true;
        }

        // 4、new cron trigger
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
        oldTrigger = oldTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        // 5、rescheduleJob
        scheduler.rescheduleJob(triggerKey, oldTrigger);

        logger.info(">>>>>>>>>>> resumeJob success, JobGroup:{}, JobName:{}", jobGroup, jobName);
        return true;
    }

    public void setScheduler(Scheduler scheduler) {
        XxlJobDynamicScheduler.scheduler = scheduler;
    }


    // ---------------------- schedule util ----------------------

    // ---------------------- init + destroy ----------------------
    public void start() throws Exception {
        // valid
        Assert.notNull(scheduler, "quartz scheduler is null");

        // init i18n
        initI18n();

        // admin registry monitor run
        JobRegistryMonitorHelper.getInstance().start();

        // admin monitor run
        JobFailMonitorHelper.getInstance().start();

        // admin-server
        initRpcProvider();

        logger.info(">>>>>>>>> init xxl-job admin success.");
    }

    public void destroy() throws Exception {
        // admin trigger pool stop
        JobTriggerPoolHelper.toStop();

        // admin registry stop
        JobRegistryMonitorHelper.getInstance().toStop();

        // admin monitor stop
        JobFailMonitorHelper.getInstance().toStop();

        // admin-server
        stopRpcProvider();
    }

    private void initI18n() {
        for (ExecutorBlockStrategyEnum item : ExecutorBlockStrategyEnum.values()) {
            item.setTitle(I18nUtil.getString("jobconf_block_".concat(item.name())));
        }
    }

    private void initRpcProvider() {
        // init
        XxlRpcProviderFactory xxlRpcProviderFactory = new XxlRpcProviderFactory();
        xxlRpcProviderFactory.initConfig(NetEnum.JETTY, Serializer.SerializeEnum.HESSIAN.getSerializer(), null, 0, XxlJobAdminConfig.getAdminConfig().getAccessToken(), null, null);

        // add services
        xxlRpcProviderFactory.addService(AdminBiz.class.getName(), null, XxlJobAdminConfig.getAdminConfig().getAdminBiz());

        // jetty handler
        jettyServerHandler = new JettyServerHandler(xxlRpcProviderFactory);
    }

    private void stopRpcProvider() throws Exception {
        new XxlRpcInvokerFactory().stop();
    }


    /**
     * pause
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    /*public static boolean pauseJob(String jobName, String jobGroup) throws SchedulerException {

    	TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            scheduler.pauseTrigger(triggerKey);
            result =  true;
        }

        logger.info(">>>>>>>>>>> pauseJob {}, triggerKey:{}", (result?"success":"fail"),triggerKey);
        return result;
    }*/


    /**
     * resume
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    /*public static boolean resumeJob(String jobName, String jobGroup) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            scheduler.resumeTrigger(triggerKey);
            result = true;
        }

        logger.info(">>>>>>>>>>> resumeJob {}, triggerKey:{}", (result?"success":"fail"), triggerKey);
        return result;
    }*/


    /**
     * run
     *
     * @param jobName
     * @param jobGroup
     * @return
     * @throws SchedulerException
     */
    /*public static boolean triggerJob(String jobName, String jobGroup) throws SchedulerException {
    	// TriggerKey : name + group
    	JobKey jobKey = new JobKey(jobName, jobGroup);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            scheduler.triggerJob(jobKey);
            result = true;
            logger.info(">>>>>>>>>>> runJob success, jobKey:{}", jobKey);
        } else {
        	logger.info(">>>>>>>>>>> runJob fail, jobKey:{}", jobKey);
        }
        return result;
    }*/


    /**
     * finaAllJobList
     *
     * @return
     *//*
    public static List<Map<String, Object>> finaAllJobList(){
        List<Map<String, Object>> jobList = new ArrayList<Map<String,Object>>();

        try {
            if (scheduler.getJobGroupNames()==null || scheduler.getJobGroupNames().size()==0) {
                return null;
            }
            String groupName = scheduler.getJobGroupNames().get(0);
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName));
            if (jobKeys!=null && jobKeys.size()>0) {
                for (JobKey jobKey : jobKeys) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(jobKey.getName(), Scheduler.DEFAULT_GROUP);
                    Trigger trigger = scheduler.getTrigger(triggerKey);
                    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                    TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                    Map<String, Object> jobMap = new HashMap<String, Object>();
                    jobMap.put("TriggerKey", triggerKey);
                    jobMap.put("Trigger", trigger);
                    jobMap.put("JobDetail", jobDetail);
                    jobMap.put("TriggerState", triggerState);
                    jobList.add(jobMap);
                }
            }

        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
        return jobList;
    }*/

}
