package top.tangyh.lamp.msg.strategy.impl.sms;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;
import top.tangyh.lamp.msg.strategy.MsgStrategy;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;
import top.tangyh.lamp.msg.strategy.domain.sms.ClSendResult;
import top.tangyh.lamp.msg.strategy.domain.sms.ClSmsProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zuihou
 * @date 2022/7/10 0010 21:49
 */
@Slf4j
@Service("clSmsMsgStrategyImpl")
public class ClSmsMsgStrategyImpl implements MsgStrategy {

    private final static Pattern REG = Pattern.compile("[$][{][a-zA-Z\\d\\\\_$]+[}]");
    private final static String VAR = "{$var}";

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
        os.write(postContent.getBytes(StandardCharsets.UTF_8));
        os.flush();
        StringBuilder sb = new StringBuilder();
        int httpRspCode = httpUrlConnection.getResponseCode();
        if (httpRspCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpUrlConnection.getInputStream(), StandardCharsets.UTF_8));
            String line;
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

    @Override
    public MsgResult exec(MsgParam msgParam) throws Exception {
        ExtendMsg extendMsg = msgParam.getExtendMsg();
        List<ExtendMsgRecipient> recipientList = msgParam.getRecipientList();
        DefMsgTemplate extendMsgTemplate = msgParam.getExtendMsgTemplate();
        Map<String, Object> propertyParams = msgParam.getPropertyParams();

        ClSmsProperty property = new ClSmsProperty();
        BeanUtil.fillBeanWithMap(propertyParams, property, true);
        property.initAndValid();
        if (property.getDebug()) {
            ClSendResult result = new ClSendResult();
            result.setCode("0");
            result.setErrorMsg("Debug模式，无需发送！");
            return MsgResult.builder().result(result).build();
        }

        Map<String, Object> map = new HashMap();
        map.put("account", property.getAccount());
        map.put("password", property.getPassword());
        String endPoint = property.getEndPoint();
        String variableEndPoint = property.getVariableEndPoint();

        String phoneNumbers = recipientList.stream().map(ExtendMsgRecipient::getRecipient).collect(Collectors.joining(StrPool.COMMA));

        if (property.getVariable()) {
            StringBuilder param = new StringBuilder();
            recipientList.forEach(recipient -> {
                // 手机
                param.append(recipient.getRecipient());
                List<Kv> params = JsonUtil.parseArray(extendMsg.getParam(), Kv.class);
                // 参数遍历
                params.forEach(item -> param.append(StrPool.COMMA).append(item.getValue()));
                // 多账号分割
                param.append(StrPool.SEMICOLON);
            });
            String params = param.toString();
            if (params.endsWith(StrPool.SEMICOLON)) {
                params = StrUtil.subBefore(param.toString(), StrPool.SEMICOLON, true);
            }

            // 变量参数使用短信参数
            map.put("params", params);

            // 短信内容,长度不能超过536个字符,占位符使用{$var}
            map.put("msg", buildVariableMsg(extendMsgTemplate.getSign(), extendMsgTemplate.getContent()));
        } else {
            // 普通短信使用 手机号
            map.put("phone", phoneNumbers);
            map.put("msg", buildMsg(extendMsgTemplate.getSign(), extendMsg.getContent()));
        }
        //是否需要状态报告
        map.put("report", "false");
        // 业务id
        map.put("uid", String.valueOf(extendMsg.getId()));
        //自定义扩展码
        map.put("extend", "1");

        String postParam = JsonUtil.toJson(map);
        log.info("请求参数={}", postParam);
        ClSendResult result = sendSmsByPost(property.getVariable() ? variableEndPoint : endPoint, postParam);
        return MsgResult.builder().result(result).build();
    }

    private String buildMsg(String sign, String msg) {
        return StrUtil.format("【{}】{}", sign, replaceMsg(msg));
    }

    private String buildVariableMsg(String sign, String msg) {
        return StrUtil.format("【{}】{}", sign, replaceMsg(msg));
    }

    private String replaceMsg(String msg) {
        if (StrUtil.isEmpty(msg)) {
            return msg;
        }
        Matcher m = REG.matcher(msg);
        String result = msg;
        while (m.find()) {
            String group = m.group();
            result = result.replace(group, VAR);
        }
        return result;
    }


    @Override
    public boolean isSuccess(MsgResult result) {
        ClSendResult clSendResult = (ClSendResult) result.getResult();
        return "0".equals(clSendResult.getCode());
    }
}
