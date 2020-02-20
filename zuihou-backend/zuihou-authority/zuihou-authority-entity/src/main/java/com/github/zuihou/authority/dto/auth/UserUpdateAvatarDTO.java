package com.github.zuihou.authority.dto.auth;

import com.github.zuihou.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 用户
 * </p>
 *
 * @author zuihou
 * @since 2019-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "UserUpdateAvatarDTO", description = "用户")
public class UserUpdateAvatarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @Length(max = 255, message = "头像长度不能超过255")
    private String avatar;

}
