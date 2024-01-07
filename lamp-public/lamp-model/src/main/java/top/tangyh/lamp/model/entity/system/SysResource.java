package top.tangyh.lamp.model.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.TreeEntity;

import static top.tangyh.lamp.model.constant.Condition.LIKE;

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
@TableName("def_resource")
@AllArgsConstructor
@Builder
public class SysResource extends TreeEntity<SysResource, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 应用ID;#def_application
     */
    @TableField(value = "application_id")
    private Long applicationId;

    /**
     * 编码;唯一编码，用于区分资源
     */
    @TableField(value = "code", condition = LIKE)
    private String code;

    @TableField(value = "name", condition = LIKE)
    private String name;

    /**
     * 类型;[20-菜单 30-视图 40-按钮 50-字段 06-数据]@Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, dictType = DictionaryType.Tenant.RESOURCE_TYPE)
     * 菜单即左侧显示的菜单，视图即隐藏的菜单(需要配置在路由中)
     */
    @TableField(value = "resource_type", condition = LIKE)
    private String resourceType;

    /**
     * 描述;resource_type=接口时表示接口说明
     */
    @TableField(value = "describe_", condition = LIKE)
    private String describe;

    /**
     * 地址栏路径;用于resource_type=菜单和视图和接口.resource_type=菜单和视图，表示地址栏地址, http开头表示外链, is_frame_src 为true表示在框架类打开.resource_type=接口，表示后端接口请求地址.
     */
    @TableField(value = "path", condition = LIKE)
    private String path;

    /**
     * 打开方式 [01-组件 02-内链 03-外链]
     */
    @TableField(value = "open_with", condition = LIKE)
    private String openWith;

    /**
     * 页面路径;用于resource_type=菜单和视图.
     * 前端页面在src/views目录下的相对地址.
     */
    @TableField(value = "component", condition = LIKE)
    private String component;

    /**
     * 重定向;用于resource_type=菜单和视图
     */
    @TableField(value = "redirect", condition = LIKE)
    private String redirect;

    /**
     * 图标
     */
    @TableField(value = "icon", condition = LIKE)
    private String icon;

    /**
     * 是否公共资源;1-无需分配所有人就可以访问的
     */
    @TableField(value = "is_general")
    private Boolean isGeneral;

    /**
     * 状态;[0-禁用 1-启用]
     */
    @TableField(value = "state")
    private Boolean state;

    /**
     * 分组
     */
    @TableField(value = "sub_group", condition = LIKE)
    private String subGroup;

    /**
     * 是否脱敏;显示时是否需要脱敏实现 (用于resource_type=字段)
     */
    @TableField(value = "field_is_secret")
    private Boolean fieldIsSecret;

    /**
     * 是否可以编辑;是否可以编辑(用于resource_type=字段)
     */
    @TableField(value = "field_is_edit")
    private Boolean fieldIsEdit;
    /**
     * 数据范围;[01-全部 02-本单位及子级 03-本单位 04-本部门 05-本部门及子级 06-个人 07-自定义]
     */
    @TableField(value = "data_scope", condition = LIKE)
    private String dataScope;
    /**
     * 实现类;自定义实现类全类名
     */
    @TableField(value = "custom_class", condition = LIKE)
    private String customClass;

    /**
     * 是否默认
     */
    @TableField(value = "is_def")
    private Boolean isDef;
    /**
     * 元数据;菜单视图的元数据
     */
    @TableField(value = "meta_json", condition = LIKE)
    private String metaJson;
    /**
     * 树层级
     */
    @Schema(description = "树层级")
    @TableField(value = "tree_grade")
    private Integer treeGrade;
    /**
     * 树路径;用id拼接树结构
     */
    @Schema(description = "树路径")
    @TableField(value = "tree_path", condition = LIKE)
    private String treePath;

}
