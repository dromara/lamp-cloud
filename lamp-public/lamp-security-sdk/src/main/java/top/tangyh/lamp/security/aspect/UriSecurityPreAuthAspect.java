package top.tangyh.lamp.security.aspect;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.tangyh.basic.annotation.security.PreAuth;
import top.tangyh.basic.context.ContextConstants;
import top.tangyh.basic.exception.ForbiddenException;
import top.tangyh.basic.exception.code.ExceptionCode;
import top.tangyh.basic.utils.StrPool;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * AOP 鉴权
 *
 * @author zuihou
 * @date 2020年03月29日21:17:49
 */
@Aspect
@Slf4j
public class UriSecurityPreAuthAspect implements ApplicationContextAware {

    /**
     * 表达式处理
     */
    private static final ExpressionParser SP_EL_PARSER = new SpelExpressionParser();
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();
    private final VerifyAuthFunction verifyAuthFunction;
    private ApplicationContext ac;

    public UriSecurityPreAuthAspect(VerifyAuthFunction verifyAuthFunction) {
        this.verifyAuthFunction = verifyAuthFunction;
    }

    /**
     * 切 方法 和 类上的 @PreAuth 注解
     *
     * @param point 切点
     * @return Object
     * @throws Throwable 没有权限的异常
     */
    @Around("execution(public * top.tangyh.basic.base.controller.*.*(..)) || " +
            "@annotation(top.tangyh.basic.annotation.security.PreAuth) || " +
            "@within(top.tangyh.basic.annotation.security.PreAuth)"
    )
    public Object preAuth(ProceedingJoinPoint point) throws Throwable {
        handleAuth(point);
        return point.proceed();
    }

    /**
     * 处理权限
     *
     * @param point 切点
     */
    private void handleAuth(ProceedingJoinPoint point) {
        Environment env = ac.getEnvironment();
        Boolean property = env.getProperty("lamp.security.enabled", Boolean.class, false);
        if (!property) {
            log.debug("全局校验权限已经关闭");
            return;
        }
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        // 读取权限注解，优先方法上，没有则读取类
        PreAuth preAuth = null;
        if (point.getSignature() instanceof MethodSignature) {
            method = ((MethodSignature) point.getSignature()).getMethod();
            if (method != null) {
                preAuth = method.getAnnotation(PreAuth.class);
            }
        }
        String methodName = method != null ? method.getName() : "";

        // 读取目标类上的权限注解
        PreAuth targetClass = point.getTarget().getClass().getAnnotation(PreAuth.class);
        //方法和类上 均无注解
        if (preAuth == null && targetClass == null) {
            log.debug("执行方法[{}]无需校验权限", methodName);
            return;
        }

        // 方法上禁用
        if (preAuth != null && !preAuth.enabled()) {
            log.debug("执行方法[{}]无需校验权限", methodName);
            return;
        }

        // 类上禁用
        if (targetClass != null && !targetClass.enabled()) {
            log.debug("执行方法[{}]无需校验权限", methodName);
            return;
        }

        // feign 远程调用
        if (isFeign(methodName)) {
            return;
        }

        String condition = getCondition(preAuth, targetClass);
        if (StrUtil.isBlank(condition)) {
            return;
        }
        Boolean hasPermit = invokePermit(point, method, condition);
        if (!hasPermit) {
            throw ForbiddenException.wrap(ExceptionCode.FORBIDDEN.build("执行方法[%s]需要[%s]权限", methodName, condition));
        }
    }

    private boolean isFeign(String methodName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                if (StrPool.TRUE.equals(request.getHeader(ContextConstants.FEIGN))) {
                    log.debug("内部调用方法[{}]无需校验权限", methodName);
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private Boolean invokePermit(ProceedingJoinPoint point, Method method, String condition) {
        StandardEvaluationContext context = new StandardEvaluationContext(verifyAuthFunction);
        Expression expression = SP_EL_PARSER.parseExpression(condition);
        // 方法参数值
        Object[] args = point.getArgs();

        context.setBeanResolver(new BeanFactoryResolver(ac));
        for (int i = 0; i < args.length; i++) {
            MethodParameter mp = new SynthesizingMethodParameter(method, i);
            mp.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
            context.setVariable(mp.getParameterName(), args[i]);
        }
        return expression.getValue(context, Boolean.class);
    }

    @Nullable
    private String getCondition(PreAuth preAuth, PreAuth targetClass) {
        String condition = preAuth == null ? targetClass.value() : preAuth.value();
        if (condition.contains(StrPool.BRACE)) {
            if (targetClass != null && StrUtil.isNotBlank(targetClass.replace())) {
                condition = StrUtil.format(condition, targetClass.replace());
            } else {
                // 子类类上边没有 @PreAuth 注解，证明该方法不需要简要权限
                return null;
            }
        }
        return condition;
    }


    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

}
