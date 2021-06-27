package com.tangyh.lamp.msg.dto;

import com.tangyh.lamp.msg.enumeration.MsgBizType;
import com.tangyh.lamp.msg.enumeration.MsgType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 消息分页参数
 *
 * @author zuihou
 * @date 2019/08/02
 */

@Data
@ToString
@ApiModel(value = "MsgQuery", description = "消息分页参数")
public class MsgQuery implements Serializable {
    private static final long serialVersionUID = -2054606159972155030L;
    @ApiModelProperty(value = "接收人ID")
    private Long userId;
    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;
    @ApiModelProperty(value = "消息类型")
    private MsgType msgType;
    @ApiModelProperty(value = "业务类型")
    private MsgBizType bizType;

    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

//    @ApiModelProperty(value = "开始时间")
//    private LocalDateTime startCreateTime;
//    @ApiModelProperty(value = "结束时间")
//    private LocalDateTime endCreateTime;

}
