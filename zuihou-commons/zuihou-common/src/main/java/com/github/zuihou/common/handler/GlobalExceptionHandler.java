package com.github.zuihou.common.handler;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.github.zuihou.base.Result;
import com.github.zuihou.common.excode.ExceptionCode;
import com.github.zuihou.exception.BizException;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import static com.github.zuihou.common.excode.ExceptionCode.REQUIRED_FILE_PARAM_EX;


/**
 * @author zuihou
 * @createTime 2017-12-13 17:04
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<String> baseExceptionHandler(BizException ex) {
        log.info("BizException:", ex);
        return Result.result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (message != null && !"".equals(message)) {
            if (message.contains("Could not read document:")) {
                String msg = "无法正确的解析json类型的参数：" +
                        message.substring(message.indexOf("Could not read document:") +
                                "Could not read document:".length(), message.indexOf(" at "));
                return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg);
            }
        }
        return Result.result(ExceptionCode.PARAM_EX.getCode(), "", ExceptionCode.PARAM_EX.getMsg());
    }

    @ExceptionHandler(BindException.class)
    public Result BindException(BindException eee) {
        log.error("BindException:", eee);
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = eee.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数对象[").append(oe.getObjectName()).append("]的字段[")
                        .append(oe.getField()).append("]的值[").append(oe.getRejectedValue()).append("]与实际类型不匹配.")

        );
        return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg.toString());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException:", ex);
        MethodArgumentTypeMismatchException eee = (MethodArgumentTypeMismatchException) ex;
        StringBuilder msg = new StringBuilder("参数[").append(eee.getName()).append("]的值[")
                .append(eee.getValue()).append("]与实际类型[").append(eee.getRequiredType().getName()).append("]不匹配");
        return Result.result(ExceptionCode.PARAM_EX.getCode(), null, msg.toString());
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result IllegalStateException(IllegalStateException ex) {
        log.error("IllegalStateException:", ex);
        return Result.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), null, ExceptionCode.ILLEGALA_ARGUMENT_EX.getMsg());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException:", e);
        StringBuilder msg = new StringBuilder();
        msg.append("缺少必须的[").append(e.getParameterType()).append("] 类型的参数[").append(e.getParameterName()).append("]");
        return Result.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), null, msg.toString());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException ex) {
        log.error("NullPointerException:", ex);
        return Result.result(ExceptionCode.NULL_POINT_EX.getCode(), null, ExceptionCode.NULL_POINT_EX.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException:", ex);
        return Result.result(ExceptionCode.ILLEGALA_ARGUMENT_EX.getCode(), null, ExceptionCode.ILLEGALA_ARGUMENT_EX.getMsg());
    }

    @ExceptionHandler(SQLException.class)
    public Result sQLException(SQLException ex) {
        log.error("SQLException:", ex);
        return Result.result(ExceptionCode.SQL_EX.getCode(), null, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("DataIntegrityViolationException:", ex);
        return Result.result(ExceptionCode.SQL_EX.getCode(), null, ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(PersistenceException.class)
    public Result<String> persistenceException(PersistenceException ex) {
        log.error("PersistenceException:", ex);
        return Result.result(ExceptionCode.SQL_EX.getCode(), "", ExceptionCode.SQL_EX.getMsg());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("HttpMediaTypeNotSupportedException:", e);
        MediaType contentType = e.getContentType();
        if (contentType != null) {
            StringBuilder msg = new StringBuilder();
            msg.append("请求类型(Content-Type)[").append(contentType.toString()).append("] 与实际接口的请求类型不匹配");
            return Result.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), null, msg.toString());
        }
        return Result.result(ExceptionCode.MEDIA_TYPE_EX.getCode(), null, "无效的Content-Type类型");
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public Result missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.error("MissingServletRequestPartException:", ex);
        return Result.result(REQUIRED_FILE_PARAM_EX.getCode(), null, REQUIRED_FILE_PARAM_EX.getMsg());
    }

    @ExceptionHandler(ServletException.class)
    public Result servletException(ServletException ex) {
        log.error("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        if (msg.equalsIgnoreCase(ex.getMessage())) {
            return Result.result(REQUIRED_FILE_PARAM_EX.getCode(), null, REQUIRED_FILE_PARAM_EX.getMsg());
        }
        return Result.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result multipartException(MultipartException ex) {
        log.error("MultipartException:", ex);
        return Result.result(REQUIRED_FILE_PARAM_EX.getCode(), null, REQUIRED_FILE_PARAM_EX.getMsg());
    }

    /**
     * jsr 规范中的验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return Result.result(ExceptionCode.BASE_VALID_PARAM.getCode(), "", message);
    }

    /**
     * spring 封装的参数验证异常， 在conttoller中没有写result参数时，会进入
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return Result.result(ExceptionCode.BASE_VALID_PARAM.getCode(), "", ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 其他异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<String> otherExceptionHandler(Exception ex) {
        log.error("Exception:", ex);
        return Result.result(ExceptionCode.SYSTEM_BUSY.getCode(), "", ExceptionCode.SYSTEM_BUSY.getMsg());
    }

}