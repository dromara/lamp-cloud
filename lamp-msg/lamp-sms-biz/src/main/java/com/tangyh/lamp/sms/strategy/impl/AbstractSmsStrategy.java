package com.tangyh.lamp.sms.strategy.impl;


import com.tangyh.basic.base.R;
import com.tangyh.lamp.sms.dao.SmsTaskMapper;
import com.tangyh.lamp.sms.entity.SmsSendStatus;
import com.tangyh.lamp.sms.entity.SmsTask;
import com.tangyh.lamp.sms.entity.SmsTemplate;
import com.tangyh.lamp.sms.enumeration.TaskStatus;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import com.tangyh.lamp.sms.strategy.SmsStrategy;
import com.tangyh.lamp.sms.strategy.domain.SmsDO;
import com.tangyh.lamp.sms.strategy.domain.SmsResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 抽象短信策略
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Slf4j

@RequiredArgsConstructor
public abstract class AbstractSmsStrategy implements SmsStrategy {

    private final SmsTaskMapper smsTaskMapper;
    private final SmsSendStatusService smsSendStatusService;

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
            List<SmsSendStatus> list = smsSendStatusService.listByTaskId(task.getId());

            list.stream().forEach(smsSendStatus -> {
                //发送
                SmsResult result = send(SmsDO.builder()
                        .taskId(task.getId()).telNum(smsSendStatus.getTelNum()).appId(appId).appSecret(appSecret)
                        .signName(signName).templateCode(templateCode).endPoint(endPoint)
                        .templateParams(templateParam)
                        .build());

                log.info("phone={}, result={}", smsSendStatus.getTelNum(), result);
                smsSendStatus.setSendStatus(result.getSendStatus())
                        .setBizId(result.getBizId()).setExt(result.getExt())
                        .setCode(result.getCode()).setMessage(result.getMessage())
                        .setFee(result.getFee());
            });

            if (!list.isEmpty()) {
                smsSendStatusService.updateBatchById(list);
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
     * @param smsDO 短信参数
     * @return 短信返回值
     */
    protected abstract SmsResult send(SmsDO smsDO);
}
