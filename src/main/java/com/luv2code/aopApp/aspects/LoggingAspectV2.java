package com.luv2code.aopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

    @Before("allServiceMethodsPointCut()")
    public void beforeServiceMethodCalled(JoinPoint joinPoint){
        log.info("Before advice method call, {}",joinPoint.getSignature());

    }

    @After("allServiceMethodsPointCut()")
    public void afterServiceMethodCalled(JoinPoint joinPoint){
        log.info("After advice method call, {}",joinPoint.getSignature());
    }

    @AfterReturning(value = "allServiceMethodsPointCut()",returning = "returnedObject")
    public void afterReturningServiceMethodCalled(JoinPoint joinPoint, Object returnedObject){
        log.info("After Returning advice method call, {}",joinPoint.getSignature());
        log.info("After Returning returned value, {}",returnedObject);
    }

    @AfterThrowing(value = "allServiceMethodsPointCut()")
    public void afterThrowingServiceMethodCalled(JoinPoint joinPoint){
        log.info("After Throwing advice method call, {}",joinPoint.getSignature());
    }

    @Around("allServiceMethodsPointCut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnedValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long diff = endTime - startTime;
        log.info("Time taken for {} is {}",proceedingJoinPoint.getSignature(),diff);
        return returnedValue;
    }

    @Pointcut("execution(* com.luv2code.aopApp.services.impl.*.*(..))")
    public void allServiceMethodsPointCut(){

    }
}
