package ${package.Enum};

<#list enumImport as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * 实体注释中生成的类型枚举
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "${enumType.swaggerComment!}-枚举")
public enum ${enumType.enumName} implements BaseEnum {

<#list enumType.kvList as kv>
    /**
     * ${kv.key?upper_case}
     */
    ${kv.key?upper_case}(<#list kv.values as value>"${value?trim}"<#if value_has_next>,</#if></#list>),
</#list>
    ;

<#list enumType.kvList[0].values as value>
    <#if value_index == 1 || enumType.kvList[0].values?size == 1>
    @Schema(description = "描述")
    <#elseif value_index == 0>
    @Schema(description = "数据库存储值")
    </#if>
    private String <#if value_index == 1 || enumType.kvList[0].values?size == 1>desc<#elseif value_index == 0>value<#else>other${value_index}</#if>;
</#list>

    /**
     * 根据当前枚举的name匹配
     */
    public static ${enumType.enumName} match(String val, ${enumType.enumName} def) {
        return Stream.of(values()).parallel().filter(item -> item.name().equalsIgnoreCase(val)).findAny().orElse(def);
    }

    public static ${enumType.enumName} get(String val) {
        return match(val, null);
    }

    public boolean eq(${enumType.enumName} val) {
        return val != null && eq(val.name());
    }

    @Override
    @Schema(description = "name", allowableValues = "<#list enumType.kvList as kv>${kv.key?upper_case}<#if kv_has_next>,</#if></#list>", example = "${enumType.kvList[0].key?upper_case}")
    public String getCode() {
        return this.name();
    }

    @Override
    @Schema(description = "数据库中的值")
    public String getValue() {
        return <#if enumType.kvList[0].values?size == 1>this.name()<#else>this.value</#if>;
    }

}
