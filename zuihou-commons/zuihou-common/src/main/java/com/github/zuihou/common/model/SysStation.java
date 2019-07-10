package com.github.zuihou.common.model;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author zuihou
 * @since 2019-07-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
public class SysStation {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Length(max = 255, message = "名称长度不能超过255")
    @TableField("name")
    private String name;

    /**
     * 组织ID
     * #c_core_org
     */
    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    private Long orgId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sortvalue")
    private Integer sortvalue;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Boolean status;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 255, message = "描述长度不能超过255")
    @TableField("describe_")
    private String describe;


}
