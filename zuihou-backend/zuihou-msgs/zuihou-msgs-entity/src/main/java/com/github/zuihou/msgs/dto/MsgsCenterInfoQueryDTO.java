package com.github.zuihou.msgs.dto;

import com.github.zuihou.msgs.enumeration.MsgsBizType;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息分页参数
 *
 * @author zuihou
 * @date 2019/08/02
 */

@Data
@ToString
@ApiModel(value = "MsgsCenterInfoQueryDTO", description = "消息分页参数")
public class MsgsCenterInfoQueryDTO implements Serializable {
    private static final long serialVersionUID = -2054606159972155030L;
    @ApiModelProperty(value = "接收人ID")
    private Long userId;
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    @ApiModelProperty(value = "消息类型")
    private MsgsCenterType msgsCenterType;
    @ApiModelProperty(value = "业务类型")
    private MsgsBizType bizType;

    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endCreateTime;

}
