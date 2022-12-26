package top.tangyh.lamp.authority.controller.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.tangyh.basic.annotation.log.SysLog;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.base.R;
import top.tangyh.basic.base.controller.DeleteController;
import top.tangyh.basic.base.controller.PoiController;
import top.tangyh.basic.base.controller.SuperSimpleController;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.lamp.authority.dto.common.OptLogResult;
import top.tangyh.lamp.authority.entity.common.OptLog;
import top.tangyh.lamp.authority.service.common.OptLogService;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author zuihou
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Tag(name = "系统日志")
@PreAuth(replace = "authority:optLog:")
public class OptLogController extends SuperSimpleController<OptLogService, OptLog>
        implements DeleteController<OptLog, Long>, PoiController<OptLog, OptLog> {

    @Operation(summary = "分页列表查询")
    @PostMapping(value = "/page")
    @PreAuth("hasAnyPermission('{}view')")
    public R<IPage<OptLog>> page(@RequestBody @Validated PageParams<OptLog> params) {
        IPage<OptLog> page = query(params);
        return R.success(page);
    }

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Parameters({
            @Parameter(name = "id", description = "主键", schema = @Schema(type = "long"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "单体查询", description = "单体查询")
    @GetMapping("/get")
    @PreAuth("hasAnyPermission('{}view')")
    public R<OptLogResult> get(@RequestParam Long id) {
        return success(baseService.getOptLogResultById(id));
    }


    @Operation(summary = "清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
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
        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
