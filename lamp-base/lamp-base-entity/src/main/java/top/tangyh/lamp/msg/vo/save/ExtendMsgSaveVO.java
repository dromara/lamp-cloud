package top.tangyh.lamp.msg.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.msg.enumeration.SourceType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 表单保存方法VO
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
@EqualsAndHashCode
@Builder
@Schema(description = "消息")
public class ExtendMsgSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板标识
     */
    @Schema(description = "模板标识")
    @Size(max = 255, message = "模板标识长度不能超过{max}")
    private String templateCode;
    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @Schema(description = "消息类型")
    @Size(max = 2, message = "消息类型长度不能超过{max}")
    @NotEmpty(message = "请填写消息类型")
    private String type;
    /**
     * 发送渠道;
     * #SourceType{APP:应用;SERVICE:服务}
     */
    @Schema(description = "发送渠道")
    private SourceType channel;
    /**
     * 参数;
     * <p>
     * 需要封装为[{{‘key’:'', ’value’:''}, {'key2':'', 'value2':''}]格式
     */
    @Schema(description = "参数")
    @Size(max = 500, message = "参数长度不能超过{max}")
    private String param;
    /**
     * 标题
     */
    @Schema(description = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 发送内容;
     * <p>
     * 需要封装正确格式化: 您好，张三，您有一个新的快递。
     */
    @Schema(description = "发送内容")
    @Size(max = 2147483647, message = "发送内容长度不能超过{max}")
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
    @Size(max = 255, message = "业务类型长度不能超过{max}")
    private String bizType;
    /**
     * 发布人姓名
     */
    @Schema(description = "发布人姓名")
    @Size(max = 255, message = "发布人姓名长度不能超过{max}")
    private String author;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @Schema(description = "提醒方式")
    @Size(max = 2, message = "提醒方式长度不能超过{max}")
    private String remindMode;

    @Schema(description = "是否草稿")
    private Boolean draft;

    @Schema(description = "接收人")
    @NotEmpty(message = "请选择接收人")
    private List<String> recipientList;
}
