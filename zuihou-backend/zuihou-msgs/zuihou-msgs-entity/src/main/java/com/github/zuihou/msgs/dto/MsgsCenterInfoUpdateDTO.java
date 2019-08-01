package com.github.zuihou.msgs.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.msgs.enumeration.MsgsCenterType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 消息中心全量表
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
@ApiModel(value = "MsgsCenterInfoUpdateDTO", description = "消息中心全量表")
public class MsgsCenterInfoUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 业务ID
     * 业务表的唯一id
     */
    @ApiModelProperty(value = "业务ID")
    @Length(max = 64, message = "业务ID长度不能超过64")
    private String bizId;
    /**
     * 业务类型
     * #MsgsBizType.code
     */
    @ApiModelProperty(value = "业务类型")
    @NotEmpty(message = "业务类型不能为空")
    @Length(max = 64, message = "业务类型长度不能超过64")
    private String bizType;
    /**
     * 消息类型
     * #MsgsCenterType{WAIT:待办;NOTIFY:通知;PUBLICITY:公示公告;WARN:预警;}
     */
    @ApiModelProperty(value = "消息类型")
    @NotNull(message = "消息类型不能为空")
    private MsgsCenterType msgsCenterType;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @Length(max = 100, message = "标题长度不能超过100")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @Length(max = 500, message = "内容长度不能超过500")
    private String content;
    /**
     * 作者名称
     */
    @ApiModelProperty(value = "作者名称")
    @Length(max = 50, message = "作者名称长度不能超过50")
    private String author;
    /**
     * 处理地址
     * 以http开头时直接跳转，否则与#c_application表拼接后跳转
     * http可带参数
     */
    @ApiModelProperty(value = "处理地址")
    @Length(max = 200, message = "处理地址长度不能超过200")
    private String handlerUrl;
    /**
     * 处理参数
     */
    @ApiModelProperty(value = "处理参数")
    @Length(max = 400, message = "处理参数长度不能超过400")
    private String handlerParams;
    /**
     * 是否单人处理后就标记已处理
     */
    @ApiModelProperty(value = "是否单人处理后就标记已处理")
    private Boolean isSingleHandle;
    /**
     * 是否删除
     * 业务数据删除后，会调用接口删除该消息
     */
    @ApiModelProperty(value = "是否删除")
    private Boolean isDelete;
    /**
     * 应用code
     */
    @ApiModelProperty(value = "应用code")
    @Length(max = 64, message = "应用code长度不能超过64")
    private String appCode;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @Length(max = 255, message = "应用名称长度不能超过255")
    private String appName;

}
