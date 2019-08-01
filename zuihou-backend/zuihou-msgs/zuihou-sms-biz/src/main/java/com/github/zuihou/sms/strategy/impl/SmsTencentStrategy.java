package com.github.zuihou.sms.strategy.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.zuihou.sms.enumeration.ProviderType;
import com.github.zuihou.sms.strategy.domain.SmsDO;
import com.github.zuihou.sms.strategy.domain.SmsResult;
import com.github.zuihou.utils.NumberHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Component
@Slf4j
public class SmsTencentStrategy extends AbstractSmsStrategy {
    @Override
    protected SmsResult send(SmsDO smsDO) {
        try {
            //初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(NumberHelper.intValueOf0(smsDO.getAppId()), smsDO.getAppSecret());

            String paramStr = smsDO.getTemplateParams();

            JSONObject param = JSONObject.parseObject(paramStr, Feature.OrderedField);

            Set<Map.Entry<String, Object>> sets = param.entrySet();

            ArrayList<String> paramList = new ArrayList<>();
            for (Map.Entry<String, Object> val : sets) {
                paramList.add(val.getValue().toString());
            }

            SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", smsDO.getPhone(),
                    NumberHelper.intValueOf0(smsDO.getTemplateCode()), paramList, smsDO.getSignName(), "", "");
            log.info(singleSenderResult.toString());
            return SmsResult.build(ProviderType.TENCENT, String.valueOf(singleSenderResult.result),
                    singleSenderResult.sid, singleSenderResult.ext, singleSenderResult.errMsg, singleSenderResult.fee);
        } catch (Exception e) {
            log.error(e.getMessage());
            return SmsResult.fail(e.getMessage());
        }
    }
}
