package top.tangyh.lamp.msg.vo.result;

import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.model.constant.EchoApi;
import top.tangyh.lamp.model.constant.EchoDictType;
import top.tangyh.lamp.msg.enumeration.SourceType;
import top.tangyh.lamp.msg.enumeration.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 消息
 * </p>
 *
 * @author zuihou
 * @date 2022-07-10 11:41:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(description = "消息")
public class ExtendMsgResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "短信记录ID")
    private Long id;

    /**
     * 消息模板;
     * #extend_msg_template
     */
    @Schema(description = "消息模板")
    private String templateCode;
    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @Schema(description = "消息类型")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
    private String type;
    /**
     * 执行状态;
     * #TaskStatus{DRAFT:草稿;WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}
     */
    @Schema(description = "执行状态")
    @Echo(api = Echo.ENUM_API)
    private TaskStatus status;
    /**
     * 发送渠道;
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @Schema(description = "发送渠道")
    @Echo(api = Echo.ENUM_API)
    private SourceType channel;
    /**
     * 参数;
     * <p>
     * 需要封装为[{{‘key’:'', ’value’:''}, {'key2':'', 'value2':''}]格式
     */
    @Schema(description = "参数")
    private String param;
    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 发送内容;
     * <p>
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @Schema(description = "发送内容")
    private String content;
    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    private LocalDateTime sendTime;
    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private Long bizId;
    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    private String bizType;

    /**
     * 发布人姓名
     */
    @Schema(description = "发布人姓名")
    private String author;

    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @Schema(description = "提醒方式")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
    private String remindMode;

    @Schema(description = "接收人")
    private List<String> recipientList;
}
