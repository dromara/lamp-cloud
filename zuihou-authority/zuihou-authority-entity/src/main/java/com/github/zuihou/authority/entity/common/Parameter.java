package com.github.zuihou.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.zuihou.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 参数配置
 * </p>
 *
 * @author zuihou
 * @since 2020-02-05
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_common_parameter")
@ApiModel(value = "Parameter", description = "参数配置")
@AllArgsConstructor
public class Parameter extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数键
     */
    @ApiModelProperty(value = "参数键")
    @NotEmpty(message = "参数键不能为空")
    @Length(max = 255, message = "参数键长度不能超过255")
    @TableField(value = "key_", condition = LIKE)
    @Excel(name = "参数键", width = 20)
    private String key;

    /**
     * 参数名称
     */
    @ApiModelProperty(value = "参数名称")
    @NotEmpty(message = "参数名称不能为空")
    @Length(max = 255, message = "参数名称长度不能超过255")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "参数名称", width = 20)
    private String name;

    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    @NotEmpty(message = "参数值不能为空")
    @Length(max = 255, message = "参数值长度不能超过255")
    @TableField(value = "value", condition = LIKE)
    @Excel(name = "参数值", width = 20)
    private String value;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Length(max = 200, message = "描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述", width = 40)
    private String describe;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("status_")
    @Excel(name = "状态", replace = {"启用_true", "禁用_false", "_null"})
    private Boolean status;

    /**
     * 只读
     */
    @ApiModelProperty(value = "只读")
    @TableField("readonly_")
    @Excel(name = "只读", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;


    @Builder
    public Parameter(Long id, Long createUser, LocalDateTime createTime, Long updateUser, LocalDateTime updateTime,
                     String key, String name, String value, String describe, Boolean status, Boolean readonly) {
        this.id = id;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.key = key;
        this.name = name;
        this.value = value;
        this.describe = describe;
        this.status = status;
        this.readonly = readonly;
    }

}
