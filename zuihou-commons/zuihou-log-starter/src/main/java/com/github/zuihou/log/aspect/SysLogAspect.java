package com.github.zuihou.log.aspect;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.base.R;
import com.github.zuihou.context.BaseContextConstants;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.log.entity.OptLogDTO;
import com.github.zuihou.log.event.SysLogEvent;
import com.github.zuihou.log.util.LogUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 操作日志使用spring event异步入库
 *
 * @author zuihou
 * @date 2019-07-01 15:15
 */
@Slf4j
@Aspect
public class SysLogAspect {

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.github.zuihou.log.annotation.SysLog)")
    public void sysLogAspect() {

    }

    private OptLogDTO get() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }

    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        tryCatch((val) -> {
            // 开始时间
            OptLogDTO sysLog = get();
            sysLog.setCreateUser(BaseContextHandler.getUserId());
            sysLog.setUserName(BaseContextHandler.getName());
            String controllerDescription = "";
            Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
            if (api != null) {
                String[] tags = api.tags();
                if (tags != null && tags.length > 0) {
                    controllerDescription = tags[0];
                }
            }

            String controllerMethodDescription = LogUtil.getControllerMethodDescription(joinPoint);
            if (StrUtil.isEmpty(controllerDescription)) {
                sysLog.setDescription(controllerMethodDescription);
            } else {
                sysLog.setDescription(controllerDescription + "-" + controllerMethodDescription);
            }

            // 类名
            sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
            //获取执行的方法名
            sysLog.setActionMethod(joinPoint.getSignature().getName());


            // 参数
            Object[] args = joinPoint.getArgs();

            String strArgs = "";
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            try {
                if (!request.getContentType().contains("multipart/form-data")) {
                    strArgs = JSONObject.toJSONString(args);
                }
            } catch (Exception e) {
                try {
                    strArgs = Arrays.toString(args);
                } catch (Exception ex) {
                    log.warn("解析参数异常", ex);
                }
            }
            sysLog.setParams(getText(strArgs));

            if (request != null) {
                sysLog.setRequestIp(ServletUtil.getClientIP(request));
                sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
                sysLog.setHttpMethod(request.getMethod());
                sysLog.setUa(StrUtil.sub(request.getHeader("user-agent"), 0, 500));
                sysLog.setTenantCode(request.getHeader(BaseContextConstants.TENANT));
            }
            sysLog.setStartTime(LocalDateTime.now());

            THREAD_LOCAL.set(sysLog);
        });
    }


    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        tryCatch((aaa) -> {
            R r = Convert.convert(R.class, ret);
            OptLogDTO sysLog = get();
            if (r == null) {
                sysLog.setType("OPT");
            } else {
                if (r.getIsSuccess()) {
                    sysLog.setType("OPT");
                } else {
                    sysLog.setType("EX");
                    sysLog.setExDetail(r.getMsg());
                }
                sysLog.setResult(getText(r.toString()));
            }

            publishEvent(sysLog);
        });

    }

    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        THREAD_LOCAL.remove();
    }

    public static final int MAX_LENGTH = 65535;

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        tryCatch((aaa) -> {
            OptLogDTO sysLog = get();
            sysLog.setType("EX");

            // 异常对象
//            sysLog.setExDetail(LogUtil.getStackTrace(e));
            sysLog.setExDetail(ExceptionUtil.stacktraceToString(e, MAX_LENGTH));
            // 异常信息
//            sysLog.setExDesc(e.getMessage());
            sysLog.setExDesc(ExceptionUtil.stacktraceToString(e, MAX_LENGTH));

            publishEvent(sysLog);
        });
    }

    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }

//    @Around("@annotation(sLog)")
//    @SneakyThrows
//    public Object around(ProceedingJoinPoint point, SysLog sLog) {
//        log.info("当前线程id={}", Thread.currentThread().getId());
//
//        String strClassName = point.getTarget().getClass().getName();
//        String strMethodName = point.getSignature().getName();
//
//        log.info("[类名]:{},[方法]:{}", strClassName, strMethodName);
//        Log sysLog = Log.builder().build();
//
//        // 开始时间
//        Long startTime = Instant.now().toEpochMilli();
//        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        BaseContextHandler.getAccount();
//        sysLog.setCreateUser(BaseContextHandler.getUserId());
//        sysLog.setRequestIp(ServletUtil.getClientIP(request));
//        sysLog.setUserName(BaseContextHandler.getNickName());
//        sysLog.setDescription(LogUtil.getControllerMethodDescription(point));
//
//        // 类名
//        sysLog.setClassPath(point.getTarget().getClass().getName());
//        //获取执行的方法名
//        sysLog.setActionMethod(point.getSignature().getName());
//        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
//        sysLog.setHttpMethod(HttpMethod.get(request.getMethod()));
//        // 参数
//        Object[] args = point.getArgs();
//        sysLog.setParams(getText(JSONObject.toJSONString(args)));
//
//        sysLog.setStartTime(LocalDateTime.now());
//        sysLog.setUa(request.getHeader("user-agent"));
//
//        // 发送异步日志事件
//        Object obj = point.proceed();
//
//        R r = Convert.convert(R.class, obj);
//        if (r.getIsSuccess()) {
//            sysLog.setType(LogType.OPT);
//        } else {
//            sysLog.setType(LogType.EX);
//            sysLog.setExDetail(r.getMsg());
//        }
//        if (r != null) {
//            sysLog.setResult(getText(r.toString()));
//        }
//
//        sysLog.setFinishTime(LocalDateTime.now());
//        long endTime = Instant.now().toEpochMilli();
//        sysLog.setConsumingTime(endTime - startTime);
//
//        applicationContext.publishEvent(new SysLogEvent(sysLog));
//        return obj;
//    }


}
