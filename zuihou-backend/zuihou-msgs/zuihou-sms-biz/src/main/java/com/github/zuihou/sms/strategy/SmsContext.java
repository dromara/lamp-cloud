package com.github.zuihou.sms.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zuihou.base.R;
import com.github.zuihou.sms.dao.SmsProviderMapper;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.dao.SmsTemplateMapper;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.entity.SmsTemplate;
import com.github.zuihou.utils.BizAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;

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
    private final SmsProviderMapper smsProviderMapper;
    private final SmsTemplateMapper smsTemplateMapper;

    @Autowired
    public SmsContext(
            Map<String, SmsStrategy> strategyMap,
//            @Qualifier("smsAliStrategy") SmsStrategy smsAliStrategy,
//                      @Qualifier("smsTencentStrategy") SmsStrategy smsTencentStrategy,
//                      @Qualifier("smsBaiduStrategy") SmsStrategy smsBaiduStrategy,
            SmsTaskMapper smsTaskMapper,
            SmsProviderMapper smsProviderMapper,
            SmsTemplateMapper smsTemplateMapper) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
        this.smsTaskMapper = smsTaskMapper;
        this.smsProviderMapper = smsProviderMapper;
        this.smsTemplateMapper = smsTemplateMapper;
//        smsContextStrategyMap.put(ProviderType.ALI.toString(), smsAliStrategy);
//        smsContextStrategyMap.put(ProviderType.TENCENT.toString(), smsTencentStrategy);
//        smsContextStrategyMap.put(ProviderType.BAIDU.toString(), smsBaiduStrategy);
    }

    /**
     * 根据任务id发送短信
     * <p>
     * 待完善的点：
     * 1， 查询次数过多，想办法优化
     *
     * @param taskId
     * @return
     */
    public String smsSend(Long taskId) {
        SmsTask smsTask = smsTaskMapper.selectById(taskId);
        BizAssert.notNull(smsTask, BASE_VALID_PARAM.build("短信任务尚未保存成功"));

        SmsProvider smsProvider = smsProviderMapper.selectById(smsTask.getProviderId());
        BizAssert.notNull(smsTask, BASE_VALID_PARAM.build("短信供应商不存在"));

        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
        SmsStrategy smsStrategy = smsContextStrategyMap.get(smsProvider.getProviderType().name());
        BizAssert.notNull(smsStrategy, BASE_VALID_PARAM.build("短信供应商不存在"));

        SmsTemplate template = smsTemplateMapper.selectById(smsTask.getTemplateId());

        R<String> result = smsStrategy.sendSms(smsTask, smsProvider, template);
        if (result.getIsSuccess()) {
            return result.getData();
        }
        return null;
    }

}
