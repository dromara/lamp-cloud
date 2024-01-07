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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
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
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(description = "接口执行日志记录")
public class ExtendInterfaceLoggingResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "")
    private Long id;

    /**
     * 接口日志ID;
     * #extend_interface_log
     */
    @Schema(description = "接口日志ID")
    private Long logId;
    /**
     * 执行时间
     */
    @Schema(description = "执行时间")
    private LocalDateTime execTime;
    /**
     * 执行状态;
     * [01-初始化 02-成功 03-失败]
     *
     * @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
     */
    @Schema(description = "执行状态")
    @Echo(api = EchoApi.DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.Base.MSG_INTERFACE_LOGGING_STATUS)
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
    private String errorMsg;

}
