package com.tangyh.lamp.msg.dto;

import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.lamp.msg.enumeration.MsgBizType;
import com.tangyh.lamp.msg.enumeration.MsgType;
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
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 消息中心表
 * </p>
 *
 * @author zuihou
 * @since 2019-12-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "MsgUpdateDTO", description = "消息中心表")
public class MsgUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 业务ID
     * 业务表的唯一id
     */
    @ApiModelProperty(value = "业务ID")
    @Size(max = 64, message = "业务ID长度不能超过64")
    private String bizId;
    /**
     * 业务类型
     * #MsgBizType{USER_LOCK:账号锁定;}
     */
    @ApiModelProperty(value = "业务类型")
    private MsgBizType bizType;
    /**
     * 消息类型
     * #MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    private MsgType msgType;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Size(max = 255, message = "标题长度不能超过255")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Size(max = 65535, message = "内容长度不能超过65,535")
    private String content;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    @Size(max = 50, message = "作者长度不能超过50")
    private String author;
    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Size(max = 255, message = "处理地址长度不能超过255")
    private String handlerUrl;
    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Size(max = 400, message = "处理参数长度不能超过400")
    private String handlerParams;
    /**
     * 是否单人处理
     */
    @ApiModelProperty(value = "是否单人处理")
    private Boolean isSingleHandle;

}
