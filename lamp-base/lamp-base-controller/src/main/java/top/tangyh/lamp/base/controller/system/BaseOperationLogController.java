package top.tangyh.lamp.base.controller.system;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.WebLog;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.interfaces.echo.EchoService;
import top.tangyh.lamp.base.entity.system.BaseOperationLog;
import top.tangyh.lamp.base.service.system.BaseOperationLogService;
import top.tangyh.lamp.base.vo.query.system.BaseOperationLogPageQuery;
import top.tangyh.lamp.base.vo.result.system.BaseOperationLogResultVO;
import top.tangyh.lamp.base.vo.save.system.BaseOperationLogSaveVO;
import top.tangyh.lamp.base.vo.update.system.BaseOperationLogUpdateVO;

import java.time.LocalDateTime;


/**
 * <p>
 * 前端控制器
 * 操作日志
 * </p>
 *
 * @author zuihou
 * @date 2021-11-08
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/baseOperationLog")
@Tag(name = "操作日志")
public class BaseOperationLogController extends SuperController<BaseOperationLogService, Long, BaseOperationLog, BaseOperationLogSaveVO, BaseOperationLogUpdateVO, BaseOperationLogPageQuery, BaseOperationLogResultVO> {

    private final EchoService echoService;

    @Override
    public EchoService getEchoService() {
        return echoService;
    }

    @Override
    public R<BaseOperationLogResultVO> getDetail(@RequestParam("id") Long id) {
        return R.success(superService.getDetail(id));
    }

    @Operation(summary = "清空日志")
    @DeleteMapping("clear")
    @WebLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }
        return success(superService.clearLog(clearBeforeTime, clearBeforeNum));
    }
}
