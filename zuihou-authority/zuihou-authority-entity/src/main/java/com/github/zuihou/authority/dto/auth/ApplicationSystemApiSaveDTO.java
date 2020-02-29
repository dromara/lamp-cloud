package com.github.zuihou.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value = "ApplicationSystemApiSaveDTO", description = "应用接口")
public class ApplicationSystemApiSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
