package top.tangyh.lamp.model.entity.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.TreeEntity;
import top.tangyh.lamp.model.constant.EchoDictType;

import javax.validation.constraints.Size;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;
import static top.tangyh.lamp.model.constant.EchoApi.DICTIONARY_ITEM_FEIGN_CLASS;

/**
 * <p>
 * 实体类
 * 资源
 * </p>
 *
 * @author zuihou
 * @since 2021-09-15
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_menu")
@AllArgsConstructor
@Builder
public class SysMenu extends TreeEntity<SysMenu, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    @TableField(value = "describe_", condition = LIKE)
    private String describe;
    /**
     * 类型;[20-菜单 60-数据]
     */
    @ApiModelProperty(value = "类型")
    @Size(max = 2, message = "类型长度不能超过{max}")
    @TableField(value = "resource_type", condition = LIKE)
    @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, dictType = EchoDictType.RESOURCE_TYPE)
    private String resourceType;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    @TableField("is_general")
    private Boolean isGeneral;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @Size(max = 255, message = "路径长度不能超过255")
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    @Size(max = 255, message = "组件长度不能超过255")
    @TableField(value = "component", condition = LIKE)
    private String component;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("state")
    private Boolean state;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @Size(max = 255, message = "菜单图标长度不能超过255")
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Size(max = 20, message = "分组长度不能超过20")
    @TableField(value = "group_", condition = LIKE)
    private String group;

    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    @TableField("readonly_")
    private Boolean readonly;

    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @ApiModelProperty(value = "数据范围")
    @TableField(value = "data_scope", condition = LIKE)
    @Size(max = 2, message = "数据范围长度不能超过{max}")
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @ApiModelProperty(value = "实现类")
    @TableField(value = "custom_class", condition = LIKE)
    @Size(max = 255, message = "实现类长度不能超过{max}")
    private String customClass;

    /**
     * 是否默认
     */
    @ApiModelProperty(value = "是否默认")
    @TableField(value = "is_def")
    private Boolean isDef;

    /**
     * 树层级
     */
    @ApiModelProperty(value = "树层级")
    @TableField(value = "tree_grade", condition = EQUAL)
    private Integer treeGrade;
    /**
     * 树路径;用id拼接树结构
     */
    @ApiModelProperty(value = "树路径")
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;
}
