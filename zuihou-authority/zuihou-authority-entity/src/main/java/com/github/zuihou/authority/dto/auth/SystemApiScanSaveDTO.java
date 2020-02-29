package com.github.zuihou.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * API接口
 *
 * @author zuihou
 * @date 2019/12/16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "SystemApiScanSaveDTO", description = "API接口")
public class SystemApiScanSaveDTO implements Serializable {
    @Valid
    @ApiModelProperty(value = "api列表")
    private List<SystemApiSaveDTO> systemApiList;

    @ApiModelProperty(value = "服务ID")
    @NotEmpty(message = "服务ID不能为空")
    @Length(max = 50, message = "服务ID长度不能超过50")
    private String serviceId;

    private String tenant;
}
