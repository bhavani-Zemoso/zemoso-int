package com.springboot.application.OnlineBookStore.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {

    @Pointcut("execution(* com.springboot.application.OnlineBookStore.dao.*.*(..))")
    public void forDaoPackage(){}

    @Pointcut("execution(* com.springboot.application.OnlineBookStore.dto.*.*(..))")
    public void forDtoPackage() {}

    @Pointcut("execution(* com.springboot.application.OnlineBookStore.service.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("execution(* com.springboot.application.OnlineBookStore.controller.*.*(..))")
    public void forControllerPackage() {}

    @Pointcut("execution(* com.springboot.application.OnlineBookStore.entity.*.*(..))")
    public void forEntityPackage() {}
}
