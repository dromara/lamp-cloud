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
import java.time.LocalDateTime;

/**
 * <p>
 * 表单修改方法VO
 * 通知表
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
@Schema(description = "通知表")
public class ExtendNoticeUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;
    /**
     * 消息ID
     */
    @Schema(description = "消息ID")
    private Long msgId;
    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    @Size(max = 64, message = "业务ID长度不能超过{max}")
    private String bizId;
    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    @Size(max = 64, message = "业务类型长度不能超过{max}")
    private String bizType;
    /**
     * 接收人
     */
    @Schema(description = "接收人")
    @NotNull(message = "请填写接收人")
    private Long recipientId;
    /**
     * 提醒方式;
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.NOTICE_REMIND_MODE)
     * [01-待办 02-预警 03-提醒]
     */
    @Schema(description = "提醒方式")
    @NotEmpty(message = "请填写提醒方式")
    @Size(max = 2, message = "提醒方式长度不能超过{max}")
    private String remindMode;
    /**
     * 标题
     */
    @Schema(description = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 内容
     */
    @Schema(description = "内容")
    @Size(max = 16777215, message = "内容长度不能超过{max}")
    private String content;
    /**
     * 发布人
     */
    @Schema(description = "发布人")
    @Size(max = 255, message = "发布人长度不能超过{max}")
    private String author;
    /**
     * 处理地址
     */
    @Schema(description = "处理地址")
    @Size(max = 255, message = "处理地址长度不能超过{max}")
    private String url;
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
     * 处理时间
     */
    @Schema(description = "处理时间")
    private LocalDateTime handleTime;
    /**
     * 读取时间
     */
    @Schema(description = "读取时间")
    private LocalDateTime readTime;
    /**
     * 是否已读
     */
    @Schema(description = "是否已读")
    private Boolean isRead;
    /**
     * 是否处理
     */
    @Schema(description = "是否处理")
    private Boolean isHandle;
    /**
     * 所属组织
     */
    @Schema(description = "所属组织")
    private Long createdOrgId;


}
