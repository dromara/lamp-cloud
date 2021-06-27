package com.tangyh.lamp.sms.strategy.impl;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.tangyh.lamp.sms.dao.SmsTaskMapper;
import com.tangyh.lamp.sms.enumeration.ProviderType;
import com.tangyh.lamp.sms.service.SmsSendStatusService;
import com.tangyh.lamp.sms.strategy.domain.SmsDO;
import com.tangyh.lamp.sms.strategy.domain.SmsResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            //初始化单发
            SmsSingleSender singleSender = new SmsSingleSender(Convert.toInt(smsDO.getAppId(), 0), smsDO.getAppSecret());

            String paramStr = smsDO.getTemplateParams();

            JSONObject param = JSONObject.parseObject(paramStr, Feature.OrderedField);

            Set<Map.Entry<String, Object>> sets = param.entrySet();

            ArrayList<String> paramList = new ArrayList<>();
            for (Map.Entry<String, Object> val : sets) {
                paramList.add(val.getValue().toString());
            }

            SmsSingleSenderResult singleSenderResult = singleSender.sendWithParam("86", smsDO.getTelNum(),
                    Convert.toInt(smsDO.getTemplateCode()), paramList, smsDO.getSignName(), "", "");
            log.info("tencent result={}", singleSenderResult.toString());
            return SmsResult.build(ProviderType.TENCENT, String.valueOf(singleSenderResult.result),
                    singleSenderResult.sid, singleSenderResult.ext,
                    ERROR_CODE_MAP.getOrDefault(String.valueOf(singleSenderResult.result), singleSenderResult.errMsg), singleSenderResult.fee);
        } catch (Exception e) {
            log.error(e.getMessage());
            return SmsResult.fail(e.getMessage());
        }
    }

}
