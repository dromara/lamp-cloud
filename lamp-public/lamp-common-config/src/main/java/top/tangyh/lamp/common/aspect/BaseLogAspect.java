package top.tangyh.lamp.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author tangyh
 * @version v1.0
 * @date 2021/9/4 8:34 下午
 * @create [2021/9/4 8:34 下午 ] [tangyh] [初始创建]
 */
@Slf4j
public abstract class BaseLogAspect {

    protected String getParamTypes(ProceedingJoinPoint joinPoint) {
        if (joinPoint.getSignature() instanceof MethodSignature ms) {
            Class<?>[] parameterTypes = ms.getParameterTypes();
            if (parameterTypes == null) {
                return "";
            } else {
                StringBuilder sb = new StringBuilder();
                for (Class<?> cls : parameterTypes) {
                    if (sb.length() > 0) {
                        sb.append(", ").append(cls.getSimpleName());
                    } else {
                        sb.append(cls.getSimpleName());
                    }
                }
                return sb.toString();
            }
        }
        return "";
    }


    protected void outArgsLog(ProceedingJoinPoint joinPoint, String logTraceId, String types, Object[] args, boolean flag) {
        log.info(">>>> [traceId:{}] {}.{}({}) start...", logTraceId, joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(), types);
        if (flag) {
            log.info(">>>> [traceId:{}] args={}", logTraceId, args);
        }
    }

    protected void outResultLog(ProceedingJoinPoint joinPoint, String logTraceId, String types, long start, Object retVal, boolean flag) {
        log.info("<<<< [traceId:{}] {}.{}({}) end... {} ms", logTraceId, joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(), types, System.currentTimeMillis() - start);
        if (flag) {
            log.info("<<<< [traceId:{}] result={}", logTraceId, retVal);
        }
    }
}
