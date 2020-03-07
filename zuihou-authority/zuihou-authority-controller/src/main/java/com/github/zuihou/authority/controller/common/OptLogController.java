package com.github.zuihou.authority.controller.common;


import com.github.zuihou.authority.dto.common.OptLogUpdateDTO;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.service.common.OptLogService;
import com.github.zuihou.base.R;
import com.github.zuihou.base.controller.SuperController;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.utils.BeanPlusUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "OptLog", tags = "系统日志")
public class OptLogController extends SuperController<OptLogService, Long, OptLog, OptLog, OptLogDTO, OptLogUpdateDTO> {


    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @Override
    @ApiOperation(value = "保存系统日志", notes = "保存系统日志不为空的字段")
    public R<OptLog> save(@RequestBody OptLogDTO data) {
        baseService.save(data);
        return success(BeanPlusUtil.toBean(data, OptLog.class));
    }

    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = 0;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            clearBeforeNum = 1000;        // 清理一千条以前日志数据
        } else if (type == 6) {
            clearBeforeNum = 10000;        // 清理一万条以前日志数据
        } else if (type == 7) {
            clearBeforeNum = 30000;        // 清理三万条以前日志数据
        } else if (type == 8) {
            clearBeforeNum = 100000;    // 清理十万条以前日志数据
        } else if (type == 9) {
            clearBeforeNum = null;            // 清理所有日志数据
        } else {
            return R.validFail("参数错误");
        }

        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }

}
