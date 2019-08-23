package com.github.zuihou.authority.dto.common;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

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
 * 字典目录
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
@ApiModel(value = "DictionarySaveDTO", description = "字典目录")
public class DictionarySaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     * 一颗树仅仅有一个统一的编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Length(max = 64, message = "编码长度不能超过64")

    private String code;
    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @NotEmpty(message = "字典名称不能为空")
    @Length(max = 64, message = "字典名称长度不能超过64")

    private String name;
    /**
     * 字典描述
     */
    @ApiModelProperty(value = "字典描述")
    @Length(max = 200, message = "字典描述长度不能超过200")

    private String describe;
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

}
