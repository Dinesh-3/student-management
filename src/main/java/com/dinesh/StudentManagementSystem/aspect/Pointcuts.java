package com.dinesh.StudentManagementSystem.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Pointcuts {

    /**
        By this we can use these pointcuts anywhere in the project

        In order to use these pointcuts need to add fully qualified name
        like below:
             @Before("com.dinesh.StudentManagementSystem.aspect.Pointcuts.servicePointCut()")

     */

    @Pointcut("execution(* com.dinesh.StudentManagementSystem.service.*.*(..))")
    public void servicePointCut(){};

    @Pointcut("execution(* com.dinesh.StudentManagementSystem.controller.*.*(..))")
    public void controllerPointCut(){};

}
