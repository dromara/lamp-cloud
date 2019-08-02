package com.github.zuihou.msgs.dto;

import java.time.LocalDateTime;

import com.github.zuihou.msgs.entity.MsgsCenterInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/08/02
 */
@Data
@ApiModel(value = "MsgsCenterInfoPageResult", description = "消息分页返回")
@ToString(callSuper = true)
public class MsgsCenterInfoPageResultDTO extends MsgsCenterInfo {
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    @ApiModelProperty(value = "读消息的时间")
    private LocalDateTime readTime;
    @ApiModelProperty(value = "接收表id")
    private Long receiveId;
}
