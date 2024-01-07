package top.tangyh.lamp.base.controller.user;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.database.mybatis.conditions.query.QueryWrap;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.base.entity.user.BasePosition;
import top.tangyh.lamp.base.service.user.BasePositionService;
import top.tangyh.lamp.base.vo.query.user.BasePositionPageQuery;
import top.tangyh.lamp.base.vo.result.user.BasePositionResultVO;
import top.tangyh.lamp.base.vo.save.user.BasePositionSaveVO;
import top.tangyh.lamp.base.vo.update.user.BasePositionUpdateVO;


/**
 * <p>
 * 前端控制器
 * 岗位
 * </p>
 *
 * @author zuihou
 * @date 2021-10-18
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basePosition")
@Tag(name = "岗位")
public class BasePositionController extends SuperController<BasePositionService, Long, BasePosition, BasePositionSaveVO, BasePositionUpdateVO, BasePositionPageQuery, BasePositionResultVO> {

    private final EchoService echoService;

    @Override
    public QueryWrap<BasePosition> handlerWrapper(BasePosition model, PageParams<BasePositionPageQuery> params) {
        QueryWrap<BasePosition> wrap = super.handlerWrapper(model, params);
        wrap.lambda().in(BasePosition::getOrgId, params.getModel().getOrgIdList());
        return wrap;
    }

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    @Parameters({
            @Parameter(name = "name", description = "name", required = true, schema = @Schema(type = "string"), in = ParameterIn.QUERY),
            @Parameter(name = "orgId", description = "orgId", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String name, @RequestParam Long orgId, @RequestParam(required = false) Long id) {
        return success(superService.check(name, orgId, id));
    }
}
