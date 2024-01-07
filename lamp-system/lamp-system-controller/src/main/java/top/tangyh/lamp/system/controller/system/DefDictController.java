package top.tangyh.lamp.system.controller.system;

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
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.database.mybatis.conditions.query.QueryWrap;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.common.constant.DefValConstants;
import top.tangyh.lamp.system.entity.system.DefDict;
import top.tangyh.lamp.system.service.system.DefDictService;
import top.tangyh.lamp.system.vo.query.system.DefDictPageQuery;
import top.tangyh.lamp.system.vo.result.system.DefDictResultVO;
import top.tangyh.lamp.system.vo.save.system.DefDictSaveVO;
import top.tangyh.lamp.system.vo.update.system.DefDictUpdateVO;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 字典
 * </p>
 *
 * @author zuihou
 * @date 2021-10-04
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defDict")
@Tag(name = "字典")
public class DefDictController extends SuperController<DefDictService, Long, DefDict, DefDictSaveVO, DefDictUpdateVO, DefDictPageQuery, DefDictResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public QueryWrap<DefDict> handlerWrapper(DefDict model, PageParams<DefDictPageQuery> params) {
        QueryWrap<DefDict> wrap = super.handlerWrapper(null, params);
        LbQueryWrap<DefDict> wrapper = wrap.lambda();
        wrapper.eq(DefDict::getParentId, DefValConstants.PARENT_ID)
                .like(DefDict::getKey, model.getKey())
                .like(DefDict::getName, model.getName())
                .in(DefDict::getClassify, params.getModel().getClassify())
                .in(DefDict::getState, params.getModel().getState());
        return wrap;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return R.success(superService.deleteDict(ids));
    }


    @Parameters({
            @Parameter(name = "id", description = "ID", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
            @Parameter(name = "key", description = "字典标识", schema = @Schema(type = "string"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "检测字典标识是否可用", description = "检测字典标识是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String key, @RequestParam(required = false) Long id) {
        return success(superService.checkByKey(key, id));
    }

}
