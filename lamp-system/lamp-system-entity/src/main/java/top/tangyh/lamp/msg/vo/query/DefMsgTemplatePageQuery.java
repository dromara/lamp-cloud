package top.tangyh.lamp.msg.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * <p>
 * 表单查询条件VO
 * 消息模板
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "消息模板")
public class DefMsgTemplatePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "模板ID")
    private Long id;
    @Schema(description = "状态")
    private Boolean state;
    /** 接口ID */
    @Schema(description = "接口ID")
    private Long interfaceId;
    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @Schema(description = "消息类型")
    private String type;
    /**
     * 模板标识
     */
    @Schema(description = "模板标识")
    private String code;
    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    private String name;
    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;
    /**
     * 模板内容
     */
    @Schema(description = "模板内容")
    private String content;
    /**
     * 脚本
     */
    @Schema(description = "脚本")
    private String script;
    /**
     * 模板参数
     */
    @Schema(description = "模板参数")
    private String param;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;
    /**
     * 打开方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
     * [01-页面 02-弹窗 03-新开窗口]
     */
    @Schema(description = "打开方式")
    private String target;
    /**
     * 自动已读
     */
    @Schema(description = "自动已读")
    private Boolean autoRead;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @Schema(description = "提醒方式")
    private String remindMode;
    /**
     * 跳转地址
     */
    @Schema(description = "跳转地址")
    private String url;

    @Schema(description = "短信模板")
    private String templateCode;

    @Schema(description = "签名")
    private String sign;
}
