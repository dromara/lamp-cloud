package ${package.Entity};

<#list entityImport as pkg>
import ${pkg};
</#list>
<#if superEntityClass??>
<#else>
import java.io.Serializable;
</#if>


/**
 * <p>
 * 实体类
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
@TableName("${table.name}")
<#if superEntityClass??>
public class ${table.entityName} extends ${superEntityClass}<<#if isTreeEntity>${table.entityName}, </#if>${pkField.javaType}> {
<#else>
public class ${table.entityName} implements Serializable {
</#if>
    private static final long serialVersionUID = 1L;

<#list fields as field>
    /**
     * ${field.comment!?replace("\n","\n     * ")}
     */
<#if field.isPk>
    <#-- 主键 -->
    @TableId(value = "${field.name}"<#if field.isIncrement>, type = IdType.AUTO<#elseif entityConfig.idType??>, type = IdType.${entityConfig.idType}</#if>)
<#else>
    @TableField(value = "${field.name}"<#if field.fill?? && field.fill?trim != ''>, fill = FieldFill.${field.fill}</#if><#if field.queryType?? && field.queryType?trim != ''>, condition = ${field.queryType}<#else></#if>)
</#if>
<#-- 乐观锁注解 -->
<#if field.isVersionField>
    @Version
</#if>
<#-- 逻辑删除注解 -->
<#if field.isLogicDeleteField>
    @TableLogic
</#if>
    @Schema(description = "${field.swaggerComment!''}")
    private ${field.javaType} ${field.javaField};
</#list>

<#if !table.isLombok>
    <#list fields as field>
    public ${field.javaType} get${field.javaField?cap_first}() {
        return ${field.javaField};
    }
    public <#if table.isChain>${table.entityName}<#else>void</#if> set${field.javaField?cap_first}(${field.javaType} ${field.javaField}) {
        this.${field.javaField} = ${field.javaField};
    <#if table.isChain>
        return this;
    </#if>
    }

    </#list>
</#if>

<#if table.isColumnConstant>
    <#list fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";
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
