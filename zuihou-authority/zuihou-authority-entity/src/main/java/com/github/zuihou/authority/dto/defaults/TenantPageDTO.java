package com.github.zuihou.authority.dto.defaults;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 企业
 * </p>
 *
 * @author zuihou
 * @since 2019-10-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TenantPageDTO", description = "企业")
public class TenantPageDTO extends TenantSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建开始时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "创建截止时间")
    private LocalDateTime endCreateTime;
}
