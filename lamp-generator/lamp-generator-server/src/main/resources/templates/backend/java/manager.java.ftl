package ${package.Manager};

<#if superManagerClass??>
import ${superManagerClassPackage};
import ${entityPackage};
</#if>
<#list managerImport as pkg>
import ${pkg};
</#list>

/**
 * <p>
 * 通用业务接口
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 * @create [${datetime}] [${author}] [代码生成器生成]
 */
<#if superManagerClass??>
public interface ${managerName} extends ${superManagerClass}<${table.entityName}> {
<#else>
public interface ${managerName} {
</#if>

}


