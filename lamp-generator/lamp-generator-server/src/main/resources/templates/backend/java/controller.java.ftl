package ${package.Controller};

<#list controllerImport as pkg>
import ${pkg};
</#list>
<#if superControllerClass??>
import ${superControllerClassPackage};
import ${servicePackage};
import ${entityPackage};
import ${saveVoPackage};
import ${updateVoPackage};
import ${resultVoPackage};
import ${pageQueryPackage};
</#if>
<#if controllerConfig.restStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>

/**
 * <p>
 * 前端控制器
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
@Validated
<#if controllerConfig.restStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>")
@Tag(name = "${table.swaggerComment}")
<#if superControllerClass??>
public class ${controllerName} extends ${superControllerClass}<${serviceName}, ${pkField.javaType}, ${table.entityName}
    <#if superControllerClass == "SuperCacheController" || superControllerClass == "SuperController" || superControllerClass == "SuperWriteController">, ${saveVoName}, ${updateVoName}</#if><#if superControllerClass == "SuperCacheController" || superControllerClass == "SuperController" || superControllerClass == "SuperReadController">, ${pageQueryName}, ${resultVoName}</#if>> {
<#else>
public class ${controllerName} {
</#if>
<#if table.isLombok>
    private final EchoService echoService;
<#else>
    @Autowired
    private EchoService echoService;
</#if>
<#if superControllerClass?? && superControllerClass != superSimpleControllerSimpleName>
    @Override
    public EchoService getEchoService() {
        return echoService;
    }
</#if>

<#if isTreeEntity>
    /**
     * 按树结构查询
     *
     * @param pageQuery 查询参数
     * @return 查询结果
     */
    @Operation(summary = "按树结构查询", description = "按树结构查询")
    @PostMapping("/tree")
    @WebLog("级联查询")
    public R<List<${table.entityName}>> tree(@RequestBody ${pageQueryName} pageQuery) {
        return success(superService.findTree(pageQuery));
    }
</#if>
}


