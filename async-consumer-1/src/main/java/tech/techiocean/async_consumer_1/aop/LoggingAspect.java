package tech.techiocean.async_consumer_1.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Around("execution(* tech.techiocean.async_consumer_1.controller.*.*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering method: {}", joinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();
        log.info("start time: {}", startTime);
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("end time: {}", endTime);
        log.info("Execution time: {} ms", endTime - startTime);
        log.info("Exiting method: {}", joinPoint.getSignature().getName());
        return result;
    }
}
