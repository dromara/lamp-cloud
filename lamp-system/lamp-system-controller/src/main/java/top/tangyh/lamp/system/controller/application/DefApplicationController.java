package top.tangyh.lamp.system.controller.application;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.system.entity.application.DefApplication;
import top.tangyh.lamp.system.service.application.DefApplicationService;
import top.tangyh.lamp.system.vo.query.application.DefApplicationPageQuery;
import top.tangyh.lamp.system.vo.result.application.ApplicationResourceResultVO;
import top.tangyh.lamp.system.vo.result.application.DefApplicationResultVO;
import top.tangyh.lamp.system.vo.save.application.DefApplicationSaveVO;
import top.tangyh.lamp.system.vo.update.application.DefApplicationUpdateVO;

import java.util.List;

import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static top.tangyh.lamp.common.constant.SwaggerConstants.DATA_TYPE_STRING;


/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2021-09-15
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defApplication")
@Tag(name = "应用")
public class DefApplicationController extends SuperCacheController<DefApplicationService, Long, DefApplication, DefApplicationSaveVO, DefApplicationUpdateVO, DefApplicationPageQuery, DefApplicationResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = DATA_TYPE_LONG), in = ParameterIn.QUERY),
            @Parameter(name = "name", description = "名称", schema = @Schema(type = DATA_TYPE_STRING), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测应用名是否重复", description = "检测应用名是否重复")
    @GetMapping("/check")
    @WebLog(value = "检测应用名是否重复")
    public R<Boolean> check(@RequestParam(required = false) Long id, @NotEmpty(message = "应用名不能为空") @RequestParam String name) {
        return success(superService.check(id, name));
    }

    @Operation(summary = "查询应用资源列表", description = "查询应用资源列表")
    @GetMapping("/findApplicationResourceList")
    @WebLog(value = "查询应用资源列表")
    public R<List<ApplicationResourceResultVO>> findApplicationResourceList() {
        return success(superService.findApplicationResourceList());
    }

    @Operation(summary = "查询可用的应用资源列表")
    @GetMapping("/findAvailableApplicationResourceList")
    @WebLog(value = "查询可用的应用资源列表")
    public R<List<ApplicationResourceResultVO>> findAvailableApplicationResourceList() {
        return success(superService.findAvailableApplicationResourceList());
    }

    @Operation(summary = "查询可用的应用数据权限列表")
    @GetMapping("/findAvailableApplicationDataScopeList")
    @WebLog(value = "查询可用的应用数据权限列表")
    public R<List<ApplicationResourceResultVO>> findAvailableApplicationDataScopeList() {
        return success(superService.findAvailableApplicationDataScopeList());
    }

}
