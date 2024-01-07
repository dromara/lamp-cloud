package top.tangyh.lamp.msg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.msg.entity.DefInterfaceProperty;
import top.tangyh.lamp.msg.service.DefInterfacePropertyService;
import top.tangyh.lamp.msg.vo.query.DefInterfacePropertyPageQuery;
import top.tangyh.lamp.msg.vo.result.DefInterfacePropertyResultVO;
import top.tangyh.lamp.msg.vo.save.DefInterfacePropertyBatchSaveVO;
import top.tangyh.lamp.msg.vo.save.DefInterfacePropertySaveVO;
import top.tangyh.lamp.msg.vo.update.DefInterfacePropertyUpdateVO;

/**
 * <p>
 * 前端控制器
 * 接口属性
 * </p>
 *
 * @author zuihou
 * @date 2022-07-04 15:51:37
 * @create [2022-07-04 15:51:37] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/defInterfaceProperty")
@Tag(name = "接口属性")
public class DefInterfacePropertyController extends SuperController<DefInterfacePropertyService, Long, DefInterfaceProperty, DefInterfacePropertySaveVO,
        DefInterfacePropertyUpdateVO, DefInterfacePropertyPageQuery, DefInterfacePropertyResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }


    /**
     * 新增
     *
     * @param saveVO 保存参数
     * @return 实体
     */
    @Operation(summary = "保存")
    @PostMapping("/batchSave")
    @WebLog(value = "保存", request = false)
    public R<Boolean> batchSave(@RequestBody @Validated DefInterfacePropertyBatchSaveVO saveVO) {
        return R.success(superService.batchSave(saveVO));

    }

}


