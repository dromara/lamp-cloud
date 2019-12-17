package com.github.zuihou.authority.dto.auth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.github.zuihou.base.entity.SuperEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 实体类
 * 应用接口
 * </p>
 *
 * @author zuihou
 * @since 2019-12-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ApplicationSystemApiUpdateDTO", description = "应用接口")
public class ApplicationSystemApiUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    private Long applicationId;
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    private Long systemApiId;

}
