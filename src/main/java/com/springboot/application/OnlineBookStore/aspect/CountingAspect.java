package com.springboot.application.OnlineBookStore.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CountingAspect {

    private static Logger logger = LoggerFactory.getLogger(CountingAspect.class);

    long daoCalls = 0, dtoCalls = 0, serviceCalls = 0, controllerCalls = 0, entityCalls = 0;

    @Before("com.springboot.application.OnlineBookStore.aspect.AOPExpressions.forDaoPackage()")
    public void countCallsToDao(JoinPoint joinPoint)
    {
        daoCalls++;
        logger.info("Call has been made to " + (MethodSignature) joinPoint.getSignature());
        logger.info("Total calls made to dao = " + daoCalls);
        System.out.println("In aspect countCallsToDao");
    }

    @Before("com.springboot.application.OnlineBookStore.aspect.AOPExpressions.forDtoPackage()")
    public void countCallsToDto(JoinPoint joinPoint)
    {
        dtoCalls++;
        logger.info("Call has been made to " + (MethodSignature) joinPoint.getSignature());
        logger.info("Total calls made to dto = " + dtoCalls);
    }

    @Before("com.springboot.application.OnlineBookStore.aspect.AOPExpressions.forServicePackage()")
    public void countCallsToService(JoinPoint joinPoint)
    {
        serviceCalls++;
        logger.info("Call has been made to " + (MethodSignature) joinPoint.getSignature());
        logger.info("Total calls made to service = " + serviceCalls);
    }

    @Before("com.springboot.application.OnlineBookStore.aspect.AOPExpressions.forControllerPackage()")
    public void countCallsToController(JoinPoint joinPoint)
    {
        controllerCalls++;
        logger.info("Call has been made to " + (MethodSignature) joinPoint.getSignature());
        logger.info("Total calls made to controller = " + controllerCalls);
    }

    @Before("com.springboot.application.OnlineBookStore.aspect.AOPExpressions.forEntityPackage()")
    public void countCallsToEntity(JoinPoint joinPoint)
    {
        entityCalls++;
        logger.info("Call has been made to " + (MethodSignature) joinPoint.getSignature());
        logger.info("Total calls made to dao = " + entityCalls);
    }

}
