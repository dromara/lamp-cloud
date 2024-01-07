package top.tangyh.lamp.msg.vo.update;

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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 表单修改方法VO
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
@Schema(description = "消息发布")
public class ExtendMsgPublishVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题")
    @Size(max = 255, message = "标题长度不能超过{max}")
    private String title;
    /**
     * 发送内容;
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
