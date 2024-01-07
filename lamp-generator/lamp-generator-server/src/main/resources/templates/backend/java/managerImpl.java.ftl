package ${package.ManagerImpl};

<#list managerImplImport as pkg>
import ${pkg};
</#list>
import ${managerPackage};
<#if superManagerImplClass??>
import ${entityPackage};
import ${superManagerImplClassPackage};
import ${mapperPackage};
</#if>

/**
 * <p>
 * 通用业务实现类
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 * @create [${datetime}] [${author}] [代码生成器生成]
 */
<#if table.isLombok>
@Slf4j
@RequiredArgsConstructor
</#if>
@Service
<#if superManagerImplClass??>
public class ${managerImplName} extends ${superManagerImplClass}<${mapperName}, ${table.entityName}> implements ${managerName} {
<#else>
public class ${managerImplName} implements ${managerName} {
</#if>

<#if superManagerImplClass?? && superManagerImplClass == superCacheManagerImplSimpleName>
    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        // TODO 需要自行新建一个 CacheKeyBuilder
        return null;
    }
</#if>
}


