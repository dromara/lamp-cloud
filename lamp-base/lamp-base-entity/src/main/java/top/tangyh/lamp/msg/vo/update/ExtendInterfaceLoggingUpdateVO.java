package top.tangyh.lamp.msg.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * 接口执行日志记录
 * </p>
 *
 * @author zuihou
 * @date 2022-07-09 23:58:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "接口执行日志记录")
public class ExtendInterfaceLoggingUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "")
    @NotNull(message = "请填写", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 接口日志ID;
     * #extend_interface_log
     */
    @Schema(description = "接口日志ID")
    @NotNull(message = "请填写接口日志ID")
    private Long logId;
    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    @NotNull(message = "请填写执行时间")
    private LocalDateTime execTime;
    /**
     * 执行状态;
     * [01-初始化 02-成功 03-失败]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
     */
    @Schema(description = "执行状态")
    @Size(max = 2, message = "执行状态长度不能超过{max}")
    private String status;
    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String params;
    /**
     * 接口返回
     */
    @Schema(description = "接口返回")
    private String result;
    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private Long bizId;
    @Schema(description = "异常信息")
    @Size(max = 2147483647, message = "异常信息长度不能超过{max}")
    private String errorMsg;

}
