package com.github.zuihou.sms.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.zuihou.base.R;
import com.github.zuihou.sms.dao.SmsProviderMapper;
import com.github.zuihou.sms.dao.SmsTaskMapper;
import com.github.zuihou.sms.entity.SmsProvider;
import com.github.zuihou.sms.entity.SmsTask;
import com.github.zuihou.sms.enumeration.ProviderType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.github.zuihou.exception.code.ExceptionCode.BASE_VALID_PARAM;
import static com.github.zuihou.utils.BizAssert.assertNotNull;

/**
 * 短信发送上下文
 *
 * @author zuihou
 * @date 2019-05-15
 */
@Component
public class SmsContext {
    private static Map<String, SmsStrategy> smsContextStrategyMap = new ConcurrentHashMap<>();

    private final SmsTaskMapper smsTaskMapper;
    private final SmsProviderMapper smsProviderMapper;

    @Autowired
    public SmsContext(@Qualifier("smsAliStrategy") SmsStrategy smsAliStrategy,
                      @Qualifier("smsTencentStrategy") SmsStrategy smsTencentStrategy,
                      @Qualifier("smsBaiduStrategy") SmsStrategy smsBaiduStrategy,
                      SmsTaskMapper smsTaskMapper,
                      SmsProviderMapper smsProviderMapper) {
        this.smsTaskMapper = smsTaskMapper;
        this.smsProviderMapper = smsProviderMapper;
        smsContextStrategyMap.put(ProviderType.ALI.toString(), smsAliStrategy);
        smsContextStrategyMap.put(ProviderType.TENCENT.toString(), smsTencentStrategy);
        smsContextStrategyMap.put(ProviderType.BAIDU.toString(), smsBaiduStrategy);
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
        assertNotNull(BASE_VALID_PARAM.build("短信任务尚未保存成功"), smsTask);

        SmsProvider smsProvider = smsProviderMapper.selectById(smsTask.getProviderId());
        assertNotNull(BASE_VALID_PARAM.build("短信供应商不存在"), smsTask);

        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
        SmsStrategy smsStrategy = smsContextStrategyMap.get(smsProvider.getProviderType().toString());
        assertNotNull(BASE_VALID_PARAM.build("短信供应商不存在"), smsStrategy);

        R<String> result = smsStrategy.sendSms(taskId);
        if (result.getIsSuccess()) {
            return result.getData();
        }
        return null;
    }

}
