package com.github.zuihou.order.controller.log;

import com.github.zuihou.base.R;
import com.github.zuihou.base.request.PageParams;
import com.github.zuihou.exception.BizException;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.order.entity.Order;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zuihou
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/log")
@AllArgsConstructor
@Api(value = "log", tags = "log")
@SysLog(enabled = true)
public class LogTestController {

    /**
     * 动态修改日志级别：
     * curl -X POST http://127.0.0.1:8769/actuator/loggers/com.github.zuihou.order.controller.log
     * <p>
     * Content-Type: application/json
     * <p>
     * <p>
     * {
     * "configuredLevel":"DEBUG"
     * }
     * <p>
     * <p>
     * 实时查看日志级别：
     * http://127.0.0.1:8769/actuator/loggers
     *
     * @return
     * @throws BizException
     */

    @PostMapping(value = "/log")
    @SysLog(enabled = false)
    public R log() throws BizException {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return R.success();
    }

    @PostMapping(value = "/responseFalse")
    @SysLog(value = "'分页列表查询:第' + #params?.current + '页, 显示' + #params?.size + '行'", response = false)
    public R responseFalse(@RequestBody @Validated PageParams<Order> params) {
        log.info("data={}", params);
        return R.success(params);
    }

    @PostMapping(value = "/requestFalse")
    @SysLog(value = "'保存订单:订单编码' + #data?.code + ', 订单Id：' + #data?.name", request = false, requestByError = false)
    public R requestFalse(@RequestBody @Validated Order data) {
        log.info("data={}", data);
//        int a = 1 / 0;
        return R.success(data);
    }


    @PostMapping(value = "/query")
    @SysLog(value = "'查询订单:订单id' + #id + ', 订单名称：' + #name")
    public R query(@RequestParam Long id, @RequestParam String name) {
        log.info("id={}, name={}", id, name);

        return R.success(id + name);
    }


}
