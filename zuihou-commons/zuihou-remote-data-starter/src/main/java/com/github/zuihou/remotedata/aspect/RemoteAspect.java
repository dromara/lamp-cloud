package com.github.zuihou.remotedata.aspect;

import com.github.zuihou.remotedata.annonation.RemoteResult;
import com.github.zuihou.remotedata.core.InjectionCore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP
 *
 * @author zuihou
 * @create 2020年01月19日09:27:41
 */
@Aspect
@AllArgsConstructor
@Slf4j
public class RemoteAspect {
    //    private RemoteCore remoteCore;
    private InjectionCore injectionCore;


    @Pointcut("@annotation(com.github.zuihou.remotedata.annonation.RemoteResult)")
    public void methodPointcut() {
    }


    @Around("methodPointcut()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint pjp, RemoteResult anno) throws Throwable {
        try {
            return injectionCore.injection(pjp, anno);
        } catch (Exception e) {
            log.error("AOP拦截@RemoteResult出错", e);
            return pjp.proceed();
        }
    }
}
