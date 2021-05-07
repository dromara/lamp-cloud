package com.tangyh.lamp.authority.dto.auth;

import com.tangyh.basic.base.entity.SuperEntity;
import com.tangyh.lamp.authority.enumeration.auth.Sex;
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
 * 全局账号
 * </p>
 *
 * @author zuihou
 * @since 2019-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "GlobalUserUpdateDTO", description = "全局账号")
public class GlobalUserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;
    @ApiModelProperty(value = "企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 10, message = "企业编码长度不能超过10")
    private String tenantCode;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    @Size(max = 20, message = "手机长度不能超过20")
    private String mobile;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Size(max = 50, message = "姓名长度不能超过20")
    private String name;
    @ApiModelProperty(value = "性别")
    private Sex sex;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Size(max = 255, message = "邮箱长度不能超过255")
    private String email;

}
