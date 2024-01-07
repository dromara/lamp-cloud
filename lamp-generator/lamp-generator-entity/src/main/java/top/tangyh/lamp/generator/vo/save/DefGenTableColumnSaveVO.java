package top.tangyh.lamp.generator.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 代码生成字段
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
@Schema(description = "代码生成字段")
public class DefGenTableColumnSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 归属表编号
     */
    @Schema(description = "归属表编号")
    @Size(max = 64, message = "归属表编号长度不能超过{max}")
    private String tableId;
    /**
     * 列名称
     */
    @Schema(description = "列名称")
    @Size(max = 200, message = "列名称长度不能超过{max}")
    private String columnName;
    /**
     * 列描述
     */
    @Schema(description = "列描述")
    @Size(max = 500, message = "列描述长度不能超过{max}")
    private String columnComment;
    /**
     * 列类型
     */
    @Schema(description = "列类型")
    @Size(max = 100, message = "列类型长度不能超过{max}")
    private String columnType;
    /**
     * JAVA类型
     */
    @Schema(description = "JAVA类型")
    @Size(max = 500, message = "JAVA类型长度不能超过{max}")
    private String javaType;
    /**
     * JAVA字段名
     */
    @Schema(description = "JAVA字段名")
    @Size(max = 200, message = "JAVA字段名长度不能超过{max}")
    private String javaField;
    /**
     * 是否主键（1是）
     */
    @Schema(description = "是否主键（1是）")
    @Size(max = 1, message = "是否主键（1是）长度不能超过{max}")
    private String isPk;
    /**
     * 是否自增（1是）
     */
    @Schema(description = "是否自增（1是）")
    @Size(max = 1, message = "是否自增（1是）长度不能超过{max}")
    private String isIncrement;
    /**
     * 是否必填（1是）
     */
    @Schema(description = "是否必填（1是）")
    @Size(max = 1, message = "是否必填（1是）长度不能超过{max}")
    private String isRequired;
    /**
     * 是否为插入字段（1是）
     */
    @Schema(description = "是否为插入字段（1是）")
    @Size(max = 1, message = "是否为插入字段（1是）长度不能超过{max}")
    private String isInsert;
    /**
     * 是否编辑字段（1是）
     */
    @Schema(description = "是否编辑字段（1是）")
    @Size(max = 1, message = "是否编辑字段（1是）长度不能超过{max}")
    private String isEdit;
    /**
     * 是否列表字段（1是）
     */
    @Schema(description = "是否列表字段（1是）")
    @Size(max = 1, message = "是否列表字段（1是）长度不能超过{max}")
    private String isList;
    /**
     * 是否查询字段（1是）
     */
    @Schema(description = "是否查询字段（1是）")
    @Size(max = 1, message = "是否查询字段（1是）长度不能超过{max}")
    private String isQuery;
    /**
     * 宽度
     */
    @Schema(description = "宽度")
    @Size(max = 255, message = "宽度长度不能超过{max}")
    private String width;
    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    @Schema(description = "查询方式（等于、不等于、大于、小于、范围）")
    @Size(max = 200, message = "查询方式（等于、不等于、大于、小于、范围）长度不能超过{max}")
    private String queryType;
    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @Schema(description = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 200, message = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    private String component;

    @Schema(description = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    @Size(max = 255, message = "VXE显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）长度不能超过{max}")
    private String vxeComponent;

    /**
     * 枚举类型
     */
    @Schema(description = "枚举类型")
    @Size(max = 255, message = "枚举类型长度不能超过{max}")
    private String enumType;
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    @Size(max = 200, message = "字典类型长度不能超过{max}")
    private String dictType;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sortValue;
    @Schema(description = "")
    @Size(max = 255, message = "长度不能超过{max}")
    private String editDefValue;
    @Schema(description = "")
    @Size(max = 255, message = "长度不能超过{max}")
    private String editHelpMessage;

}
