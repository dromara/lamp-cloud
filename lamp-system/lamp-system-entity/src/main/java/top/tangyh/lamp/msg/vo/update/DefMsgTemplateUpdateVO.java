package top.tangyh.lamp.msg.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.SuperEntity;

import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
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
public class DefMsgTemplateUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "模板ID")
    @NotNull(message = "请填写模板ID", groups = SuperEntity.Update.class)
    private Long id;
    @Schema(description = "状态")
    private Boolean state;
    /** 接口ID */
    @Schema(description = "接口ID")
    @NotNull(message = "请选择接口")
    private Long interfaceId;
    /**
     * 消息类型;
     * [01-短信 02-邮件 03-站内信];
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_TEMPLATE_TYPE)
     */
    @Schema(description = "消息类型")
    @NotEmpty(message = "请填写消息类型")
    @Size(max = 2, message = "消息类型长度不能超过{max}")
    private String type;
    /**
     * 模板标识
     */
    @Schema(description = "模板标识")
    @NotEmpty(message = "请填写模板标识")
    @Size(max = 255, message = "模板标识长度不能超过{max}")
    private String code;
    /**
     * 模板名称
     */
    @Schema(description = "模板名称")
    @Size(max = 255, message = "模板名称长度不能超过{max}")
    private String name;
    /**
     * 标题
     */
    @Schema(description = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 模板内容
     */
    @Schema(description = "模板内容")
    @NotEmpty(message = "请填写模板内容")
    @Size(max = 255, message = "模板内容长度不能超过{max}")
    private String content;
    /**
     * 脚本
     */
    @Schema(description = "脚本")
    @Size(max = 255, message = "脚本长度不能超过{max}")
    private String script;
    /**
     * 模板参数
     */
    @Schema(description = "模板参数")
    @Size(max = 255, message = "模板参数长度不能超过{max}")
    private String param;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 255, message = "备注长度不能超过{max}")
    private String remarks;
    /**
     * 打开方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_TARGET)
     * [01-页面 02-弹窗 03-新开窗口]
     */
    @Schema(description = "打开方式")
    @Size(max = 2, message = "打开方式长度不能超过{max}")
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
    @Size(max = 2, message = "提醒方式长度不能超过{max}")
    private String remindMode;
    /**
     * 跳转地址
     */
    @Schema(description = "跳转地址")
    @Size(max = 255, message = "跳转地址长度不能超过{max}")
    private String url;
    @Schema(description = "短信模板")
    @Size(max = 255, message = "短信模板长度不能超过{max}")
    private String templateCode;
    @Schema(description = "签名")
    @Size(max = 255, message = "签名长度不能超过{max}")
    private String sign;
}
