package top.tangyh.lamp.msg.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.msg.entity.ExtendInterfaceLogging;
import top.tangyh.lamp.msg.service.ExtendInterfaceLoggingService;
import top.tangyh.lamp.msg.vo.query.ExtendInterfaceLoggingPageQuery;
import top.tangyh.lamp.msg.vo.result.ExtendInterfaceLoggingResultVO;
import top.tangyh.lamp.msg.vo.save.ExtendInterfaceLoggingSaveVO;
import top.tangyh.lamp.msg.vo.update.ExtendInterfaceLoggingUpdateVO;

/**
 * <p>
 * 前端控制器
 * 接口执行日志记录
 * </p>
 *
 * @author zuihou
 * @date 2022-07-09 23:58:59
 * @create [2022-07-09 23:58:59] [zuihou] [代码生成器生成]
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/extendInterfaceLogging")
@Tag(name = "接口执行日志记录")
public class ExtendInterfaceLoggingController extends SuperController<ExtendInterfaceLoggingService, Long, ExtendInterfaceLogging, ExtendInterfaceLoggingSaveVO,
        ExtendInterfaceLoggingUpdateVO, ExtendInterfaceLoggingPageQuery, ExtendInterfaceLoggingResultVO> {
    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

}


