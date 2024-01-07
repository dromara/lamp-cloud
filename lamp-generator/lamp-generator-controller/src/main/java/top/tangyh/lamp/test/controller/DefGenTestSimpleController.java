package top.tangyh.lamp.test.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.test.entity.DefGenTestSimple;
import top.tangyh.lamp.test.service.DefGenTestSimpleService;
import top.tangyh.lamp.test.vo.query.DefGenTestSimplePageQuery;
import top.tangyh.lamp.test.vo.result.DefGenTestSimpleResultVO;
import top.tangyh.lamp.test.vo.save.DefGenTestSimpleSaveVO;
import top.tangyh.lamp.test.vo.update.DefGenTestSimpleUpdateVO;

/**
 * <p>
 * 前端控制器
 * 测试单表
 * </p>
 *
 * @author zuihou
 * @date 2022-04-15 15:36:45
 * @create [2022-04-15 15:36:45] [zuihou] [代码生成器生成]
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defGenTestSimple")
@Tag(name = "测试单表")
public class DefGenTestSimpleController extends SuperController<DefGenTestSimpleService, Long, DefGenTestSimple, DefGenTestSimpleSaveVO,
        DefGenTestSimpleUpdateVO, DefGenTestSimplePageQuery, DefGenTestSimpleResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


