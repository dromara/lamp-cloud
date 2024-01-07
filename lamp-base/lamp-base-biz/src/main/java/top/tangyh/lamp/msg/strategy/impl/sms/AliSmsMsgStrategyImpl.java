package top.tangyh.lamp.msg.strategy.impl.sms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;
import top.tangyh.lamp.msg.strategy.domain.sms.AliSmsProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://next.api.aliyun.com/api/Dysmsapi/2017-05-25/SendSms?spm=a2c4g.11186623.0.0.7dd67218uJgZR6&params={}&sdkStyle=dara&lang=JAVAASYNC&tab=DEMO
 *
 * @author zuihou
 * @date 2022/7/10 0010 17:40
 */
@Slf4j
@Service("aliSmsMsgStrategyImpl")
public class AliSmsMsgStrategyImpl implements MsgStrategy {
    private final static Map<String, com.aliyun.dysmsapi20170525.Client> CACHE = new HashMap<>();

    /**
     * 使用AK&SK初始化账号Client
     *
     * @param property 短信参数
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(AliSmsProperty property) throws Exception {
        String key = StrUtil.format("{}:{}:{}:{}", property.getAccessKeyId(), property.getAccessKeySecret(),
                property.getRegionId(), property.getEndpoint());

        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }

        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(property.getAccessKeyId())
                // 您的 AccessKey Secret
                .setAccessKeySecret(property.getAccessKeySecret())
                .setEndpoint(property.getEndpoint());
        if (StrUtil.isNotEmpty(property.getRegionId())) {
            config.setRegionId(property.getRegionId());
        }
        com.aliyun.dysmsapi20170525.Client client = new com.aliyun.dysmsapi20170525.Client(config);
        CACHE.put(key, client);
        return client;
    }

    @Override
    public MsgResult exec(MsgParam msgParam) throws Exception {
        ExtendMsg extendMsg = msgParam.getExtendMsg();
        List<ExtendMsgRecipient> recipientList = msgParam.getRecipientList();
        DefMsgTemplate extendMsgTemplate = msgParam.getExtendMsgTemplate();
        Map<String, Object> propertyParams = msgParam.getPropertyParams();

        AliSmsProperty property = new AliSmsProperty();
        BeanUtil.fillBeanWithMap(propertyParams, property, true);
        property.initAndValid();
        if (property.getDebug()) {
            SendSmsResponse result = new SendSmsResponse();
            SendSmsResponseBody body = new SendSmsResponseBody();
            body.setCode("OK");
            body.setMessage("debug模式无需发送");
            result.setBody(body);
            return MsgResult.builder().result(result).build();
        }
        // 最多1000个手机
        String phoneNumbers = recipientList.stream().map(ExtendMsgRecipient::getRecipient).collect(Collectors.joining(StrPool.COMMA));

        //可自助调整超时时间 也可以配置在 propertyParams 中
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        com.aliyun.dysmsapi20170525.Client client = createClient(property);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumbers);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(extendMsgTemplate.getSign());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(extendMsgTemplate.getTemplateCode());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        Map<String, String> map = parseParam(extendMsg.getParam());
        request.setTemplateParam(JsonUtil.toJson(map));

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(String.valueOf(extendMsg.getId()));

        log.info("阿里短信发送参数={}", JSONObject.toJSONString(request));
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = client.sendSms(request);

        log.info("阿里短信发送结果={}", JSONObject.toJSONString(sendSmsResponse));
        return MsgResult.builder().result(sendSmsResponse).build();
    }


    @Override
    public boolean isSuccess(MsgResult result) {
        SendSmsResponse sendResult = (SendSmsResponse) result.getResult();
        return sendResult.getBody() != null && "OK".equals(sendResult.getBody().getCode());
    }

}
