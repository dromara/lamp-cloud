package com.tangyh.lamp.msg.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 消息接收表
 * </p>
 *
 * @author zuihou
 * @since 2020-11-21
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("e_msg_receive")
@ApiModel(value = "MsgReceive", description = "消息接收表")
@AllArgsConstructor
public class MsgReceive extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     * #msg
     */
    @ApiModelProperty(value = "消息ID")
    @NotNull(message = "消息ID不能为空")
    @TableField("msg_id")
    @Excel(name = "消息ID")
    private Long msgId;

    /**
     * 接收人ID
     * #c_user
     */
    @ApiModelProperty(value = "接收人ID")
    @NotNull(message = "接收人ID不能为空")
    @TableField("user_id")
    @Excel(name = "接收人ID")
    private Long userId;

    /**
     * 是否已读
     */
    @ApiModelProperty(value = "是否已读")
    @TableField("is_read")
    @Excel(name = "是否已读", replace = {"是_true", "否_false", "_null"})
    private Boolean isRead;


    @Builder
    public MsgReceive(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                      Long msgId, Long userId, Boolean isRead) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.msgId = msgId;
        this.userId = userId;
        this.isRead = isRead;
    }

}
