package ${package.Mapper};

<#list mapperImport as pkg>
import ${pkg};
</#list>
<#if superMapperClass??>
import ${superMapperClassPackage};
import ${entityPackage};
</#if>
import org.springframework.stereotype.Repository;
<#if table.isTenantLine>
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
</#if>

/**
 * <p>
 * Mapper 接口
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 * @create [${datetime}] [${author}] [代码生成器生成]
 */
@Repository
<#if table.isTenantLine>
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
</#if>
<#if superMapperClass??>
public interface ${mapperName} extends ${superMapperClass}<${table.entityName}> {
<#else>
public interface ${mapperName} {
</#if>

}


