package top.tangyh.lamp.system.controller.tenant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.system.entity.tenant.DefDatasourceConfig;
import top.tangyh.lamp.system.service.tenant.DefDatasourceConfigService;
import top.tangyh.lamp.system.vo.query.tenant.DefDatasourceConfigPageQuery;
import top.tangyh.lamp.system.vo.result.tenant.DefDatasourceConfigResultVO;
import top.tangyh.lamp.system.vo.save.tenant.DefDatasourceConfigSaveVO;
import top.tangyh.lamp.system.vo.update.tenant.DefDatasourceConfigUpdateVO;


/**
 * <p>
 * 前端控制器
 * 数据源
 * </p>
 *
 * @author zuihou
 * @date 2021-09-13
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defDatasourceConfig")
@Tag(name = "数据源")
public class DefDatasourceConfigController extends SuperController<DefDatasourceConfigService, Long, DefDatasourceConfig, DefDatasourceConfigSaveVO, DefDatasourceConfigUpdateVO, DefDatasourceConfigPageQuery, DefDatasourceConfigResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Operation(summary = "测试数据库链接")
    @PostMapping("/testConnect")
    public R<Boolean> testConnect(@RequestParam Long id) {
        return R.success(superService.testConnection(id));
    }
}
