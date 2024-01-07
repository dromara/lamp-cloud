package top.tangyh.lamp.generator.vo.update;

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
import top.tangyh.basic.base.entity.SuperEntity;
import top.tangyh.lamp.generator.enumeration.SqlConditionEnum;

import java.io.Serializable;

/**
 * <p>
 * 表单修改方法VO
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-24 13:27:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode
@Builder
@Schema(description = "代码生成字段")
public class DefGenTableColumnUpdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    @NotNull(message = "请填写ID", groups = SuperEntity.Update.class)
    private Long id;

    /**
     * 所属表ID
     */
    @Schema(description = "所属表ID")
    @NotNull(message = "请填写所属表ID")
    private Long tableId;
    /**
     * 列名称
     */
    @Schema(description = "列名称")
    @NotEmpty(message = "请填写列名称")
    @Size(max = 255, message = "列名称长度不能超过{max}")
    private String name;
    /**
     * 列描述
     */
    @Schema(description = "列描述")
    @NotEmpty(message = "请填写列描述")
    @Size(max = 500, message = "列描述长度不能超过{max}")
    private String comment;
    /**
     * swagger 描述
     */
    @Schema(description = "swagger 描述")
    @NotEmpty(message = "请填写swagger 描述")
    @Size(max = 255, message = "swagger 描述长度不能超过{max}")
    private String swaggerComment;
    /**
     * 列类型
     */
    @Schema(description = "列类型")
    @NotEmpty(message = "请填写列类型")
    @Size(max = 255, message = "列类型长度不能超过{max}")
    private String type;
    /**
     * JAVA类型
     */
    @Schema(description = "JAVA类型")
    @NotEmpty(message = "请填写JAVA类型")
    @Size(max = 500, message = "JAVA类型长度不能超过{max}")
    private String javaType;
    /**
     * JAVA字段名
     */
    @Schema(description = "JAVA字段名")
    @NotEmpty(message = "请填写JAVA字段名")
    @Size(max = 255, message = "JAVA字段名长度不能超过{max}")
    private String javaField;
    /**
     * TS类型
     */
    @Schema(description = "TS类型")
    @NotEmpty(message = "请填写TS类型")
    @Size(max = 255, message = "TS类型长度不能超过{max}")
    private String tsType;
    /**
     * 长度
     */
    @Schema(description = "长度")
    @NotNull(message = "请填写长度")
    private Integer size;
    /**
     * 小数位数
     */
    @Schema(description = "小数位数")
    private Integer digit;
    /**
     * 是否主键
     */
    @Schema(description = "是否主键")
    @NotNull(message = "请填写是否主键")
    private Boolean isPk;
    /**
     * 是否自增
     */
    @Schema(description = "是否自增")
    @NotNull(message = "请填写是否自增")
    private Boolean isIncrement;
    /**
     * 是否必填
     */
    @Schema(description = "是否必填")
    @NotNull(message = "请填写是否必填")
    private Boolean isRequired;
    /**
     * 是否为逻辑删除字段
     */
    @Schema(description = "是否为逻辑删除字段")
    @NotNull(message = "请填写是否为逻辑删除字段")
    private Boolean isLogicDeleteField;
    /**
     * 是否为乐观锁字段
     */
    @Schema(description = "是否为乐观锁字段")
    @NotNull(message = "请填写是否为乐观锁字段")
    private Boolean isVersionField;
    /**
     * 填充类型
     */
    @Schema(description = "填充类型")
    @Size(max = 255, message = "填充类型长度不能超过{max}")
    private String fill;
    /**
     * 是否编辑字段
     */
    @Schema(description = "是否编辑字段")
    @NotNull(message = "请填写是否编辑字段")
    private Boolean isEdit;
    /**
     * 是否列表字段
     */
    @Schema(description = "是否列表字段")
    @NotNull(message = "请填写是否列表字段")
    private Boolean isList;
    /**
     * 是否查询字段
     */
    @Schema(description = "是否查询字段")
    @NotNull(message = "请填写是否查询字段")
    private Boolean isQuery;
    /**
     * 宽度
     */
    @Schema(description = "宽度")
    @Size(max = 255, message = "宽度长度不能超过{max}")
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @Schema(description = "查询方式")
    private SqlConditionEnum queryType;
    /**
     * 显示组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @Schema(description = "显示组件")
    @Size(max = 200, message = "显示组件长度不能超过{max}")
    @NotEmpty(message = "请填写显示组件")
    private String component;

    @Schema(description = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 255, message = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    @NotEmpty(message = "请填写VXE显示组件")
    private String vxeComponent;

    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @Size(max = 255, message = "字典类型长度不能超过{max}")
    private String dictType;
    /**
     * @Echo
     */
    @Schema(description = "@Echo")
    @Size(max = 255, message = "@Echo长度不能超过{max}")
    private String echoStr;
    /**
     * 枚举
     */
    @Schema(description = "枚举")
    @Size(max = 255, message = "枚举长度不能超过{max}")
    private String enumStr;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortValue;
    /**
     * 编辑页面默认值
     */
    @Schema(description = "编辑页面默认值")
    @Size(max = 255, message = "编辑页面默认值长度不能超过{max}")
    private String editDefValue;
    /**
     * 编辑页面提示信息
     */
    @Schema(description = "编辑页面提示信息")
    @Size(max = 255, message = "编辑页面提示信息长度不能超过{max}")
    private String editHelpMessage;
    /**
     * 主页提示信息
     */
    @Schema(description = "主页提示信息")
    @Size(max = 255, message = "主页提示信息长度不能超过{max}")
    private String indexHelpMessage;


}
