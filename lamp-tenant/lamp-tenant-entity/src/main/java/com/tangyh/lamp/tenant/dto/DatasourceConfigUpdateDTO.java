package com.tangyh.lamp.tenant.dto;

import com.tangyh.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 数据源
 * </p>
 *
 * @author zuihou
 * @since 2020-11-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DatasourceConfigUpdateDTO", description = "数据源")
public class DatasourceConfigUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 255, message = "账号长度不能超过255")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过255")
    private String password;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @NotEmpty(message = "链接不能为空")
    @Size(max = 255, message = "链接长度不能超过255")
    private String url;
    /**
     * 驱动
     */
    @ApiModelProperty(value = "驱动")
    @NotEmpty(message = "驱动不能为空")
    @Size(max = 255, message = "驱动长度不能超过255")
    private String driverClassName;
}
