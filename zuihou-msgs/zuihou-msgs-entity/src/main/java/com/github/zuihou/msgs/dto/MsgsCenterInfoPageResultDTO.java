package com.github.zuihou.msgs.dto;

import com.github.zuihou.msgs.entity.MsgsCenterInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 消息分页返回
 *
 * @author zuihou
 * @date 2019/08/02
 */
@Data
@ApiModel(value = "MsgsCenterInfoPageResult", description = "消息分页返回")
@ToString(callSuper = true)
public class MsgsCenterInfoPageResultDTO extends MsgsCenterInfo {
    private static final long serialVersionUID = -44224723996050485L;
    @ApiModelProperty(value = "状态")
    private Boolean isRead;
    @ApiModelProperty(value = "读消息的时间")
    private LocalDateTime readTime;
    @ApiModelProperty(value = "接收表id")
    private Long receiveId;
}
