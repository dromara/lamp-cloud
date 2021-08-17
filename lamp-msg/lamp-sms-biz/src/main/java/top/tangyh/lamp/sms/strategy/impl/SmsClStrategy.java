package top.tangyh.lamp.sms.strategy.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.lamp.sms.dao.SmsTaskMapper;
import top.tangyh.lamp.sms.enumeration.ProviderType;
import top.tangyh.lamp.sms.service.SmsSendStatusService;
import top.tangyh.lamp.sms.strategy.domain.SmsDO;
import top.tangyh.lamp.sms.strategy.domain.SmsResult;
import top.tangyh.lamp.sms.strategy.domain.cl.ClSendResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创蓝 发送短信实现类
 *
 * @author zuihou
 * @date 2018/12/20
 */
@Component("CL")
@Slf4j
public class SmsClStrategy extends AbstractSmsStrategy {
    //带变量发送url
    private static final String VARIABLE_URL = "http://smssh1.253.com/msg/variable/json";
    //短信下发
    private static final String SEND_URL = "http://smssh1.253.com/msg/send/json";

    public SmsClStrategy(SmsTaskMapper smsTaskMapper, SmsSendStatusService smsSendStatusService) {
        super(smsTaskMapper, smsSendStatusService);
    }

    private static ClSendResult sendSmsByPost(String path, String postContent) throws IOException {
        ClSendResult clSendResult = new ClSendResult();
        URL url = new URL(path);
        HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
        httpUrlConnection.setRequestMethod("POST");
        httpUrlConnection.setConnectTimeout(10000);
        httpUrlConnection.setReadTimeout(10000);
        httpUrlConnection.setDoOutput(true);
        httpUrlConnection.setDoInput(true);
        httpUrlConnection.setRequestProperty("Charset", "UTF-8");
        httpUrlConnection.setRequestProperty("Content-Type", "application/json");
        httpUrlConnection.connect();
        OutputStream os = httpUrlConnection.getOutputStream();
        os.write(postContent.getBytes("UTF-8"));
        os.flush();
        StringBuilder sb = new StringBuilder();
        int httpRspCode = httpUrlConnection.getResponseCode();
        if (httpRspCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpUrlConnection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            clSendResult = JsonUtil.parse(sb.toString(), ClSendResult.class);
        } else {
            clSendResult.setCode("-1");
            clSendResult.setErrorMsg("服务器网络异常或地址错误");
        }
        return clSendResult;
    }

    /**
     * 只支持模板发送
     *
     * @param smsDO 短信参数
     * @return
     */
    @Override
    protected SmsResult send(SmsDO smsDO) {
        try {
            Map<String, Object> map = new HashMap();
            map.put("account", smsDO.getAppId());
            map.put("password", smsDO.getAppSecret());
            String endPoint = StrUtil.isEmpty(smsDO.getEndPoint()) ? VARIABLE_URL : smsDO.getEndPoint();
            if (VARIABLE_URL.equals(endPoint)) {
                StringBuilder sb = new StringBuilder(smsDO.getTelNum());
                List<Kv> params = JsonUtil.parseArray(smsDO.getTemplateParams(), Kv.class);
                params.forEach(item -> {
                    sb.append(",").append(item.getValue());
                });
                // 变量参数使用短信参数
                map.put("params", sb.toString());
                // 内容
                map.put("msg", smsDO.getTemplateContent());
            } else {
                // 普通短信使用 手机号
                map.put("phone", smsDO.getTelNum());
                // 内容
                map.put("msg", smsDO.getSmsContent());
            }
            //是否需要状态报告
            map.put("report", "true");
            // 业务id
            map.put("uid", String.valueOf(smsDO.getTaskId()));
            //自定义扩展码
            map.put("extend", "1");

            ClSendResult ret = sendSmsByPost(endPoint, JsonUtil.toJson(map));

            return SmsResult.build(ProviderType.CL, ret.getCode(), ret.getMsgId(), "", ret.getErrorMsg(), 0);
        } catch (Exception e) {
            log.warn("阿里短信发送失败:" + smsDO.getTelNum(), e);
            return SmsResult.fail(e.getMessage());
        }

    }

}
