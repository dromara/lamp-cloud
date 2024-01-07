package ${package.Service};

<#list serviceImport as pkg>
import ${pkg};
</#list>
<#if superServiceClass??>
import ${superServiceClassPackage};
import ${entityPackage};
import ${saveVoPackage};
import ${updateVoPackage};
import ${resultVoPackage};
import ${pageQueryPackage};
</#if>


/**
 * <p>
 * 业务接口
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 * @create [${datetime}] [${author}] [代码生成器生成]
 */
<#if superServiceClass??>
public interface ${serviceName} extends ${superServiceClass}<${pkField.javaType}, ${table.entityName}> {
<#else>
public interface ${serviceName} {
</#if>

<#if isTreeEntity>
    /**
     * 查询树结构
     *
     * @param query 参数
     * @return 树
     */
    List<${table.entityName}> findTree(${pageQueryName} query);
</#if>
}


