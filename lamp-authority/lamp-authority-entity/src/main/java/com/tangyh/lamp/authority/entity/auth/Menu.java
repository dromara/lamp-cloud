package com.tangyh.lamp.authority.entity.auth;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tangyh.basic.base.entity.TreeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author zuihou
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_menu")
@ApiModel(value = "Menu", description = "菜单")
@AllArgsConstructor
public class Menu extends TreeEntity<Menu, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    @Excel(name = "描述")
    private String describe;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    @TableField("is_general")
    @Excel(name = "通用菜单", replace = {"是_true", "否_false", "_null"})
    private Boolean isGeneral;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Size(max = 255, message = "路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    @Excel(name = "路径")
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    @Size(max = 255, message = "组件长度不能超过255")
    @TableField(value = "component", condition = LIKE)
    @Excel(name = "组件")
    private String component;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    @Excel(name = "状态", replace = {"是_true", "否_false", "_null"})
    private Boolean state;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Size(max = 255, message = "菜单图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    @Excel(name = "菜单图标")
    private String icon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Size(max = 20, message = "分组长度不能超过20")
    @TableField(value = "group_", condition = LIKE)
    @Excel(name = "分组")
    private String group;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @TableField("readonly_")
    @Excel(name = "内置", replace = {"是_true", "否_false", "_null"})
    private Boolean readonly;


    @Builder
    public Menu(Long id, String label, Integer sortValue, Long parentId, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                String describe, Boolean isGeneral, String path, String component, Boolean state,
                String icon, String group, Boolean readonly) {
        this.id = id;
        this.label = label;
        this.sortValue = sortValue;
        this.parentId = parentId;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.describe = describe;
        this.isGeneral = isGeneral;
        this.path = path;
        this.component = component;
        this.state = state;
        this.icon = icon;
        this.group = group;
        this.readonly = readonly;
    }

}
