package top.tangyh.lamp.system.controller.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.system.biz.application.DefResourceBiz;
import top.tangyh.lamp.system.entity.application.DefResource;
import top.tangyh.lamp.system.service.application.DefResourceService;
import top.tangyh.lamp.system.vo.query.application.DefResourcePageQuery;
import top.tangyh.lamp.system.vo.result.application.DefResourceResultVO;
import top.tangyh.lamp.system.vo.save.application.DefResourceSaveVO;
import top.tangyh.lamp.system.vo.update.application.DefResourceUpdateVO;

import java.util.List;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;


/**
 * <p>
 * 前端控制器
 * 资源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defResource")
@Tag(name = "资源")
public class DefResourceController extends SuperController<DefResourceService, Long, DefResource, DefResourceSaveVO, DefResourceUpdateVO, DefResourcePageQuery, DefResourceResultVO> {

    private final EchoService echoService;
    private final DefResourceBiz defResourceBiz;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "code", description = "编码", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测资源编码是否可用", description = "检测资源编码是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String code) {
        return success(superService.check(id, code));
    }


    @Parameters({
            @Parameter(name = "path", description = "编码", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测资源路径是否可用", description = "检测资源路径是否可用")
    @GetMapping("/checkPath")
    public R<Boolean> checkPath(@RequestParam(required = false) Long id, @RequestParam Long applicationId, @RequestParam String path) {
        return success(superService.checkPath(id, applicationId, path));
    }

    @Parameters({
            @Parameter(name = "name", description = "编码", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测资源名称是否可用", description = "检测资源名称是否可用")
    @GetMapping("/checkName")
    public R<Boolean> checkName(@RequestParam(required = false) Long id, @RequestParam Long applicationId, @RequestParam String name) {
        return success(superService.checkName(id, applicationId, name));
    }

    @Override
    public R<DefResource> handlerSave(DefResourceSaveVO data) {
        return success(superService.saveWithCache(data));
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(defResourceBiz.removeByIdWithCache(ids));
    }

    @Override
    public R<DefResource> handlerUpdate(DefResourceUpdateVO data) {
        return success(superService.updateWithCacheById(data));
    }

    /**
     * 查询系统中所有的的资源，按树结构返回
     * 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     */
    @Operation(summary = "查询系统所有的资源", description = "查询系统所有的资源")
    @PostMapping("/tree")
    @WebLog("查询系统所有的菜单")
    public R<List<DefResourceResultVO>> allTree(@RequestBody DefResource query) {
        List<DefResource> list = superService.list(Wraps.lbQ(query).orderByAsc(DefResource::getSortValue));
        List<DefResourceResultVO> resultList = BeanPlusUtil.toBeanList(list, DefResourceResultVO.class);
        // 回显 @Echo 标记的字段
        echoService.action(resultList);
        return success(TreeUtil.buildTree(resultList));
    }


    @Operation(summary = "移动资源", description = "移动资源")
    @PutMapping("/moveResource")
    @WebLog("移动资源")
    public R<Boolean> moveResource(@RequestParam Long id, @RequestParam(required = false) Long parentId) {
        superService.moveResource(id, parentId);
        return success();
    }

    @Override
    public R<DefResourceResultVO> get(@PathVariable Long id) {
        return R.success(superService.getResourceById(id));
    }
}
