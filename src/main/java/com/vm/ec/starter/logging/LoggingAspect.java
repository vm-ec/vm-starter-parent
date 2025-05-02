package com.vm.ec.starter.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {}

    @Before("restControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering: {} with args = {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "restControllerMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Exiting: {} with result = {}", joinPoint.getSignature(), result);
    }
}
