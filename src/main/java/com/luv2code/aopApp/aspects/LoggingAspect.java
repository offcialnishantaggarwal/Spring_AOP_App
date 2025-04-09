package com.luv2code.aopApp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
@Component
@Slf4j
public class LoggingAspect {

//    @Before("execution(* orderPackage(..))")
//    @Before("execution(* com.luv2code.aopApp.services.impl.ShipmentServiceImpl.orderPackage(..))")
//    @Before("execution(* com.luv2code.aopApp.services.impl.*.orderPackage(..))")
    @Before("execution(* com.luv2code.aopApp.services.impl.*.*(..))")
    public void beforeOrderPackage(JoinPoint joinPoint){
        log.info("Before called from Logging Aspect kind, {}",joinPoint.getKind());
        log.info("Before called from Logging Aspect signature, {}",joinPoint.getSignature());
    }

//    @Before("within(com.luv2code.aopApp.services.impl.*)")
    @Before("within(com.luv2code.aopApp..*)")
    public void beforeServiceImplCalls(){
        log.info("Service impl calls");
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionalAnnotationCalls(){
        log.info("Before Transactional Annotations Calls");
    }

    @Before("@annotation(com.luv2code.aopApp.aspects.MyLoggingAnnotation)")
    public void beforeMyLoggingAnnotationCalls(){
        log.info("Before MyLogging Annotations Calls");
    }

    @Before("myLoggingAndAopMethodsPointCut()")
    public void beforeMyLoggingAndAopMethodsPointCut(){
        log.info("Before MyLogging Annotations Calls And Aop Methods");
    }

    @After("myLoggingAndAopMethodsPointCut()")
    public void afterMyLoggingAndAopMethodsPointCut(){
        log.info("After MyLogging Annotations Calls And Aop Methods");
    }

    @Pointcut("@annotation(com.luv2code.aopApp.aspects.MyLoggingAnnotation) && within(com.luv2code.aopApp..*)")
    public void myLoggingAndAopMethodsPointCut(){

    }
}
