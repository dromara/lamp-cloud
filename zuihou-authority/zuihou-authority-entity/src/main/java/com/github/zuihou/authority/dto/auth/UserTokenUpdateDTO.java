package com.github.zuihou.authority.dto.auth;

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
 * token
 * </p>
 *
 * @author zuihou
 * @since 2020-04-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserTokenUpdateDTO", description = "token")
public class UserTokenUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    @Length(max = 50, message = "登录IP长度不能超过50")
    private String loginIp;
    /**
     * 登录地点
     */
    @ApiModelProperty(value = "登录地点")
    @Length(max = 50, message = "登录地点长度不能超过50")
    private String location;
    /**
     * 客户端Key
     */
    @ApiModelProperty(value = "客户端Key")
    @Length(max = 24, message = "客户端Key长度不能超过24")
    private String clientId;
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    @Length(max = 65535, message = "token长度不能超过65,535")
    private String token;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Length(max = 50, message = "姓名长度不能超过50")
    private String name;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @Length(max = 30, message = "账号长度不能超过30")
    private String account;
}
