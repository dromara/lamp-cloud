package com.github.zuihou.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuihou.common.constant.BizConstant;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.database.mybatis.conditions.Wraps;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.jobs.api.JobsTimingApi;
import com.github.zuihou.jobs.dto.XxlJobInfo;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.enumeration.ProviderType;
import com.github.zuihou.sms.enumeration.TaskStatus;
import com.github.zuihou.sms.enumeration.TemplateCodeType;
import com.github.zuihou.sms.service.SmsTaskService;
import com.github.zuihou.sms.service.SmsTemplateService;
import com.github.zuihou.sms.strategy.SmsContext;
import com.github.zuihou.sms.util.PhoneUtils;
import com.github.zuihou.utils.BizAssert;
import com.github.zuihou.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;

/**
 * <p>
 * 业务实现类
 * 发送任务
 * 所有的短息发送调用，都视为是一次短信任务，任务表只保存数据和执行状态等信息，
 * 具体的发送状态查看发送状态（#sms_send_status）表
 * </p>
 *
 * @author zuihou
 * @date 2019-08-01
 */
@Slf4j
@Service
public class SmsTaskServiceImpl extends ServiceImpl<SmsTaskMapper, SmsTask> implements SmsTaskService {
    @Resource
    private JobsTimingApi jobsTimingApi;
    @Autowired
    private SmsContext smsContext;
    @Autowired
    private SmsTemplateService smsTemplateService;

    private static String content(ProviderType providerType, String templateContent, String templateParams) {
        try {
            if (StringUtils.isNotEmpty(templateParams)) {
                JSONObject param = JSONObject.parseObject(templateParams, Feature.OrderedField);
                return processTemplate(templateContent, providerType.getRegex(), param);
            }
            return "";
        } catch (Exception e) {
            log.error("替换失败", e);
            return "";
        }
    }

    private static String processTemplate(String template, String regex, JSONObject params) {
        log.info("regex={}, template={}", regex, template);
        log.info("params={}", params.toString());
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(regex).matcher(template);
        while (m.find()) {
            String key = m.group(1);
            String value = params.getString(key);
            value = value == null ? "" : value;
            if (value.contains("$")) {
                value = value.replaceAll("\\$", "\\\\\\$");
            }
            m.appendReplacement(sb, value);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Override
    public void saveTask(SmsTask smsTask, TemplateCodeType type) {
        validAndInit(smsTask, null);

        send(smsTask, (task) -> save(task));
    }

    /**
     * 验证数据，并初始化数据
     *
     * @param smsTask
     * @param type
     */
    public void validAndInit(SmsTask smsTask, TemplateCodeType type) {
        SmsTemplate template = null;
        if (type != null) {
            template = smsTemplateService.getOne(Wrappers.<SmsTemplate>lambdaQuery()
                    .eq(SmsTemplate::getCustomCode, type.name()));
            BizAssert.notNull(template, BASE_VALID_PARAM.build("短信参数不能为空"));

            smsTask.setTemplateId(template.getId());

            if (StringUtils.isEmpty(smsTask.getTopic())) {
                smsTask.setTopic(template.getSignName());
            }
        } else {
            template = smsTemplateService.getById(smsTask.getTemplateId());
            BizAssert.notNull(template, BASE_VALID_PARAM.build("短信参数不能为空"));
        }

        //1，验证必要参数
        Set<String> phoneList = PhoneUtils.getPhone(smsTask.getReceiver());
        BizAssert.isFalse(phoneList == null || phoneList.isEmpty(), BASE_VALID_PARAM.build("接收人不能为空"));

        // 验证定时发送的时间，至少大于（当前时间+5分钟） ，是为了防止 定时调度或者是保存数据跟不上
        if (smsTask.getSendTime() != null) {
            boolean flag = LocalDateTime.now().plusMinutes(5).isBefore(smsTask.getSendTime());
            BizAssert.isTrue(flag, BASE_VALID_PARAM.build("定时发送时间至少在当前时间的5分钟之后"));
        }

        if (StringUtils.isNotEmpty(smsTask.getContent()) && smsTask.getContent().length() > 450) {
            throw new BizException(BASE_VALID_PARAM.getCode(), "发送内容不能超过500字");
        }

        String templateParams = smsTask.getTemplateParams();
        JSONObject obj = JSONObject.parseObject(templateParams, Feature.OrderedField);
        BizAssert.notNull(obj, BASE_VALID_PARAM.build("短信参数格式必须为严格的json字符串"));

        if (StringUtils.isEmpty(smsTask.getContent())) {
            smsTask.setContent(content(template.getProviderType(), template.getContent(), smsTask.getTemplateParams()));
        }

    }

    @Override
    public void update(SmsTask smsTask) {
        validAndInit(smsTask, null);

        send(smsTask, (task) -> {
            updateById(task);
            if (task.getSendTime() == null) {
                update(Wraps.<SmsTask>lbU()
                        .set(SmsTask::getSendTime, null)
                        .eq(SmsTask::getId, task.getId()));
            }
            return true;
        });
    }


    /**
     * 具体的短信任务保存操作
     *
     * @param smsTask
     * @param function 保存/修改方法
     * @return
     */
    private SmsTask send(SmsTask smsTask, Function<SmsTask, Boolean> function) {
        //1， 初始化默认参数
        smsTask.setStatus(TaskStatus.WAITING);

        //2，保存or修改 短信任务
        if (!function.apply(smsTask)) {
            return smsTask;
        }

        //保存草稿，直接返回
        if (smsTask.getDraft()) {
            return smsTask;
        }

        //3, 判断是否立即发送
        if (smsTask.getSendTime() == null) {
            smsContext.smsSend(smsTask.getId());
        } else {
            JSONObject param = new JSONObject();
            param.put("id", smsTask.getId());
            param.put("tenant", BaseContextHandler.getTenant());
            param.put("database", BaseContextHandler.getDatabase());
            //推送定时任务
            jobsTimingApi.addTimingTask(
                    XxlJobInfo.build(BizConstant.DEF_JOB_GROUP_NAME,
                            DateUtils.localDateTime2Date(smsTask.getSendTime()),
                            BizConstant.SMS_SEND_JOB_HANDLER,
                            param.toString()));
        }
        return smsTask;
    }
}
