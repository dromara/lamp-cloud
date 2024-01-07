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
@Schema(description = "消息")
public class ExtendMsgUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板标识
     */
    @Schema(description = "模板标识")
    @Size(max = 255, message = "模板标识长度不能超过{max}")
    private String templateCode;
    /**
     * 参数;
     * <p>
     * 需要封装为[{{‘key’:'', ’value’:''}, {'key2':'', 'value2':''}]格式
     */
    @Schema(description = "参数")
    @Size(max = 500, message = "参数长度不能超过{max}")
    private String param;

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

    @Schema(description = "接收人")
    @NotEmpty(message = "请选择接收人")
    private List<String> recipientList;
}
