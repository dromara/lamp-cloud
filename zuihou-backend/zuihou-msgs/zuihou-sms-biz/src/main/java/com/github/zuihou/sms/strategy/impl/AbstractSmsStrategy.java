package com.github.zuihou.sms.strategy.impl;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.zuihou.base.R;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.enumeration.ProviderType;
import com.github.zuihou.sms.enumeration.TaskStatus;
import com.github.zuihou.sms.service.SmsProviderService;
import com.github.zuihou.sms.service.SmsSendStatusService;
import com.github.zuihou.sms.service.SmsTemplateService;
import com.github.zuihou.sms.strategy.SmsStrategy;
import com.github.zuihou.sms.strategy.domain.SmsDO;
import com.github.zuihou.sms.strategy.domain.SmsResult;
import com.github.zuihou.sms.util.PhoneUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Slf4j
public abstract class AbstractSmsStrategy implements SmsStrategy {


    @Autowired
    private SmsTaskMapper smsTaskMapper;
    @Autowired
    private SmsProviderService smsProviderService;
    @Autowired
    private SmsTemplateService smsTemplateService;
    @Autowired
    private SmsSendStatusService smsSendStatusService;

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
    public R<String> sendSms(Long taskId) {
        SmsTask task = smsTaskMapper.selectById(taskId);
        SmsProvider provider = smsProviderService.getById(task.getProviderId());
        SmsTemplate template = smsTemplateService.getById(task.getTemplateId());

        String appId = provider.getAppId();
        String appSecret = provider.getAppSecret();
        String endPoint = provider.getUrl();

        // 发送使用签名的调用ID
        String signName = template.getSignName();
        //参数json
        String templateParam = task.getTemplateParams();
        String templateCode = template.getTemplateCode();
        String content = task.getContext();

        log.info("appId={}, appSecret={}, endPoint={},signName={}, templateCode={}", appId, appSecret, endPoint, signName, templateCode);
        log.info("templateParam={}", templateParam);

        try {
            //解析接受者手机号
            Set<String> phoneList = PhoneUtils.getPhone(task.getReceiver());

            List<SmsSendStatus> list = phoneList.stream().map((phone) -> {
                //发送
                SmsResult result = send(SmsDO.builder()
                        .taskId(taskId).phone(phone).appId(appId).appSecret(appSecret)
                        .signName(signName).templateCode(templateCode).endPoint(endPoint).templateParams(templateParam)
                        .build());

                log.info("phone={}, result={}", phone, result);
                return SmsSendStatus.builder()
                        .taskId(taskId).receiver(phone).sendStatus(result.getSendStatus())
                        .bizId(result.getBizId()).ext(result.getExt())
                        .code(result.getCode()).message(result.getMessage()).fee(result.getFee()).build();
            }).collect(Collectors.toList());

            if (!list.isEmpty()) {
                smsSendStatusService.saveBatch(list);
            }


            if (StringUtils.isEmpty(task.getContext())) {
                content = content(provider.getProviderType(), template.getContent(), task.getTemplateParams());
            }

        } catch (Exception e) {
            log.warn("短信发送任务发送失败", e);
            updateStatus(taskId, TaskStatus.FAIL, content);
            return R.success(String.valueOf(taskId));
        }

        updateStatus(taskId, TaskStatus.SUCCESS, content);
        return R.success(String.valueOf(taskId));
    }

    public void updateStatus(Long taskId, TaskStatus success, String content) {
        SmsTask updateTask = new SmsTask();
        updateTask.setId(taskId);
        updateTask.setStatus(success);
        updateTask.setContext(content);
        smsTaskMapper.updateById(updateTask);
    }


    /**
     * 子类执行具体的发送任务
     *
     * @param smsDO
     * @return
     */
    protected abstract SmsResult send(SmsDO smsDO);
}
