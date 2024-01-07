package top.tangyh.lamp.msg.strategy;

import cn.hutool.core.util.StrUtil;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.model.Kv;
import top.tangyh.lamp.common.utils.FreeMarkerUtil;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.glue.GlueFactory;
import top.tangyh.lamp.msg.strategy.domain.MsgParam;
import top.tangyh.lamp.msg.strategy.domain.MsgResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zuihou
 * @date 2022/7/10 0010 14:18
 */
public interface MsgStrategy {
    /**
     * 解析参数
     *
     * @param param param
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author tangyh
     * @date 2022/10/28 4:58 PM
     * @create [2022/10/28 4:58 PM ] [tangyh] [初始创建]
     */
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

    /**
     * 替换变量
     *
     * @param extendMsg         extendMsg
     * @param extendMsgTemplate extendMsgTemplate
     * @return top.tangyh.lamp.msg.strategy.domain.MsgResult
     * @author tangyh
     * @date 2022/10/28 4:58 PM
     * @create [2022/10/28 4:58 PM ] [tangyh] [初始创建]
     */
    default MsgResult replaceVariable(ExtendMsg extendMsg, DefMsgTemplate extendMsgTemplate) {
        String script = extendMsgTemplate.getScript();
        String templateContent = extendMsgTemplate.getContent();
        String templateTitle = extendMsgTemplate.getTitle();
        Map<String, Object> params = new LinkedHashMap<>();
        if (StrUtil.isNotEmpty(extendMsg.getParam())) {
            List<Kv> list = JsonUtil.parseArray(extendMsg.getParam(), Kv.class);
            for (Kv kv : list) {
                params.put(kv.getKey(), kv.getValue());
            }
        }
        Map<String, Object> resultParams = params;
        String title = templateTitle;
        String content = templateContent;
        if (StrUtil.isNotEmpty(script)) {
            resultParams = (Map<String, Object>) GlueFactory.getInstance().exeGroovyScript(script, params);
        }
        if (StrUtil.isNotEmpty(templateTitle)) {
            title = FreeMarkerUtil.generateString(templateTitle, resultParams);
        }
        if (StrUtil.isNotEmpty(templateContent)) {
            content = FreeMarkerUtil.generateString(templateContent, resultParams);
        }

        return MsgResult.builder().title(title).content(content).build();
    }

    /**
     * 执行发送
     *
     * @param msgParam msgParam
     * @return top.tangyh.lamp.msg.strategy.domain.MsgResult
     * @throws Exception 异常
     * @author tangyh
     * @date 2022/10/28 4:58 PM
     * @create [2022/10/28 4:58 PM ] [tangyh] [初始创建]
     */
    MsgResult exec(MsgParam msgParam) throws Exception;

    /**
     * 是否执行成功
     *
     * @param result 执行函数的返回值
     * @return
     */
    default boolean isSuccess(MsgResult result) {
        return true;
    }
}
