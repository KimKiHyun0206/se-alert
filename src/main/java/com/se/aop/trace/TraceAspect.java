package com.se.aop.trace;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {

    @Before("@annotation(com.se.aop.trace.Trace)")
    public void doBeforeTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {} args= {}", joinPoint.getSignature(), args);
    }

    @After("@annotation(com.se.aop.trace.Trace)")
    public void doAfterTrace(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("[trace] {} args= {}", joinPoint.getSignature(), args);
    }

    //@Around("@annotation(com.se.aop.trace.Trace)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[trace] {}", joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        log.info("[trace] proceed={}", proceed);
        return proceed;
    }
}