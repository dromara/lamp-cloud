package top.tangyh.lamp.generator.vo.result;

import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import top.tangyh.basic.annotation.echo.Echo;
import top.tangyh.basic.base.entity.Entity;
import top.tangyh.basic.interfaces.echo.EchoVO;
import top.tangyh.lamp.generator.enumeration.SqlConditionEnum;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 表单查询方法返回值VO
 * 代码生成字段
 * </p>
 *
 * @author zuihou
 * @date 2022-03-25 20:42:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Schema(description = "代码生成字段")
public class DefGenTableColumnResultVO extends Entity<Long> implements Serializable, EchoVO {

    private static final long serialVersionUID = 1L;
    @Builder.Default
    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "ID")
    private Long id;

    /**
     * 所属表ID
     */
    @Schema(description = "所属表ID")
    private Long tableId;
    /**
     * 列名称
     */
    @Schema(description = "列名称")
    private String name;
    /**
     * 列描述
     */
    @Schema(description = "列描述")
    private String comment;
    /**
     * swagger 描述
     */
    @Schema(description = "swagger 描述")
    private String swaggerComment;
    /**
     * 列类型
     */
    @Schema(description = "列类型")
    private String type;
    /**
     * JAVA类型
     */
    @Schema(description = "JAVA类型")
    private String javaType;
    /**
     * JAVA字段名
     */
    @Schema(description = "JAVA字段名")
    private String javaField;
    /**
     * TS类型
     */
    @Schema(description = "TS类型")
    private String tsType;
    /**
     * 长度
     */
    @Schema(description = "长度")
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
    private Boolean isPk;
    /**
     * 是否自增
     */
    @Schema(description = "是否自增")
    private Boolean isIncrement;
    /**
     * 是否必填
     */
    @Schema(description = "是否必填")
    private Boolean isRequired;
    /**
     * 是否为逻辑删除字段
     */
    @Schema(description = "是否为逻辑删除字段")
    private Boolean isLogicDeleteField;
    /**
     * 是否为乐观锁字段
     */
    @Schema(description = "是否为乐观锁字段")
    private Boolean isVersionField;
    /**
     * 填充类型
     */
    @Schema(description = "填充类型")
    private String fill;
    /**
     * 是否编辑字段
     */
    @Schema(description = "是否编辑字段")
    private Boolean isEdit;
    /**
     * 是否列表字段
     */
    @Schema(description = "是否列表字段")
    private Boolean isList;
    /**
     * 是否查询字段
     */
    @Schema(description = "是否查询字段")
    private Boolean isQuery;
    /**
     * 宽度
     */
    @Schema(description = "宽度")
    private String width;
    /**
     * 查询方式;
     * #SqlConditionEnum{EQUAL:01}
     * （等于、不等于、大于、小于、范围）
     */
    @Schema(description = "查询方式")
    @Echo(api = Echo.ENUM_API)
    private SqlConditionEnum queryType;
    /**
     * 显示组件;
     * （文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @Schema(description = "显示组件")
    private String component;

    @Schema(description = "Vxe显示组件")
    private String vxeComponent;
    /**
     * 字典类型
     */
    @Schema(description = "字典类型")
    private String dictType;
    /**
     * @Echo
     */
    @Schema(description = "@Echo")
    private String echoStr;
    /**
     * 枚举
     */
    @Schema(description = "枚举")
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
    private String editDefValue;
    /**
     * 编辑页面提示信息
     */
    @Schema(description = "编辑页面提示信息")
    private String editHelpMessage;
    /**
     * 主页提示信息
     */
    @Schema(description = "主页提示信息")
    private String indexHelpMessage;


}
