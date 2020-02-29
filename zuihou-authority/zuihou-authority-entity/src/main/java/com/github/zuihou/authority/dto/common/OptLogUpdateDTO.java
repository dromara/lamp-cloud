package com.github.zuihou.authority.dto.common;

import com.github.zuihou.authority.enumeration.common.LogType;
import com.github.zuihou.base.entity.SuperEntity;
import com.github.zuihou.common.enums.HttpMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @since 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "OptLogUpdateDTO", description = "系统日志")
public class OptLogUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 操作IP
     */
    @ApiModelProperty(value = "操作IP")
    @Length(max = 50, message = "操作IP长度不能超过50")
    private String requestIp;
    /**
     * 日志类型
     * #LogType{OPT:操作类型;EX:异常类型}
     */
    @ApiModelProperty(value = "日志类型")
    private LogType type;
    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @Length(max = 50, message = "操作人长度不能超过50")
    private String userName;
    /**
     * 操作描述
     */
    @ApiModelProperty(value = "操作描述")
    @Length(max = 255, message = "操作描述长度不能超过255")
    private String description;
    /**
     * 类路径
     */
    @ApiModelProperty(value = "类路径")
    @Length(max = 255, message = "类路径长度不能超过255")
    private String classPath;
    /**
     * 请求类型
     */
    @ApiModelProperty(value = "请求类型")
    @Length(max = 50, message = "请求类型长度不能超过50")
    private String actionMethod;
    /**
     * 请求地址
     */
    @ApiModelProperty(value = "请求地址")
    @Length(max = 50, message = "请求地址长度不能超过50")
    private String requestUri;
    /**
     * 请求类型
     * #HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
     */
    @ApiModelProperty(value = "请求类型")
    private HttpMethod httpMethod;
    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数")
    @Length(max = 65535, message = "请求参数长度不能超过65,535")
    private String params;
    /**
     * 返回值
     */
    @ApiModelProperty(value = "返回值")
    @Length(max = 65535, message = "返回值长度不能超过65,535")
    private String result;
    /**
     * 异常详情信息
     */
    @ApiModelProperty(value = "异常详情信息")
    @Length(max = 65535, message = "异常详情信息长度不能超过65,535")
    private String exDesc;
    /**
     * 异常描述
     */
    @ApiModelProperty(value = "异常描述")
    @Length(max = 65535, message = "异常描述长度不能超过65,535")
    private String exDetail;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;
    /**
     * 消耗时间
     */
    @ApiModelProperty(value = "消耗时间")
    private Long consumingTime;
    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    @Length(max = 500, message = "浏览器长度不能超过500")
    private String ua;

}
