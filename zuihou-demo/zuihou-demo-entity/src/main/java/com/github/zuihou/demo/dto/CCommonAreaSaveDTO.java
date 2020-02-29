package com.github.zuihou.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @since 2019-08-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "CCommonAreaSaveDTO", description = "地区表")
public class CCommonAreaSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 地区编码
     */
    @ApiModelProperty(value = "地区编码")
    @NotEmpty(message = "地区编码不能为空")
    @Length(max = 64, message = "地区编码长度不能超过64")
    private String code;
    /**
     * 全名
     */
    @ApiModelProperty(value = "全名")
    @Length(max = 255, message = "全名长度不能超过255")
    private String fullName;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    @Length(max = 255, message = "经度长度不能超过255")
    private String longitude;
    /**
     * 维度
     */
    @ApiModelProperty(value = "维度")
    @Length(max = 255, message = "维度长度不能超过255")
    private String latitude;
    /**
     * 行政区级
     */
    @ApiModelProperty(value = "行政区级")
    @NotNull(message = "行政区级不能为空")
    private Integer level;
    /**
     * 租户
     */
    @ApiModelProperty(value = "租户")
    @Length(max = 6, message = "租户长度不能超过6")
    private String tenantId;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private Long parentId;
    /**
     * 上级行政区码
     */
    @ApiModelProperty(value = "上级行政区码")
    @Length(max = 64, message = "上级行政区码长度不能超过64")
    private String parentCode;

}
