package com.github.zuihou.sms.strategy.impl;

import com.github.zuihou.base.R;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsSendStatus;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.sms.enumeration.TaskStatus;
import com.github.zuihou.sms.service.SmsSendStatusService;
import com.github.zuihou.sms.strategy.SmsStrategy;
import com.github.zuihou.sms.strategy.domain.SmsDO;
import com.github.zuihou.sms.strategy.domain.SmsResult;
import com.github.zuihou.sms.util.PhoneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 抽象短信策略
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Slf4j
public abstract class AbstractSmsStrategy implements SmsStrategy {

    @Autowired
    private SmsTaskMapper smsTaskMapper;
    @Autowired
    private SmsSendStatusService smsSendStatusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<String> sendSms(SmsTask task, SmsTemplate template) {
        String appId = template.getAppId();
        String appSecret = template.getAppSecret();
        String endPoint = template.getUrl();

        // 发送使用签名的调用ID
        String signName = template.getSignName();
        //参数json
        String templateParam = task.getTemplateParams();
        String templateCode = template.getTemplateCode();

        log.info("appId={}, appSecret={}, endPoint={},signName={}, templateCode={}", appId, appSecret, endPoint, signName, templateCode);
        log.info("templateParam={}", templateParam);

        try {
            //解析接受者手机号
            Set<String> phoneList = PhoneUtils.getPhone(task.getReceiver());

            List<SmsSendStatus> list = phoneList.stream().map((phone) -> {
                //发送
                SmsResult result = send(SmsDO.builder()
                        .taskId(task.getId()).phone(phone).appId(appId).appSecret(appSecret)
                        .signName(signName).templateCode(templateCode).endPoint(endPoint).templateParams(templateParam)
                        .build());

                log.info("phone={}, result={}", phone, result);
                return SmsSendStatus.builder()
                        .taskId(task.getId()).receiver(phone).sendStatus(result.getSendStatus())
                        .bizId(result.getBizId()).ext(result.getExt())
                        .code(result.getCode()).message(result.getMessage()).fee(result.getFee()).build();
            }).collect(Collectors.toList());

            if (!list.isEmpty()) {
                smsSendStatusService.saveBatch(list);
            }
        } catch (Exception e) {
            log.warn("短信发送任务发送失败", e);
            updateStatus(task.getId(), TaskStatus.FAIL);
            return R.success(String.valueOf(task.getId()));
        }

        updateStatus(task.getId(), TaskStatus.SUCCESS);
        return R.success(String.valueOf(task.getId()));
    }

    public void updateStatus(Long taskId, TaskStatus success) {
        SmsTask updateTask = new SmsTask();
        updateTask.setId(taskId);
        updateTask.setStatus(success);
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
