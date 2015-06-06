/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.service.UserService;

public class UserTester extends BaseTest {
    
    @Autowired
    private UserService userService;
    
    @Test(expected = NullPointerException.class)
    public void findByUserName() {
        String un = "admin";
        
        User u1 = userService.findById(0);
        
        User u2 = userService.findByUserName(un);
        
        System.out.println(u1.toString());
        System.out.println(u2.toString());
    }
}
