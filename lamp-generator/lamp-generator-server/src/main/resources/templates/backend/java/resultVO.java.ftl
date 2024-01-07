package ${package.ResultVO};

<#list resultVoImport as pkg>
import ${pkg};
</#list>
import java.io.Serializable;

/**
 * <p>
 * 表单查询方法返回值VO
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 */
<#if table.isLombok>
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
    <#if table.isChain>
@Accessors(chain = true)
    </#if>
@EqualsAndHashCode(callSuper = <#if superEntityClass??>true<#else>false</#if>)
@Builder
</#if>
@Schema(description = "${table.swaggerComment}")
<#if superEntityClass??>
public class ${resultVoName} extends ${superEntityClass}<<#if isTreeEntity>${resultVoName}, </#if>${pkField.javaType}> implements Serializable, EchoVO {
<#else>
public class ${resultVoName} implements Serializable, EchoVO {
</#if>

    private static final long serialVersionUID = 1L;

    private Map<String, Object> echoMap = MapUtil.newHashMap();

    @Schema(description = "${pkField.swaggerComment!''}")
    private ${pkField.javaType} ${pkField.javaField};

<#list fields as field>
    /**
    * ${field.comment!?replace("\n","\n     * ")}
    */
    @Schema(description = "${field.swaggerComment!}")
    <#if field.echoStr?? && field.echoStr?trim != ''>
    ${field.echoStr}
    </#if>
    private ${field.javaType} ${field.javaField};
</#list>

<#if isTreeEntity>
    @Schema(description = "父节点")
    protected ${pkField.javaType} parentId;

    @Schema(description = "排序号")
    protected Integer sortValue;
</#if>

<#if !table.isLombok>
    @Override
    public Map<String, Object> getEchoMap() {
        return echoMap;
    }

    <#list fields as field>
    public ${field.javaType} get${field.javaField?cap_first}() {
        return ${field.javaField};
    }
    <#if table.isChain>
    public ${resultVoName} set${field.javaField?cap_first}(${field.javaType} ${field.javaField}) {
    <#else>
    public void set${field.javaField?cap_first}(${field.javaType} ${field.javaField}) {
    </#if>
        this.${field.javaField} = ${field.javaField};
    <#if table.isChain>
        return this;
    </#if>
    }

    </#list>
</#if>

<#if !table.isLombok>
    @Override
    public String toString() {
        return "${table.entityName}{" +
    <#list fields as field>
        <#if field_index==0>
            "${field.javaField}=" + ${field.javaField} +
        <#else>
            ", ${field.javaField}=" + ${field.javaField} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
