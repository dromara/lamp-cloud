package com.tangyh.lamp.authority.entity.common;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.Entity;
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
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

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
@ToString(callSuper = true)
@EqualsAndHashCode(of = {"type", "code"})
@Accessors(chain = true)
@TableName("c_dictionary")
@ApiModel(value = "Dictionary", description = "字典项")
@AllArgsConstructor
public class Dictionary extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @NotEmpty(message = "类型不能为空")
    @Size(max = 255, message = "类型长度不能超过255")
    @TableField(value = "type", condition = LIKE)
    @Excel(name = "类型")
    private String type;

    /**
     * 类型标签
     */
    @ApiModelProperty(value = "类型标签")
    @NotEmpty(message = "类型标签不能为空")
    @Size(max = 255, message = "类型标签长度不能超过255")
    @TableField(value = "label", condition = LIKE)
    @Excel(name = "类型标签")
    private String label;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotEmpty(message = "编码不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @TableField(value = "code", condition = LIKE)
    @Excel(name = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 64, message = "名称长度不能超过64")
    @TableField(value = "name", condition = LIKE)
    @Excel(name = "名称")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 255, message = "描述长度不能超过255")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述")
    private String describe;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    @Excel(name = "排序")
    private Integer sortValue;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    @Excel(name = "图标")
    private String icon;

    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    @Size(max = 255, message = "css样式长度不能超过255")
    @TableField(value = "css_style", condition = LIKE)
    @Excel(name = "css样式")
    private String cssStyle;

    /**
     * 类选择器
     */
    @ApiModelProperty(value = "类选择器")
    @Size(max = 255, message = "类选择器长度不能超过255")
    @TableField(value = "css_class", condition = LIKE)
    @Excel(name = "类选择器")
    private String cssClass;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @TableField("readonly_")
    @Excel(name = "内置", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;


    @Builder
    public Dictionary(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                      String type, String label, String code, String name, Boolean state,
                      String describe, Integer sortValue, String icon, String cssStyle, String cssClass, Boolean readonly) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.type = type;
        this.label = label;
        this.code = code;
        this.name = name;
        this.state = state;
        this.describe = describe;
        this.sortValue = sortValue;
        this.icon = icon;
        this.cssStyle = cssStyle;
        this.cssClass = cssClass;
        this.readonly = readonly;
    }

}
