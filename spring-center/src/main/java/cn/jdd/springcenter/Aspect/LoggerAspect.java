package cn.jdd.springcenter.Aspect;

import Log.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Pointcut("execution(public * cn.jdd.springcenter.Controller.*.*(..))")
    public void loggerPointcut(){}

    @Around("loggerPointcut()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){
        LogFactory logFactory = new LogFactory(proceedingJoinPoint.getClass());
        logFactory.debugLog("pre enter" + proceedingJoinPoint.getSignature().getName());
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logFactory.debugLog(throwable.getMessage());
        }
        logFactory.debugLog("exit" + proceedingJoinPoint.getSignature().getName());
    }
}
