package com.github.zuihou.log.util;

import cn.hutool.core.util.ReflectUtil;
import com.github.zuihou.log.annotation.SysLog;
import com.github.zuihou.utils.StrPool;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.Arrays;

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
    public static String getDescribe(JoinPoint point) {
        SysLog annotation = getTargetAnno(point);
        if (annotation == null) {
            return StrPool.EMPTY;
        }
        return annotation.value();
    }

    public static String getDescribe(SysLog annotation) {
        if (annotation == null) {
            return StrPool.EMPTY;
        }
        return annotation.value();
    }

    /**
     * 先从子类上获取 @SysLog ，没有则从父类获取
     *
     * @param point
     * @return
     */
    public static SysLog getTargetAnno(JoinPoint point) {
        try {
            // 获取连接点签名的方法名
            String methodName = point.getSignature().getName();
            //获取连接点参数
            Object[] args = point.getArgs();
            Method method = ReflectUtil.getMethodOfObj(point.getTarget(), methodName, args);
            Method parentMethod = null;
            Class[] classes = Arrays.stream(args).map((arg) -> arg.getClass()).toArray(Class[]::new);
            parentMethod = ReflectUtil.getMethod(point.getTarget().getClass().getSuperclass(), methodName, classes);
            SysLog annotation = null;
            if (method != null) {
                annotation = method.getAnnotation(SysLog.class);
            }

            if (annotation == null && parentMethod != null) {
                annotation = parentMethod.getAnnotation(SysLog.class);
            }
            return annotation;
        } catch (Exception e) {
            return null;
        }
    }

}
