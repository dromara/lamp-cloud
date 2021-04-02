package com.tangyh.lamp.authority.dto.common;

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

/**
 * <p>
 * 实体类
 * 字典项
 * </p>
 *
 * @author zuihou
 * @since 2020-01-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DictionaryTypeSaveDTO", description = "字典类型")
public class DictionaryTypeSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;

}
