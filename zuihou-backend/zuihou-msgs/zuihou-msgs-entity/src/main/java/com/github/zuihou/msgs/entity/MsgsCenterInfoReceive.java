package com.github.zuihou.msgs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 消息中心接收表
 * </p>
 *
 * @author zuihou
 * @since 2019-12-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("msgs_center_info_receive")
@ApiModel(value = "MsgsCenterInfoReceive", description = "消息中心接收表")
public class MsgsCenterInfoReceive extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息中心ID
     * #msgs_center_info
     */
    @ApiModelProperty(value = "消息中心ID")
    @NotNull(message = "消息中心ID不能为空")
    @TableField("msgs_center_id")
    private Long msgsCenterId;

    /**
     * 接收人ID
     * #c_user
     */
    @ApiModelProperty(value = "接收人ID")
    @NotNull(message = "接收人ID不能为空")
    @TableField("user_id")
    private Long userId;

    /**
     * 是否已读
     * #BooleanStatus
     */
    @ApiModelProperty(value = "是否已读")
    @TableField("is_read")
    private Boolean isRead;


    @Builder
    public MsgsCenterInfoReceive(Long id, LocalDateTime createTime, Long createUser, Long updateUser, LocalDateTime updateTime,
                                 Long msgsCenterId, Long userId, Boolean isRead) {
        this.id = id;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.msgsCenterId = msgsCenterId;
        this.userId = userId;
        this.isRead = isRead;
    }

}
