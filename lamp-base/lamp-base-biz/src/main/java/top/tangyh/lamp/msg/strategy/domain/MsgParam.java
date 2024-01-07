package top.tangyh.lamp.msg.strategy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import top.tangyh.lamp.file.entity.Appendix;
import top.tangyh.lamp.msg.entity.DefMsgTemplate;
import top.tangyh.lamp.msg.entity.ExtendMsg;
import top.tangyh.lamp.msg.entity.ExtendMsgRecipient;

import java.util.List;
import java.util.Map;

/**
 * 消息执行参数
 *
 * @author tangyh
 * @version v1.0
 * @date 2022/7/25 10:43 PM
 * @create [2022/7/25 10:43 PM ] [tangyh] [初始创建]
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class MsgParam {
    /** 消息内容 */
    private ExtendMsg extendMsg;
    /** 消息接收人 */
    private List<ExtendMsgRecipient> recipientList;
    /** 采用的消息模板 */
    private DefMsgTemplate extendMsgTemplate;
    /** 接口需要使用的动态参数 */
    private Map<String, Object> propertyParams;
    /** 消息的附件 */
    private List<Appendix> list;
}
