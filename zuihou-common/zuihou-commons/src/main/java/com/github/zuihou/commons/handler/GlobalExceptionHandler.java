package com.github.zuihou.commons.handler;


import com.github.zuihou.base.Result;
import com.github.zuihou.commons.exception.core.ExceptionCode;
import com.github.zuihou.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zuihou
 * @createTime 2017-12-13 17:04
 */
@ControllerAdvice(value = {
        "com.github.zuihou.auth",
        "com.github.zuihou.gateway",
        "com.github.zuihou.admin.impl",
        "com.github.zuihou.open.impl",
})
@ResponseBody  //返回结果为json
public class GlobalExceptionHandler {
    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public Result baseExceptionHandler(BizException ex) {
        log.error("BizException:", ex);
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result otherExceptionHandler(Exception ex) {
        log.error("Exception:", ex);
        return new Result(ExceptionCode.SYSTEM_BUSY.getCode(), null, ex.getMessage());
    }
}