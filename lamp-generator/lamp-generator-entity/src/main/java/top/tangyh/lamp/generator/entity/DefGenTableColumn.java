package top.tangyh.lamp.generator.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
import top.tangyh.lamp.generator.enumeration.SqlConditionEnum;

import static top.tangyh.lamp.model.constant.Condition.LIKE;


/**
 * <p>
 * 实体类
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-24 16:45:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@TableName("def_gen_table_column")
public class DefGenTableColumn extends Entity<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 所属表ID
     */
    @TableField(value = "table_id")
    private Long tableId;
    /**
     * 列名称
     */
    @TableField(value = "name", condition = LIKE)
    private String name;
    /**
     * 列描述
     */
    @TableField(value = "comment_", condition = LIKE)
    private String comment;
    /**
     * swagger 描述
     */
    @TableField(value = "swagger_comment", condition = LIKE)
    private String swaggerComment;
    /**
     * 列类型
     */
    @TableField(value = "type", condition = LIKE)
    private String type;
    /**
     * JAVA类型
     */
    @TableField(value = "java_type", condition = LIKE)
    private String javaType;
    /**
     * JAVA字段名
     */
    @TableField(value = "java_field", condition = LIKE)
    private String javaField;
    /**
     * TS类型
     */
    @TableField(value = "ts_type", condition = LIKE)
    private String tsType;
    /**
     * 长度
     */
    @TableField(value = "size_")
    private Long size;
    /**
     * 小数位数
     */
    @TableField(value = "digit")
    private Integer digit;
    /**
     * 是否主键
     */
    @TableField(value = "is_pk")
    private Boolean isPk;
    /**
     * 是否自增
     */
    @TableField(value = "is_increment")
    private Boolean isIncrement;
    /**
     * 是否必填
     */
    @TableField(value = "is_required")
    private Boolean isRequired;
    /**
     * 是否为逻辑删除字段
     */
    @TableField(value = "is_logic_delete_field")
    private Boolean isLogicDeleteField;
    /**
     * 是否为乐观锁字段
     */
    @TableField(value = "is_version_field")
    private Boolean isVersionField;
    /**
     * 填充类型
     */
    @TableField(value = "fill", condition = LIKE)
    private FieldFill fill;
    /**
     * 是否编辑字段
     */
    @TableField(value = "is_edit")
    private Boolean isEdit;
    /**
     * 是否列表字段
     */
    @TableField(value = "is_list")
    private Boolean isList;
    /**
     * 是否查询字段
     */
    @TableField(value = "is_query")
    private Boolean isQuery;
    /**
     * 宽度
     */
    @TableField(value = "width", condition = LIKE)
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @TableField(value = "query_type")
    private SqlConditionEnum queryType;
    /**
     * 显示组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField(value = "component", condition = LIKE)
    private String component;
    /**
     * vxe表格组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField(value = "vxe_component", condition = LIKE)
    private String vxeComponent;
    /**
     * 字典类型
     */
    @TableField(value = "dict_type", condition = LIKE)
    private String dictType;
    /**
     * @Echo
     */
    @TableField(value = "echo_str", condition = LIKE)
    private String echoStr;
    /**
     * 枚举
     */
    @TableField(value = "enum_str", condition = LIKE)
    private String enumStr;
    /**
     * 排序
     */
    @TableField(value = "sort_value")
    private Integer sortValue;
    /**
     * 编辑页面默认值
     */
    @TableField(value = "edit_def_value", condition = LIKE)
    private String editDefValue;
    /**
     * 编辑页面提示信息
     */
    @TableField(value = "edit_help_message", condition = LIKE)
    private String editHelpMessage;
    /**
     * 主页提示信息
     */
    @TableField(value = "index_help_message", condition = LIKE)
    private String indexHelpMessage;


}
