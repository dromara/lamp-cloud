package com.github.zuihou.authority.dto.common;

import com.github.zuihou.base.entity.SuperEntity;
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
 * @since 2019-10-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginLogUpdateDTO", description = "系统日志")
public class LoginLogUpdateDTO implements Serializable {

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
     * 登录用户
     */
    @ApiModelProperty(value = "登录用户")
    @Length(max = 50, message = "登录用户长度不能超过50")
    private String userName;
    /**
     * 登录描述
     */
    @ApiModelProperty(value = "登录描述")
    @Length(max = 255, message = "登录描述长度不能超过255")
    private String description;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime loginTime;
    /**
     * 浏览器请求头
     */
    @ApiModelProperty(value = "浏览器请求头")
    private Long ua;
    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    @Length(max = 50, message = "登录地点长度不能超过50")
    private String location;

}
