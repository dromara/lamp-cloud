package com.github.zuihou.msgs.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author zuihou
 * @since 2019-08-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "MsgsCenterInfoReceiveSaveDTO", description = "消息中心 接收表 全量数据")
public class MsgsCenterInfoReceiveSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息中心ID
     * #msgs_center_info
     */
    @ApiModelProperty(value = "消息中心ID")
    @NotNull(message = "消息中心ID不能为空")
    private Long msgsCenterId;
    /**
     * 接收人ID
     * #c_user
     */
    @ApiModelProperty(value = "接收人ID")
    @NotNull(message = "接收人ID不能为空")
    private Long userId;
    /**
     * 是否已读
     * #BooleanStatus
     */
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;

}
