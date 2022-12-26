package top.tangyh.lamp.sms.dto;

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
import top.tangyh.lamp.sms.enumeration.SendStatus;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 短信发送状态
 * </p>
 *
 * @author zuihou
 * @since 2021-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "短信发送状态")
public class SmsSendStatusSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     * #e_sms_task
     */
    @Schema(description = "任务ID")
    @NotNull(message = "请填写任务ID")
    private Long taskId;
    /**
     * 发送状态
     * #SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}
     */
    @Schema(description = "发送状态")
    @NotNull(message = "请填写发送状态")
    private SendStatus sendStatus;
    /**
     * 接收者手机
     * 单个手机号
     * 阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID
     */
    @Schema(description = "接收者手机")
    @NotEmpty(message = "请填写接收者手机")
    @Size(max = 20, message = "接收者手机长度不能超过20")
    private String telNum;
    /**
     * 发送回执ID
     */
    @Schema(description = "发送回执ID")
    @Size(max = 255, message = "发送回执ID长度不能超过255")
    private String bizId;
    /**
     * 发送返回
     * 阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无
     */
    @Schema(description = "发送返回")
    @Size(max = 255, message = "发送返回长度不能超过255")
    private String ext;
    /**
     * 状态码
     * 阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功
     */
    @Schema(description = "状态码")
    @Size(max = 255, message = "状态码长度不能超过255")
    private String code;
    /**
     * 状态码的描述
     */
    @Schema(description = "状态码的描述")
    @Size(max = 500, message = "状态码的描述长度不能超过500")
    private String message;
    /**
     * 短信计费的条数
     * 腾讯专用
     */
    @Schema(description = "短信计费的条数")
    private Integer fee;

}
