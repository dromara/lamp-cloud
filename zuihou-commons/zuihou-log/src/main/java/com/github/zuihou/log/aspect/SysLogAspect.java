package com.github.zuihou.log.aspect;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.github.zuihou.authority.entity.common.OptLog;
import com.github.zuihou.authority.enumeration.common.LogType;
import com.github.zuihou.base.R;
import com.github.zuihou.common.enums.HttpMethod;
import com.github.zuihou.context.BaseContextHandler;
import com.github.zuihou.log.event.SysLogEvent;
import com.github.zuihou.log.util.LogUtil;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    private OptLog sysLog = new OptLog();
    private long beginTime = 0;

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.github.zuihou.log.annotation.SysLog)")
    public void sysLogAspect() {

    }

    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        log.info("当前线程id={}", Thread.currentThread().getId());
        log.info("joinPoint={}", joinPoint);

        // 开始时间
        beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        BaseContextHandler.getAccount();
        sysLog.setCreateUser(BaseContextHandler.getUserId());
        sysLog.setRequestIp(ServletUtil.getClientIP(request));
        sysLog.setUserName(BaseContextHandler.getNickName());
        sysLog.setDescription(LogUtil.getControllerMethodDescription(joinPoint));

        // 类名
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        //获取执行的方法名
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
        sysLog.setHttpMethod(HttpMethod.get(request.getMethod()));
        // 参数
        Object[] args = joinPoint.getArgs();
        sysLog.setParams(getText(JSONObject.toJSONString(args)));

        sysLog.setStartTime(LocalDateTime.now());
        sysLog.setUa(request.getHeader("user-agent"));
    }


    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        log.info("当前线程id={}", Thread.currentThread().getId());
        log.info("ret={}", ret);
        R r = Convert.convert(R.class, ret);
        if (r.getIsSuccess()) {
            sysLog.setType(LogType.OPT);
        } else {
            sysLog.setType(LogType.EX);
            sysLog.setExDetail(r.getMsg());
        }
        if (ret != null) {
            sysLog.setResult(getText(r.toString()));
        }

        publishEvent();
    }

    private void publishEvent() {
        sysLog.setFinishTime(LocalDateTime.now());
        long endTime = Instant.now().toEpochMilli();
        sysLog.setConsumingTime(endTime - beginTime);

        applicationContext.publishEvent(new SysLogEvent(sysLog));
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        log.info("当前线程id={}", Thread.currentThread().getId());
        log.info("e={}", e);

        sysLog.setType(LogType.EX);

        // 异常对象
        sysLog.setExDetail(LogUtil.getStackTrace(e));
        // 异常信息
        sysLog.setExDesc(e.getMessage());

        publishEvent();
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


    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StringUtils.substring(val, 0, 65535);
    }

}
