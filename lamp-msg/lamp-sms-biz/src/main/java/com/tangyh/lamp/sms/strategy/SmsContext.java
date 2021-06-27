package com.tangyh.lamp.sms.strategy;


import com.tangyh.basic.utils.BizAssert;
import com.tangyh.lamp.sms.dao.SmsTaskMapper;
import com.tangyh.lamp.sms.dao.SmsTemplateMapper;
import com.tangyh.lamp.sms.entity.SmsTask;
import com.tangyh.lamp.sms.entity.SmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信发送上下文
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Component

public class SmsContext {
    private final Map<String, SmsStrategy> smsContextStrategyMap = new ConcurrentHashMap<>();

    private final SmsTaskMapper smsTaskMapper;
    private final SmsTemplateMapper smsTemplateMapper;

    public SmsContext(
            Map<String, SmsStrategy> strategyMap,
            SmsTaskMapper smsTaskMapper,
            SmsTemplateMapper smsTemplateMapper) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
        this.smsTaskMapper = smsTaskMapper;
        this.smsTemplateMapper = smsTemplateMapper;
    }

    /**
     * 根据任务id发送短信
     * <p>
     * 待完善的点：
     * 1， 查询次数过多，想办法优化
     *
     * @param taskId 任务id
     * @return 任务id
     */
    @Async
    public void smsSend(Long taskId) {
        SmsTask smsTask = smsTaskMapper.selectById(taskId);
        BizAssert.notNull(smsTask, "短信任务尚未保存成功");

        SmsTemplate template = smsTemplateMapper.selectById(smsTask.getTemplateId());
        BizAssert.notNull(template, "短信模板为空");

        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
        SmsStrategy smsStrategy = smsContextStrategyMap.get(template.getProviderType().name());
        BizAssert.notNull(smsStrategy, "短信供应商不存在");

        smsStrategy.sendSms(smsTask, template);
    }

}
