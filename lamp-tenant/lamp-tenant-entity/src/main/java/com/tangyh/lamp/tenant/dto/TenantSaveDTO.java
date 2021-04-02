package com.tangyh.lamp.tenant.dto;

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
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体类
 * 企业
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
@ApiModel(value = "TenantSaveDTO", description = "企业")
public class TenantSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @NotEmpty(message = "企业编码不能为空")
    @Size(max = 20, message = "企业编码长度不能超过20")
    private String code;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Size(max = 255, message = "企业名称长度不能超过255")
    private String name;
    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    @Size(max = 50, message = "责任人长度不能超过50")
    private String duty;
    /**
     * 有效期
     * 为空表示永久
     */
    @ApiModelProperty(value = "有效期")
    private LocalDateTime expirationTime;
    /**
     * logo地址
     */
    @ApiModelProperty(value = "logo地址")
    @Size(max = 255, message = "logo地址长度不能超过255")
    private String logo;
    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    @Size(max = 255, message = "企业简介长度不能超过255")
    private String describe;

}
