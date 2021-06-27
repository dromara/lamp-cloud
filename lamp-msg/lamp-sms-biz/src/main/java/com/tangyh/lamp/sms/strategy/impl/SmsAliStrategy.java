package com.tangyh.lamp.sms.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tangyh.lamp.sms.dao.SmsTaskMapper;
import com.tangyh.lamp.sms.enumeration.ProviderType;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import com.tangyh.lamp.sms.strategy.domain.SmsDO;
import com.tangyh.lamp.sms.strategy.domain.SmsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ali 发送短信实现类
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Component("ALI")
@Slf4j
public class SmsAliStrategy extends AbstractSmsStrategy {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    public SmsAliStrategy(SmsTaskMapper smsTaskMapper, SmsSendStatusService smsSendStatusService) {
        super(smsTaskMapper, smsSendStatusService);
    }

    @Override
    protected SmsResult send(SmsDO smsDO) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsDO.getAppId(), smsDO.getAppSecret());
            DefaultProfile.addEndpoint("cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(smsDO.getTelNum());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(smsDO.getSignName());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(smsDO.getTemplateCode());
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(smsDO.getTemplateParams());

            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId(String.valueOf(smsDO.getTaskId()));

            log.info("阿里短信发送参数={}", JSONObject.toJSONString(request));
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            log.info("阿里短信发送结果={}", JSONObject.toJSONString(sendSmsResponse));
            return SmsResult.build(ProviderType.ALI, sendSmsResponse.getCode(), sendSmsResponse.getBizId(),
                    sendSmsResponse.getRequestId(), sendSmsResponse.getMessage(), 0);
        } catch (ClientException e) {
            log.warn("阿里短信发送失败:" + smsDO.getTelNum(), e);
            return SmsResult.fail(e.getMessage());
        }

    }
}
