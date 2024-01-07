package top.tangyh.lamp.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.lamp.generator.enumeration.EntitySuperClassEnum;
import top.tangyh.lamp.generator.enumeration.GenTypeEnum;
import top.tangyh.lamp.generator.enumeration.PopupTypeEnum;
import top.tangyh.lamp.generator.enumeration.SuperClassEnum;
import top.tangyh.lamp.generator.enumeration.TplEnum;

import static com.baomidou.mybatisplus.annotation.SqlCondition.EQUAL;
import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 代码生成
 * </p>
 *
 * @author zuihou
 * @date 2022-04-07 09:33:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_gen_table")
public class DefGenTable extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 表描述
     */
    @TableField(value = "comment_", condition = LIKE)
    private String comment;
    /**
     * swagger 描述
     */
    @TableField(value = "swagger_comment", condition = LIKE)
    private String swaggerComment;
    /**
     * 数据源
     */
    @TableField(value = "ds_id", condition = EQUAL)
    private Long dsId;
    /**
     * 作者
     */
    @TableField(value = "author", condition = LIKE)
    private String author;
    /**
     * 关联子表的ID
     */
    @TableField(value = "sub_id", condition = EQUAL)
    private Long subId;
    /**
     * 子表关联的外键Java字段名
     */
    @TableField(value = "sub_java_field_name", condition = LIKE)
    private String subJavaFieldName;

    /**
     * 实体类名称
     */
    @TableField(value = "entity_name", condition = LIKE)
    private String entityName;
    /**
     * 实体父类;
     * #EntitySuperClassEnum{SUPER_ENTITY:01}
     */
    @TableField(value = "entity_super_class", condition = EQUAL)
    private EntitySuperClassEnum entitySuperClass;
    /**
     * 父类;
     * <p>
     * #SuperClassEnum{SUPER_CLASS:01}
     */
    @TableField(value = "super_class", condition = EQUAL)
    private SuperClassEnum superClass;
    /**
     * 基础包路径
     */
    @TableField(value = "parent", condition = LIKE)
    private String parent;
    /**
     * 前端应用名;
     * 如：src/views目录下的 basic 和 devOperation
     * <p>
     * basic 表示基础平台
     * <p>
     * devOperation 表示开发运营系统
     * <p>
     * xxx 表示你们自己新建的xxx系统
     */
    @TableField(value = "plus_application_name", condition = LIKE)
    private String plusApplicationName;
    /**
     * 前端模块名;
     * 如：src/views/devOperation 目录下的文件夹名
     * 如：src/views/basic 目录下的文件夹名
     */
    @TableField(value = "plus_module_name", condition = LIKE)
    private String plusModuleName;
    /**
     * 服务名
     */
    @TableField(value = "service_name", condition = LIKE)
    private String serviceName;
    /**
     * 模块名
     */
    @TableField(value = "module_name", condition = LIKE)
    private String moduleName;
    /**
     * 子包名
     */
    @TableField(value = "child_package_name", condition = LIKE)
    private String childPackageName;
    /**
     * 行级租户注解
     */
    @TableField(value = "is_tenant_line", condition = EQUAL)
    private Boolean isTenantLine;
    /**
     * 数据源
     */
    @TableField(value = "ds_value", condition = LIKE)
    private String dsValue;
    /**
     * 数据源级租户注解
     */
    @TableField(value = "is_ds", condition = EQUAL)
    private Boolean isDs;
    /**
     * 是否为lombok模型
     */
    @TableField(value = "is_lombok", condition = EQUAL)
    private Boolean isLombok;
    /**
     * 是否为链式模型
     */
    @TableField(value = "is_chain", condition = EQUAL)
    private Boolean isChain;
    /**
     * 是否生成字段常量
     */
    @TableField(value = "is_column_constant", condition = EQUAL)
    private Boolean isColumnConstant;
    /**
     * 生成代码方式;
     * #GenTypeEnum{GEN:01,直接生成;ZIP:02,打包下载;}
     * （01zip压缩包 02自定义路径）
     */
    @TableField(value = "gen_type", condition = EQUAL)
    private GenTypeEnum genType;
    /**
     * 生成路径;
     * （不填默认项目路径）
     */
    @TableField(value = "output_dir", condition = LIKE)
    private String outputDir;
    /**
     * 前端生成路径;
     * （不填默认项目路径）
     */
    @TableField(value = "front_output_dir", condition = LIKE)
    private String frontOutputDir;
    /**
     * 使用的模板;
     * #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}
     */
    @TableField(value = "tpl_type", condition = EQUAL)
    private TplEnum tplType;
    /**
     * 弹窗方式;
     * #PopupTypeEnum{MODAL:01,对话框;DRAWER:02,抽屉;}
     */
    @TableField(value = "popup_type", condition = EQUAL)
    private PopupTypeEnum popupType;
    /**
     * 新增按钮权限编码
     */
    @TableField(value = "add_auth", condition = LIKE)
    private String addAuth;
    /**
     * 编辑按钮权限编码
     */
    @TableField(value = "edit_auth", condition = LIKE)
    private String editAuth;
    /**
     * 删除按钮权限编码
     */
    @TableField(value = "delete_auth", condition = LIKE)
    private String deleteAuth;
    /**
     * 查看按钮权限编码
     */
    @TableField(value = "view_auth", condition = LIKE)
    private String viewAuth;
    /**
     * 复制按钮权限编码
     */
    @TableField(value = "copy_auth", condition = LIKE)
    private String copyAuth;
    /**
     * 新增按钮是否显示
     */
    @TableField(value = "add_show", condition = EQUAL)
    private Boolean addShow;
    /**
     * 编辑按钮是否显示
     */
    @TableField(value = "edit_show", condition = EQUAL)
    private Boolean editShow;
    /**
     * 删除按钮是否显示
     */
    @TableField(value = "delete_show", condition = EQUAL)
    private Boolean deleteShow;
    /**
     * 复制按钮是否显示
     */
    @TableField(value = "copy_show", condition = EQUAL)
    private Boolean copyShow;
    /**
     * 详情按钮是否显示
     */
    @TableField(value = "view_show", condition = EQUAL)
    private Boolean viewShow;
    /**
     * 其它生成选项
     */
    @TableField(value = "options", condition = LIKE)
    private String options;
    /**
     * 备注
     */
    @TableField(value = "remark", condition = LIKE)
    private String remark;
    /**
     * 上级菜单ID
     */
    @TableField(value = "menu_parent_id", condition = EQUAL, updateStrategy = FieldStrategy.ALWAYS)
    private Long menuParentId;
    /**
     * 所属应用ID
     */
    @TableField(value = "menu_application_id", condition = EQUAL)
    private Long menuApplicationId;
    /**
     * 菜单名称
     */
    @TableField(value = "menu_name", condition = LIKE)
    private String menuName;
    /**
     * 菜单图标
     */
    @TableField(value = "menu_icon", condition = LIKE)
    private String menuIcon;
    /**
     * 父ID字段名
     */
    @TableField(value = "tree_parent_id", condition = EQUAL)
    private Long treeParentId;
    /**
     * 名称字段名
     */
    @TableField(value = "tree_name", condition = LIKE)
    private String treeName;


}
