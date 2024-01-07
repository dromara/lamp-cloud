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
 * 消息接收人
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
@Schema(description = "消息接收人")
public class ExtendMsgRecipientUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 任务ID;
     * <p>
     * #extend_msg
     */
    @Schema(description = "任务ID")
    @NotNull(message = "请填写任务ID")
    private Long msgId;
    /**
     * 接收人;
     * 可能是手机号、邮箱、用户ID等
     */
    @Schema(description = "接收人")
    @NotEmpty(message = "请填写接收人")
    @Size(max = 255, message = "接收人长度不能超过{max}")
    private String recipient;

    @Schema(description = "扩展信息")
    @Size(max = 255, message = "扩展信息不能超过{max}")
    private String ext;

}
