package com.tangyh.lamp.authority.dto.common;

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
 * 字典项
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DictionaryUpdateDTO", description = "字典项")
public class DictionaryUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @NotEmpty(message = "类型不能为空")
    @Size(max = 255, message = "类型长度不能超过255")
    private String type;
    /**
     * 类型标签
     */
    @ApiModelProperty(value = "类型标签")
    @NotEmpty(message = "类型标签不能为空")
    @Size(max = 255, message = "类型标签长度不能超过255")
    private String label;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    private String name;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    private String describe;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;
    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    private String cssStyle;
    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    private String cssClass;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
}
