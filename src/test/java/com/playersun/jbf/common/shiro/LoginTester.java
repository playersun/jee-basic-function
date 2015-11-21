/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.playersun.jbf.common.BaseTestWithSpring;


public class LoginTester extends BaseTestWithSpring {
    
    @Test
    public void login(){
        
        UsernamePasswordToken upToken = new UsernamePasswordToken("admin", "admin");
        
        Subject sub = SecurityUtils.getSubject();
        sub.login(upToken);
        System.out.println(sub.getPrincipal());
    }
}
