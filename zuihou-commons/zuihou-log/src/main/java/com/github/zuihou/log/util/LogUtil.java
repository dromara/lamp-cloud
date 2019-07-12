package com.github.zuihou.log.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import com.github.zuihou.log.annotation.SysLog;

import org.aspectj.lang.JoinPoint;

/**
 * 日志工具类
 *
 * @author zuihou
 * @date 2019-04-28 11:30
 */
public class LogUtil {

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getControllerMethodDescription(JoinPoint point) {
        try {
            // 获取连接点目标类名
            String targetName = point.getTarget().getClass().getName();
            // 获取连接点签名的方法名
            String methodName = point.getSignature().getName();
            //获取连接点参数
            Object[] args = point.getArgs();
            //根据连接点类的名字获取指定类
            Class targetClass = Class.forName(targetName);
            //获取类里面的方法
            Method[] methods = targetClass.getMethods();
            String description = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == args.length) {
                        description = method.getAnnotation(SysLog.class).value();
                        break;
                    }
                }
            }
            return description;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
