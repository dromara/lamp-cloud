package top.tangyh.lamp.generator.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.lamp.generator.enumeration.EntitySuperClassEnum;
import top.tangyh.lamp.generator.enumeration.GenTypeEnum;
import top.tangyh.lamp.generator.enumeration.PopupTypeEnum;
import top.tangyh.lamp.generator.enumeration.SuperClassEnum;
import top.tangyh.lamp.generator.enumeration.TplEnum;

import java.io.Serializable;

/**
 * <p>
 * 表单保存方法VO
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
@EqualsAndHashCode
@Builder
@Schema(description = "代码生成")
public class DefGenTableSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @Schema(description = "表名称")
    @NotEmpty(message = "请填写表名称")
    @Size(max = 200, message = "表名称长度不能超过{max}")
    private String name;
    /**
     * 表描述
     */
    @Schema(description = "表描述")
    @NotEmpty(message = "请填写表描述")
    @Size(max = 500, message = "表描述长度不能超过{max}")
    private String comment;
    /**
     * swagger 描述
     */
    @Schema(description = "swagger 描述")
    @NotEmpty(message = "请填写swagger 描述")
    @Size(max = 255, message = "swagger 描述长度不能超过{max}")
    private String swaggerComment;
    /**
     * 数据源
     */
    @Schema(description = "数据源")
    @NotNull(message = "请填写数据源")
    private Long dsId;
    /**
     * 作者
     */
    @Schema(description = "作者")
    @NotEmpty(message = "请填写作者")
    @Size(max = 255, message = "作者长度不能超过{max}")
    private String author;
    /**
     * 关联子表ID
     */
    @Schema(description = "关联子表ID")
    private Long subId;
    /**
     * 子表关联的外键名
     */
    @Schema(description = "子表关联的外键字段名")
    @Size(max = 255, message = "子表关联的外键字段名长度不能超过{max}")
    private String subJavaFieldName;
    /**
     * 实体类名称
     */
    @Schema(description = "实体类名称")
    @NotEmpty(message = "请填写实体类名称")
    @Size(max = 255, message = "实体类名称长度不能超过{max}")
    private String entityName;
    /**
     * 实体父类;
     * #EntitySuperClassEnum{SUPER_ENTITY:01}
     */
    @Schema(description = "实体父类")
    @NotNull(message = "请填写实体父类")
    private EntitySuperClassEnum entitySuperClass;
    /**
     * 父类;
     * <p>
     * #SuperClassEnum{SUPER_CLASS:01}
     */
    @Schema(description = "父类")
    @NotNull(message = "请填写父类")
    private SuperClassEnum superClass;
    /**
     * 基础包路径
     */
    @Schema(description = "基础包路径")
    @NotEmpty(message = "请填写基础包路径")
    @Size(max = 255, message = "基础包路径长度不能超过{max}")
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
    @Schema(description = "前端应用名")
    @NotEmpty(message = "请填写前端应用名")
    @Size(max = 255, message = "前端应用名长度不能超过{max}")
    private String plusApplicationName;
    /**
     * 前端模块名;
     * 如：src/views/devOperation 目录下的文件夹名
     * 如：src/views/basic 目录下的文件夹名
     */
    @Schema(description = "前端模块名")
    @NotEmpty(message = "请填写前端模块名")
    @Size(max = 255, message = "前端模块名长度不能超过{max}")
    private String plusModuleName;
    /**
     * 服务名
     */
    @Schema(description = "服务名")
    @NotEmpty(message = "请填写服务名")
    @Size(max = 255, message = "服务名长度不能超过{max}")
    private String serviceName;
    /**
     * 模块名
     */
    @Schema(description = "模块名")
    @NotEmpty(message = "请填写模块名")
    @Size(max = 255, message = "模块名长度不能超过{max}")
    private String moduleName;
    /**
     * 子包名
     */
    @Schema(description = "子包名")
    @Size(max = 255, message = "子包名长度不能超过{max}")
    private String childPackageName;
    /**
     * 行级租户注解
     */
    @Schema(description = "行级租户注解")
    @NotNull(message = "请填写行级租户注解")
    private Boolean isTenantLine;
    /**
     * 数据源
     */
    @Schema(description = "数据源")
    @Size(max = 255, message = "数据源长度不能超过{max}")
    private String dsValue;
    /**
     * 数据源级租户注解
     */
    @Schema(description = "数据源级租户注解")
    @NotNull(message = "请填写数据源级租户注解")
    private Boolean isDs;
    /**
     * 是否为lombok模型
     */
    @Schema(description = "是否为lombok模型")
    @NotNull(message = "请填写是否为lombok模型")
    private Boolean isLombok;
    /**
     * 是否为链式模型
     */
    @Schema(description = "是否为链式模型")
    @NotNull(message = "请填写是否为链式模型")
    private Boolean isChain;
    /**
     * 是否生成字段常量
     */
    @Schema(description = "是否生成字段常量")
    @NotNull(message = "请填写是否生成字段常量")
    private Boolean isColumnConstant;
    /**
     * 生成代码方式;
     * #GenTypeEnum{GEN:01,直接生成;ZIP:02,打包下载;}
     * （01zip压缩包 02自定义路径）
     */
    @Schema(description = "生成代码方式")
    @NotNull(message = "请填写生成代码方式")
    private GenTypeEnum genType;
    /**
     * 生成路径;
     * （不填默认项目路径）
     */
    @Schema(description = "生成路径")
    @Size(max = 255, message = "生成路径长度不能超过{max}")
    private String outputDir;
    /**
     * 前端生成路径;
     * （不填默认项目路径）
     */
    @Schema(description = "前端生成路径")
    @Size(max = 255, message = "前端生成路径长度不能超过{max}")
    private String frontOutputDir;
    /**
     * 使用的模板;
     * #TplEnum{SIMPLE:01,单表;TREE:02,树结构;MAIN_SUB:03,主从结构}
     */
    @Schema(description = "使用的模板")
    @NotNull(message = "请填写使用的模板")
    private TplEnum tplType;
    /**
     * 弹窗方式;
     * #PopupTypeEnum{MODAL:01,对话框;DRAWER:02,抽屉;}
     */
    @Schema(description = "弹窗方式")
    @NotNull(message = "请填写弹窗方式")
    private PopupTypeEnum popupType;
    /**
     * 新增按钮权限编码
     */
    @Schema(description = "新增按钮权限编码")
    @Size(max = 255, message = "新增按钮权限编码长度不能超过{max}")
    private String addAuth;
    /**
     * 编辑按钮权限编码
     */
    @Schema(description = "编辑按钮权限编码")
    @Size(max = 255, message = "编辑按钮权限编码长度不能超过{max}")
    private String editAuth;
    /**
     * 删除按钮权限编码
     */
    @Schema(description = "删除按钮权限编码")
    @Size(max = 255, message = "删除按钮权限编码长度不能超过{max}")
    private String deleteAuth;
    /**
     * 复制按钮权限编码
     */
    @Schema(description = "复制按钮权限编码")
    @Size(max = 255, message = "复制按钮权限编码长度不能超过{max}")
    private String copyAuth;
    /**
     * 新增按钮是否显示
     */
    @Schema(description = "新增按钮是否显示")
    @NotNull(message = "请填写新增按钮是否显示")
    private Boolean addShow;
    /**
     * 编辑按钮是否显示
     */
    @Schema(description = "编辑按钮是否显示")
    @NotNull(message = "请填写编辑按钮是否显示")
    private Boolean editShow;
    /**
     * 删除按钮是否显示
     */
    @Schema(description = "删除按钮是否显示")
    @NotNull(message = "请填写删除按钮是否显示")
    private Boolean deleteShow;
    /**
     * 复制按钮是否显示
     */
    @Schema(description = "复制按钮是否显示")
    @NotNull(message = "请填写复制按钮是否显示")
    private Boolean copyShow;
    /**
     * 详情按钮是否显示
     */
    @Schema(description = "详情按钮是否显示")
    @NotNull(message = "请填写详情按钮是否显示")
    private Boolean viewShow;
    /**
     * 其它生成选项
     */
    @Schema(description = "其它生成选项")
    @Size(max = 1000, message = "其它生成选项长度不能超过{max}")
    private String options;
    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过{max}")
    private String remark;
    /**
     * 上级菜单ID
     */
    @Schema(description = "上级菜单ID")
    @NotNull(message = "请填写上级菜单ID")
    private Long menuParentId;
    /**
     * 所属应用ID
     */
    @Schema(description = "所属应用ID")
    @NotNull(message = "请填写所属应用ID")
    private Long menuApplicationId;
    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @NotEmpty(message = "请填写菜单名称")
    @Size(max = 255, message = "菜单名称长度不能超过{max}")
    private String menuName;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    @Size(max = 255, message = "菜单图标长度不能超过{max}")
    private String menuIcon;
    /**
     * 父ID字段名
     */
    @Schema(description = "父ID字段名")
    private Long treeParentId;
    /**
     * 名称字段名
     */
    @Schema(description = "名称字段名")
    @Size(max = 255, message = "名称字段名长度不能超过{max}")
    private String treeName;


}
