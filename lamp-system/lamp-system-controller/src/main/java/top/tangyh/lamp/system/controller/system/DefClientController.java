package top.tangyh.lamp.system.controller.system;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.system.entity.system.DefClient;
import top.tangyh.lamp.system.service.system.DefClientService;
import top.tangyh.lamp.system.vo.query.system.DefClientPageQuery;
import top.tangyh.lamp.system.vo.result.system.DefClientResultVO;
import top.tangyh.lamp.system.vo.save.system.DefClientSaveVO;
import top.tangyh.lamp.system.vo.update.system.DefClientUpdateVO;


/**
 * <p>
 * 前端控制器
 * 客户端
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/defClient")
@Tag(name = "客户端")
public class DefClientController extends SuperController<DefClientService, Long, DefClient, DefClientSaveVO, DefClientUpdateVO, DefClientPageQuery, DefClientResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}
