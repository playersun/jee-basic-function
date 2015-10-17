/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAop {
    
    /**
     * 切入点
     */
    @Pointcut(
            value = "target(com.playersun.jbf.modules.sys.service.RoleService)")
    private void userServicePointcut() {
    }
    
    @Pointcut(value = "execution(* com.playersun.jbf..service..*Service.*(..))")
    private void cachePutPointcut() {
    }
    
    @After(value = "cachePutPointcut()")
    public void showLog(JoinPoint jp) {
        System.out.println("-----------" + jp.getTarget().getClass().getName());
    }
}
