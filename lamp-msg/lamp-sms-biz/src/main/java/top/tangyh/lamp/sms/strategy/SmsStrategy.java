package top.tangyh.lamp.sms.strategy;


import cn.hutool.core.util.StrUtil;
import top.tangyh.basic.base.R;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.lamp.sms.entity.SmsTask;
import top.tangyh.lamp.sms.entity.SmsTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 抽象策略类: 发送短信
 * <p>
 * 每个短信 服务商都有自己的 发送短信策略(sdk)
 *
 * @author zuihou
 * @date 2019-05-15
 */
public interface SmsStrategy {
    /**
     * 发送短信
     *
     * @param task     短信任务
     * @param template 短信模版
     * @return 任务id
     */
    R<String> sendSms(SmsTask task, SmsTemplate template);

    default Map<String, String> parseParam(String param) {
        Map<String, String> map = new LinkedHashMap<>();
        if (StrUtil.isNotEmpty(param)) {
            List<Kv> list = JsonUtil.parseArray(param, Kv.class);
            for (Kv kv : list) {
                map.put(kv.getKey(), kv.getValue());
            }
        }
        return map;
    }
}
