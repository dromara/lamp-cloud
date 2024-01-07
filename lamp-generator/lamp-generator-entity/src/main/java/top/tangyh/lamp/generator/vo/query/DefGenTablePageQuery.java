package top.tangyh.lamp.generator.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * 实体类
 * 代码生成
 * </p>
 *
 * @author zuihou
 * @since 2022-03-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@Schema(description = "代码生成")
public class DefGenTablePageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @Schema(description = "表名称")
    private String name;

    @Schema(description = "数据源名称")
    private Long dsId;

    /**
     * 表描述
     */
    @Schema(description = "表描述")
    private String comment;
    @Schema(description = "swagger描述")
    private String swaggerComment;
    @Schema(description = "作者")
    private String author;
    /**
     * 关联子表的表名
     */
    @Schema(description = "关联子表的表名")
    private String subTableName;
    /**
     * 子表关联的外键名
     */
    @Schema(description = "子表关联的外键名")
    private String subTableFkName;
    /**
     * 实体类名称
     */
    @Schema(description = "实体类名称")
    private String entityName;
    /**
     * 非实体父类
     */
    @Schema(description = "非实体父类")
    private SuperClassEnum superClass;
    /**
     * 实体父类
     */
    @Schema(description = "实体父类")
    private EntitySuperClassEnum entitySuperClass;

    /**
     * 生成包路径
     */
    @Schema(description = "生成包路径")
    private String parent;

    /**
     * 生成的服务名
     * <p>
     * 如： lamp-base-server 中的base
     * 如： lamp-system-server system
     */
    @Schema(description = "生成的服务名")
    private String serviceName;
    /**
     * 前端应用名
     * 如：src/views目录下的 basic 和 devOperation
     * <p>
     * basic 表示基础平台
     * devOperation 表示开发运营系统
     * xxx 表示你们自己新建的xxx系统
     */
    @Schema(description = "前端应用名")
    private String plusApplicationName;
    /**
     * 前端模块名
     * 如：src/views/devOperation 目录下的文件夹名
     * 如：src/views/basic 目录下的文件夹名
     * <p>
     */
    @Schema(description = "前端模块名")
    private String plusModuleName;
    /**
     * 生成模块名
     * <p>
     * 如： top.tangyh.lamp.base.dao.common 包中的 base
     * 如： top.tangyh.lamp.file.dao.xxx 包中的 file
     */
    @Schema(description = "生成模块名")
    private String moduleName;
    /**
     * 子包
     * <p>
     * 如： top.tangyh.lamp.base.dao.common 包中的 common
     * 如： top.tangyh.lamp.file.dao.xxx 包中的 xxx
     */
    @Schema(description = "子包")
    private String childPackageName;
    /**
     * 是否添加行级租户注解
     */
    @Schema(description = "是否添加行级租户注解")
    private Boolean isTenantLine;
    /**
     * 是否添加数据源级租户注解
     */
    @Schema(description = "是否添加数据源级租户注解")
    private Boolean isDs;
    /**
     * 数据源级租户 数据源
     */
    @Schema(description = "数据源")
    private String dsValue;

    /**
     * 是否为lombok模型
     */
    @Schema(description = "是否为lombok模型")
    private Boolean isLombok;
    /**
     * 是否为链式模型
     */
    @Schema(description = "是否为链式模型")
    private Boolean isChain;
    /**
     * 是否生成字段常量
     */
    @Schema(description = "是否生成字段常量")
    private Boolean isColumnConstant;
    /**
     * 生成代码方式（0-打包下载 1-直接生成）
     */
    @Schema(description = "生成代码方式")
    private GenTypeEnum genType;

    /**
     * 使用的模板
     */
    @Schema(description = "使用的模板")
    private TplEnum tplType;
    /**
     * 弹窗方式
     */
    @Schema(description = "弹窗方式")
    private PopupTypeEnum popupType;
    /**
     * 菜单名
     */
    @Schema(description = "菜单名")
    private String menuName;

    /**
     * 后端生成路径（不填默认项目路径）
     */
    @Schema(description = "后端生成路径")
    private String outputDir;
    /**
     * 前端生成路径（不填默认项目路径）
     */
    @Schema(description = "前端生成路径")
    private String frontOutputDir;
    /**
     * 新增按钮权限编码
     */
    @Schema(description = "新增按钮权限编码")
    private String addAuth;
    /**
     * 编辑按钮权限编码
     */
    @Schema(description = "编辑按钮权限编码")
    private String editAuth;
    /**
     * 删除按钮权限编码
     */
    @Schema(description = "删除按钮权限编码")
    private String deleteAuth;
    /**
     * 复制按钮权限编码
     */
    @Schema(description = "复制按钮权限编码")
    private String copyAuth;
    /**
     * 新增按钮是否显示
     */
    @Schema(description = "新增按钮是否显示")
    private Boolean addShow;

    @Schema(description = "详情按钮是否显示")
    private Boolean viewShow;

    /**
     * 编辑按钮是否显示
     */
    @Schema(description = "编辑按钮是否显示")
    private Boolean editShow;


    /**
     * 删除按钮是否显示
     */
    @Schema(description = "删除按钮是否显示")
    private Boolean deleteShow;
    /**
     * 复制按钮是否显示
     */
    @Schema(description = "复制按钮是否显示")
    private Boolean copyShow;
    /**
     * 其它生成选项
     */
    @Schema(description = "其它生成选项")
    private String options;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}
