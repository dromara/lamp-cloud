package com.github.zuihou.authority.dto.common;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @since 2019-07-28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DictionaryItemSaveDTO", description = "字典项")
public class DictionaryItemSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典id
     */
    @ApiModelProperty(value = "字典id")
    @NotNull(message = "字典id不能为空")

    private Long dictionaryId;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    @NotEmpty(message = "字典编码不能为空")
    @Length(max = 64, message = "字典编码长度不能超过64")

    private String dictionaryCode;
    /**
     * 字典项编码
     */
    @ApiModelProperty(value = "字典项编码")
    @NotEmpty(message = "字典项编码不能为空")
    @Length(max = 64, message = "字典项编码长度不能超过64")

    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Length(max = 64, message = "名称长度不能超过64")

    private String name;
    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")

    private Boolean isEnable;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")

    private Boolean isDelete;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 255, message = "描述长度不能超过255")

    private String describe;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")

    private Integer sortValue;

}
