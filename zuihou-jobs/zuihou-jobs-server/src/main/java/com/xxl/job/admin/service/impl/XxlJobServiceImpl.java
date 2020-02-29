package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.core.schedule.XxlJobDynamicScheduler;
import com.xxl.job.admin.core.trigger.JobTypeEnum;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.dao.XxlJobLogDao;
import com.xxl.job.admin.dao.XxlJobLogGlueDao;
import com.xxl.job.admin.service.XxlJobService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

/**
 * core job action for xxl-job
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
@Service
public class XxlJobServiceImpl implements XxlJobService {
    private static final String TRIGGER_CHART_DATA_CACHE = "trigger_chart_data_cache";
    private static Logger logger = LoggerFactory.getLogger(XxlJobServiceImpl.class);
    @Resource
    public XxlJobLogDao xxlJobLogDao;
    @Resource
    private XxlJobGroupDao xxlJobGroupDao;
    @Resource
    private XxlJobInfoDao xxlJobInfoDao;
    @Resource
    private XxlJobLogGlueDao xxlJobLogGlueDao;

    @Override
    public Map<String, Object> pageList(Integer start, Integer length, Integer jobGroup, String jobDesc, String executorHandler, String filterTime, Integer type) {

        // page list
        List<XxlJobInfo> list = xxlJobInfoDao.pageList(start, length, jobGroup, jobDesc, executorHandler, type);
        int listCount = xxlJobInfoDao.pageListCount(start, length, jobGroup, jobDesc, executorHandler, type);

        // fill job info
        if (list != null && list.size() > 0) {
            for (XxlJobInfo jobInfo : list) {
                XxlJobDynamicScheduler.fillJobInfo(jobInfo);
            }
        }

        // package result
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("recordsTotal", listCount);        // 总记录数
        maps.put("recordsFiltered", listCount);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        return maps;
    }

    @Override
    public ReturnT<String> addStart(XxlJobInfo jobInfo) {
        ReturnT<String> result = this.add(jobInfo);
        if (result.getIsSuccess()) {
            start(Integer.valueOf(result.getContent()));
        }
        return result;
    }

    @Override
    public ReturnT<String> add(XxlJobInfo jobInfo) {
        // valid 执行器是否存在
        XxlJobGroup group = xxlJobGroupDao.load(jobInfo.getJobGroup());
        if (group == null) {
            group = xxlJobGroupDao.getByName(jobInfo.getJobGroupName());
            if (group == null) {
                return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
            }
            jobInfo.setJobGroup(group.getId());
        }


        if (JobTypeEnum.CRON.eq(jobInfo.getType())) {
            if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
                return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_unvalid"));
            }
        } else {
            jobInfo.setType(JobTypeEnum.TIMES.getCode());
            //表单校验
            String msg = validate(jobInfo.getIntervalSeconds(), jobInfo.getRepeatCount(),
                    jobInfo.getStartExecuteTime(), jobInfo.getEndExecuteTime());
            if (!StringUtils.isEmpty(msg)) {
                return new ReturnT<>(ReturnT.FAIL_CODE, msg);
            }
        }
        if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType()) && StringUtils.isBlank(jobInfo.getExecutorHandler())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + "JobHandler"));
        }

        if (StringUtils.isBlank(jobInfo.getJobDesc())) {
            jobInfo.setJobDesc("任务描述");
        }
        if (StringUtils.isBlank(jobInfo.getAuthor())) {
            jobInfo.setAuthor("admin");
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            jobInfo.setExecutorRouteStrategy(ExecutorRouteStrategyEnum.FIRST.name());
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            jobInfo.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.SERIAL_EXECUTION.name());
        }
        if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
            jobInfo.setGlueType(GlueTypeEnum.BEAN.name());
        }
        // fix "\r" in shell
        if (GlueTypeEnum.GLUE_SHELL == GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource() != null) {
            jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
        }

        // ChildJobId valid
        if (StringUtils.isNotBlank(jobInfo.getChildJobId())) {
            String[] childJobIds = StringUtils.split(jobInfo.getChildJobId(), ",");
            for (String childJobIdItem : childJobIds) {
                if (StringUtils.isNotBlank(childJobIdItem) && StringUtils.isNumeric(childJobIdItem)) {
                    XxlJobInfo childJobInfo = xxlJobInfoDao.loadById(Integer.valueOf(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_unvalid")), childJobIdItem));
                }
            }
            jobInfo.setChildJobId(StringUtils.join(childJobIds, ","));
        }


        // add in db
        xxlJobInfoDao.save(jobInfo);
        if (jobInfo.getId() < 1) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_add") + I18nUtil.getString("system_fail")));
        }

        return new ReturnT<String>(String.valueOf(jobInfo.getId()));
    }

    /**
     * 定时表单规则校验
     *
     * @param repeatInterval
     * @param repeatCount
     * @param startTime
     * @param endTime
     * @return
     */
    public String validate(Integer repeatInterval, Integer repeatCount, Date startTime, Date endTime) {
        if (repeatInterval != null && repeatInterval < 0L) {
            return "间隔秒数必须大于等于0.";
        }
        if (repeatCount != null && repeatInterval != null && repeatCount < -1 && repeatInterval >= 1) {
            return "重复次数必须大于等于-1.";
        }
        if (repeatCount != null && repeatCount < 0 && repeatCount != -1) {
            return "重复次数必须大于等于0.";
        }
        if (repeatCount != null && repeatInterval != null && repeatCount != 0 && repeatInterval < 1L) {
            return "间隔秒数不能为0.";
        }
        if (startTime == null) {
            return "执行开始时间不能为空.";
        }
        if (startTime.before(new Date())) {
            return "执行开始时间不能小于当前时间.";
        }
        if (endTime != null) {
            if (endTime.before(startTime) || endTime.equals(startTime)) {
                return "结束执行时间不能小于等于开始执行时间.";
            }
        }
        return null;
    }

    @Override
    public ReturnT<String> update(XxlJobInfo jobInfo) {

        // valid
        if (JobTypeEnum.CRON.eq(jobInfo.getType())) {
            if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_unvalid"));
            }
        } else {
            //表单校验
            String msg = validate(jobInfo.getIntervalSeconds(), jobInfo.getRepeatCount(),
                    jobInfo.getStartExecuteTime(), jobInfo.getEndExecuteTime());
            if (!StringUtils.isEmpty(msg)) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, msg);
            }
        }
        if (StringUtils.isBlank(jobInfo.getJobDesc())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
        }
        if (StringUtils.isBlank(jobInfo.getAuthor())) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_unvalid")));
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_unvalid")));
        }

        // ChildJobId valid
        if (StringUtils.isNotBlank(jobInfo.getChildJobId())) {
            String[] childJobIds = StringUtils.split(jobInfo.getChildJobId(), ",");
            for (String childJobIdItem : childJobIds) {
                if (StringUtils.isNotBlank(childJobIdItem) && StringUtils.isNumeric(childJobIdItem)) {
                    XxlJobInfo childJobInfo = xxlJobInfoDao.loadById(Integer.valueOf(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_unvalid")), childJobIdItem));
                }
            }
            jobInfo.setChildJobId(StringUtils.join(childJobIds, ","));
        }

        // stage job info
        XxlJobInfo existsJobInfo = xxlJobInfoDao.loadById(jobInfo.getId());
        if (existsJobInfo == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_id") + I18nUtil.getString("system_not_found")));
        }
        //String old_cron = exists_jobInfo.getJobCron();

        existsJobInfo.setJobCron(jobInfo.getJobCron());
        existsJobInfo.setJobDesc(jobInfo.getJobDesc());
        existsJobInfo.setAuthor(jobInfo.getAuthor());
        existsJobInfo.setAlarmEmail(jobInfo.getAlarmEmail());
        existsJobInfo.setExecutorRouteStrategy(jobInfo.getExecutorRouteStrategy());
        existsJobInfo.setExecutorHandler(jobInfo.getExecutorHandler());
        existsJobInfo.setExecutorParam(jobInfo.getExecutorParam());
        existsJobInfo.setExecutorBlockStrategy(jobInfo.getExecutorBlockStrategy());
        existsJobInfo.setExecutorTimeout(jobInfo.getExecutorTimeout());
        existsJobInfo.setExecutorFailRetryCount(jobInfo.getExecutorFailRetryCount());
        existsJobInfo.setChildJobId(jobInfo.getChildJobId());
        existsJobInfo.setRepeatCount(jobInfo.getRepeatCount());
        existsJobInfo.setStartExecuteTime(jobInfo.getStartExecuteTime());
        existsJobInfo.setEndExecuteTime(jobInfo.getEndExecuteTime());
        existsJobInfo.setIntervalSeconds(jobInfo.getIntervalSeconds());
        xxlJobInfoDao.update(existsJobInfo);

        if (JobTypeEnum.TIMES.eq(jobInfo.getType())) {
            return ReturnT.SUCCESS;
        }

        // update quartz-cron if started
        String qzGroup = String.valueOf(existsJobInfo.getJobGroup());
        String qzName = String.valueOf(existsJobInfo.getId());
        try {
            XxlJobDynamicScheduler.updateJobCron(qzGroup, qzName, existsJobInfo.getJobCron());
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return ReturnT.FAIL;
        }

        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> remove(Integer id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);
        String group = String.valueOf(xxlJobInfo.getJobGroup());
        String name = String.valueOf(xxlJobInfo.getId());

        try {
            // unbind quartz
            XxlJobDynamicScheduler.removeJob(name, group);

            xxlJobInfoDao.delete(id);
            xxlJobLogDao.delete(id);
            xxlJobLogGlueDao.deleteByJobId(id);
            return ReturnT.SUCCESS;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return ReturnT.FAIL;
        }
    }

    @Override
    public ReturnT<String> start(Integer id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);
        if (JobTypeEnum.CRON.eq(xxlJobInfo.getType())) {
            if (!CronExpression.isValidExpression(xxlJobInfo.getJobCron())) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_unvalid"));
            }
        } else {
            //表单校验
            String msg = validate(xxlJobInfo.getIntervalSeconds(), xxlJobInfo.getRepeatCount(),
                    xxlJobInfo.getStartExecuteTime(), xxlJobInfo.getEndExecuteTime());
            if (!StringUtils.isEmpty(msg)) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, msg);
            }
        }
        String group = String.valueOf(xxlJobInfo.getJobGroup());
        String name = String.valueOf(xxlJobInfo.getId());
        String cronExpression = xxlJobInfo.getJobCron();
        boolean ret = false;
        try {
            //判断定时类型
            if (JobTypeEnum.CRON.eq(xxlJobInfo.getType())) {
                ret = XxlJobDynamicScheduler.addJob(name, group, cronExpression);
            } else {
                /*if (!DateUtil.isMatch(xxlJobInfo.get())) {
                    return new ReturnT<>(ReturnT.START_JOB_FAI, "触发时间不能小于当前时间.");
                }*/
                ret = XxlJobDynamicScheduler.addJob(name, group, xxlJobInfo.getStartExecuteTime(), xxlJobInfo.getEndExecuteTime(), xxlJobInfo.getIntervalSeconds(), xxlJobInfo.getRepeatCount());
            }
            return ret ? ReturnT.SUCCESS : ReturnT.FAIL;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return ReturnT.FAIL;
        }
    }

	/*@Override
    public ReturnT<String> triggerJob(int id, int failRetryCount) {

		JobTriggerPoolHelper.trigger(id, failRetryCount);
		return ReturnT.SUCCESS;

        *//*XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);
        if (xxlJobInfo == null) {
        	return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_id")+I18nUtil.getString("system_unvalid")) );
		}

        String group = String.valueOf(xxlJobInfo.getJobGroup());
        String name = String.valueOf(xxlJobInfo.getId());

		try {
			XxlJobDynamicScheduler.triggerJob(name, group);
			return ReturnT.SUCCESS;
		} catch (SchedulerException e) {
			logger.error(e.getMessage(), e);
			return new ReturnT<String>(ReturnT.FAIL_CODE, e.getMessage());
		}*//*

	}*/

    @Override
    public ReturnT<String> stop(Integer id) {
        XxlJobInfo xxlJobInfo = xxlJobInfoDao.loadById(id);
        String group = String.valueOf(xxlJobInfo.getJobGroup());
        String name = String.valueOf(xxlJobInfo.getId());

        try {
            // bind quartz
            boolean ret = XxlJobDynamicScheduler.removeJob(name, group);
            return ret ? ReturnT.SUCCESS : ReturnT.FAIL;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return ReturnT.FAIL;
        }
    }

    @Override
    public Map<String, Object> dashboardInfo() {

        int jobInfoCount = xxlJobInfoDao.findAllCount();
        int jobLogCount = xxlJobLogDao.triggerCountByHandleCode(-1);
        int jobLogSuccessCount = xxlJobLogDao.triggerCountByHandleCode(ReturnT.SUCCESS_CODE);

        // executor count
        Set<String> executerAddressSet = new HashSet<String>();
        List<XxlJobGroup> groupList = xxlJobGroupDao.findAll();

        if (CollectionUtils.isNotEmpty(groupList)) {
            for (XxlJobGroup group : groupList) {
                if (CollectionUtils.isNotEmpty(group.getRegistryList())) {
                    executerAddressSet.addAll(group.getRegistryList());
                }
            }
        }

        int executorCount = executerAddressSet.size();

        Map<String, Object> dashboardMap = new HashMap<String, Object>();
        dashboardMap.put("jobInfoCount", jobInfoCount);
        dashboardMap.put("jobLogCount", jobLogCount);
        dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
        dashboardMap.put("executorCount", executorCount);
        return dashboardMap;
    }

    @Override
    public ReturnT<Map<String, Object>> chartInfo(Date startDate, Date endDate) {
		/*// get cache
		String cacheKey = TRIGGER_CHART_DATA_CACHE + "_" + startDate.getTime() + "_" + endDate.getTime();
		Map<String, Object> chartInfo = (Map<String, Object>) LocalCacheUtil.get(cacheKey);
		if (chartInfo != null) {
			return new ReturnT<Map<String, Object>>(chartInfo);
		}*/

        // process
        List<String> triggerDayList = new ArrayList<String>();
        List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
        List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
        List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
        int triggerCountRunningTotal = 0;
        int triggerCountSucTotal = 0;
        int triggerCountFailTotal = 0;

        List<Map<String, Object>> triggerCountMapAll = xxlJobLogDao.triggerCountByDay(startDate, endDate);
        if (CollectionUtils.isNotEmpty(triggerCountMapAll)) {
            for (Map<String, Object> item : triggerCountMapAll) {
                String day = String.valueOf(item.get("triggerDay"));
                int triggerDayCount = Integer.valueOf(String.valueOf(item.get("triggerDayCount")));
                int triggerDayCountRunning = Integer.valueOf(String.valueOf(item.get("triggerDayCountRunning")));
                int triggerDayCountSuc = Integer.valueOf(String.valueOf(item.get("triggerDayCountSuc")));
                int triggerDayCountFail = triggerDayCount - triggerDayCountRunning - triggerDayCountSuc;

                triggerDayList.add(day);
                triggerDayCountRunningList.add(triggerDayCountRunning);
                triggerDayCountSucList.add(triggerDayCountSuc);
                triggerDayCountFailList.add(triggerDayCountFail);

                triggerCountRunningTotal += triggerDayCountRunning;
                triggerCountSucTotal += triggerDayCountSuc;
                triggerCountFailTotal += triggerDayCountFail;
            }
        } else {
            for (int i = 4; i > -1; i--) {
                triggerDayList.add(FastDateFormat.getInstance("yyyy-MM-dd").format(DateUtils.addDays(new Date(), -i)));
                triggerDayCountRunningList.add(0);
                triggerDayCountSucList.add(0);
                triggerDayCountFailList.add(0);
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("triggerDayList", triggerDayList);
        result.put("triggerDayCountRunningList", triggerDayCountRunningList);
        result.put("triggerDayCountSucList", triggerDayCountSucList);
        result.put("triggerDayCountFailList", triggerDayCountFailList);

        result.put("triggerCountRunningTotal", triggerCountRunningTotal);
        result.put("triggerCountSucTotal", triggerCountSucTotal);
        result.put("triggerCountFailTotal", triggerCountFailTotal);

		/*// set cache
		LocalCacheUtil.set(cacheKey, result, 60*1000);     // cache 60s*/

        return new ReturnT<Map<String, Object>>(result);
    }

}
