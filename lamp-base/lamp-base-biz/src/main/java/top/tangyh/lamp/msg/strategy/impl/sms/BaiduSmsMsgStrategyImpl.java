package top.tangyh.lamp.msg.strategy.impl.sms;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV3Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;
import top.tangyh.lamp.msg.strategy.domain.sms.BaiduSmsProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 仅支持jdk7 和 jdk8，其他版本请使用API调用
 * https://cloud.baidu.com/doc/SMS/s/dkioav2od
 *
 * @author zuihou
 * @date 2022/7/10 0010 17:40
 */
@Slf4j
@Service("baiduSmsMsgStrategyImpl")
public class BaiduSmsMsgStrategyImpl implements MsgStrategy {
    @Override
    public MsgResult exec(MsgParam msgParam) {
        ExtendMsg extendMsg = msgParam.getExtendMsg();
        List<ExtendMsgRecipient> recipientList = msgParam.getRecipientList();
        DefMsgTemplate extendMsgTemplate = msgParam.getExtendMsgTemplate();
        Map<String, Object> propertyParams = msgParam.getPropertyParams();

        BaiduSmsProperty property = new BaiduSmsProperty();
        BeanUtil.fillBeanWithMap(propertyParams, property, true);
        property.initAndValid();

        if (property.getDebug()) {
            SendMessageV3Response result = new SendMessageV3Response();
            result.setCode("1000");
            result.setMessage("Debug模式，无需发送！");
            return MsgResult.builder().result(result).build();
        }
        String phoneNumbers = recipientList.stream().map(ExtendMsgRecipient::getRecipient).collect(Collectors.joining(StrPool.COMMA));
        // ak、sk等config
        SmsClientConfiguration config = new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(property.getAccessKeyId(), property.getSecretKey()));
        config.setEndpoint(property.getEndPoint());

        // 实例化发送客户端
        SmsClient smsClient = new SmsClient(config);
        // 若模板内容为：您的验证码是${code},在${time}分钟内输入有效

        //实例化请求对象
        SendMessageV3Request request = new SendMessageV3Request();

        Map<String, String> map = parseParam(extendMsg.getParam());

        request.setSignatureId(extendMsgTemplate.getSign());
        request.setTemplate(extendMsgTemplate.getTemplateCode());
        request.setContentVar(map);
        request.setCustom(String.valueOf(extendMsg.getId()));

        // 若 一次性发送200个手机号以上，请修改拆分后多次调用。
        request.setMobile(phoneNumbers);
        // 发送请求
        SendMessageV3Response response = smsClient.sendMessage(request);

        log.info("百度发送短信返回值={}", JSONObject.toJSONString(response));

        return MsgResult.builder().result(response).build();
    }

    @Override
    public boolean isSuccess(MsgResult result) {
        SendMessageV3Response sendResult = (SendMessageV3Response) result.getResult();
        return "1000".equals(sendResult.getCode());
    }
}
