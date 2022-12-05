package top.tangyh.lamp.sms.strategy.impl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.lamp.sms.dao.SmsTaskMapper;
import top.tangyh.lamp.sms.enumeration.ProviderType;
import top.tangyh.lamp.sms.service.SmsSendStatusService;
import top.tangyh.lamp.sms.strategy.domain.SmsDO;
import top.tangyh.lamp.sms.strategy.domain.SmsResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 腾讯发送短信实现类
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Component("TENCENT")
@Slf4j
public class SmsTencentStrategy extends AbstractSmsStrategy {

    private static final Map<String, String> ERROR_CODE_MAP = new HashMap<>(50);

    static {
        ERROR_CODE_MAP.put("1001", "sig 校验失败");
        ERROR_CODE_MAP.put("1002", "短信/语音内容中含有敏感词");
        ERROR_CODE_MAP.put("1003", "请求包体没有 sig 字段或 sig 为空");
        ERROR_CODE_MAP.put("1004", "请求包解析失败，通常情况下是由于没有遵守 API 接口说明规范导致的");
        ERROR_CODE_MAP.put("1006", "请求没有权限");
        ERROR_CODE_MAP.put("1007", "其他错误");
        ERROR_CODE_MAP.put("1008", "请求下发短信/语音超时");
        ERROR_CODE_MAP.put("1009", "请求 IP 不在白名单中");
        ERROR_CODE_MAP.put("1011", "不存在该 REST API 接口");
        ERROR_CODE_MAP.put("1012", "签名格式错误或者签名未审批");
        ERROR_CODE_MAP.put("1013", "下发短信/语音命中了频率限制策略");
        ERROR_CODE_MAP.put("1014", "模版未审批或请求的内容与审核通过的模版内容不匹配");
        ERROR_CODE_MAP.put("1015", "手机号在阻止列表库中，通常是用户退订或者命中运营商阻止列表导致的");
        ERROR_CODE_MAP.put("1016", "手机号格式错误");
        ERROR_CODE_MAP.put("1017", "请求的短信内容太长");
        ERROR_CODE_MAP.put("1018", "语音验证码格式错误");
        ERROR_CODE_MAP.put("1019", "sdk appId 不存在");
        ERROR_CODE_MAP.put("1020", "sdk appId 已禁用");
        ERROR_CODE_MAP.put("1021", "请求发起时间不正常，通常是由于您的服务器时间与腾讯云服务器时间差异超过10分钟导致的");
        ERROR_CODE_MAP.put("1022", "业务短信日下发条数超过设定的上限");
        ERROR_CODE_MAP.put("1023", "单个手机号30秒内下发短信条数超过设定的上限");
        ERROR_CODE_MAP.put("1024", "单个手机号1小时内下发短信条数超过设定的上限");
        ERROR_CODE_MAP.put("1025", "单个手机号日下发短信条数超过设定的上限");
        ERROR_CODE_MAP.put("1026", "单个手机号下发相同内容超过设定的上限");
        ERROR_CODE_MAP.put("1029", "营销短信发送时间限制");
        ERROR_CODE_MAP.put("1030", "不支持该请求");
        ERROR_CODE_MAP.put("1031", "套餐包余量不足");
        ERROR_CODE_MAP.put("1032", "个人用户没有发营销短信的权限");
        ERROR_CODE_MAP.put("1033", "欠费被停止服务");
        ERROR_CODE_MAP.put("1034", "群发请求里既有国内手机号也有国际手机号");
        ERROR_CODE_MAP.put("1036", "单个模板变量字符数超过12个");
        ERROR_CODE_MAP.put("1045", "不支持该地区短信下发");
        ERROR_CODE_MAP.put("1046", "调用群发 API 接口单次提交的手机号个数超过200个");
        ERROR_CODE_MAP.put("1047", "国际短信日下发条数被限制");
        ERROR_CODE_MAP.put("60008", "处理请求超时");
    }

    public SmsTencentStrategy(SmsTaskMapper smsTaskMapper, SmsSendStatusService smsSendStatusService) {
        super(smsTaskMapper, smsSendStatusService);
    }

    @Override
    protected SmsResult send(SmsDO smsDO) {
        try {

            //单次请求最多支持200个手机号且要求全为境内手机号或全为境外手机号。
            String[] phoneNumbers = new String[]{smsDO.getTelNum()};

            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred = new Credential(smsDO.getAppId(), smsDO.getAppSecret());

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* 指定接入地域域名，默认就近地域接入域名为 sms.tencentcloudapi.com ，也支持指定地域域名访问，例如广州地域的域名为 sms.ap-guangzhou.tencentcloudapi.com */
//            httpProfile.setEndpoint(property.getEndpoint());

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);

            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，支持的地域列表参考 https://cloud.tencent.com/document/api/382/52071#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
//            req.setSmsSdkAppid(smsDO.getAppId());

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名 */
            // 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
            req.setSign(smsDO.getSignName());

            /* 模板 ID: 必须填写已审核通过的模板 ID */
            // 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
            req.setTemplateID(smsDO.getTemplateCode());

            String paramStr = smsDO.getTemplateParams();
            List<Kv> param = JsonUtil.parseArray(paramStr, Kv.class);

            List<String> paramList = new ArrayList<>();
            for (Kv kv : param) {
                paramList.add(kv.getValue());
            }
            /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
            req.setTemplateParamSet(paramList.stream().toArray(String[]::new));

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
            req.setPhoneNumberSet(phoneNumbers);

            /* 用户的 session 内容（无需要可忽略）: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            req.setSessionContext(String.valueOf(smsDO.getTaskId()));

            /* 短信码号扩展号（无需要可忽略）: 默认未开通，如需开通请联系 [腾讯云短信小助手] */
//            String extendCode = "";
//            req.setExtendCode(extendCode);

            /* 国际/港澳台短信 SenderId（无需要可忽略）: 国内短信填空，默认未开通，如需开通请联系 [腾讯云短信小助手] */
//            String senderid = "";
//            req.setSenderId(senderid);

            SendSmsResponse sendSmsResponse = client.SendSms(req);

            SendStatus[] sendStatusSet = sendSmsResponse.getSendStatusSet();

            if (sendStatusSet != null && sendStatusSet.length > 0) {
                SendStatus sendStatus = sendStatusSet[0];

                return SmsResult.build(ProviderType.TENCENT, sendStatus.getCode(),
                        sendStatus.getSerialNo(), sendSmsResponse.getRequestId(),
                        ERROR_CODE_MAP.getOrDefault(sendStatus.getMessage(), sendStatus.getMessage()), Integer.parseInt(String.valueOf(sendStatus.getFee())));
            }

            return SmsResult.fail("发送失败");
//
//            //初始化单发
//            SmsSingleSender singleSender = new SmsSingleSender(Convert.toInt(smsDO.getAppId(), 0), smsDO.getAppSecret());
//
//
//            String paramStr = smsDO.getTemplateParams();
//            List<Kv> param = JsonUtil.parseArray(paramStr, Kv.class);
//
//            ArrayList<String> paramList = new ArrayList<>();
//            for (Kv kv : param) {
//                paramList.add(kv.getValue());
//            }
//
//            SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", smsDO.getTelNum(),
//                    Convert.toInt(smsDO.getTemplateCode()), paramList, smsDO.getSignName(), "", "");
//            log.info("tencent result={}", singleSenderResult.toString());
//            return SmsResult.build(ProviderType.TENCENT, String.valueOf(singleSenderResult.result),
//                    singleSenderResult.sid, singleSenderResult.ext,
//                    ERROR_CODE_MAP.getOrDefault(String.valueOf(singleSenderResult.result), singleSenderResult.errMsg), singleSenderResult.fee);
        } catch (Exception e) {
            log.error(e.getMessage());
            return SmsResult.fail(e.getMessage());
        }
    }

}
