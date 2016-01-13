/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.modules.sys.entity.Role;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.service.UserAuthService;
import com.playersun.jbf.modules.sys.service.UserService;


/**
 * 
 * @author PlayerSun
 * @date Jan 12, 2016
 */
public class UserAuthServiceTester extends BaseTest {
    @Autowired
    private UserAuthService userAuthService;
    
    @Autowired
    private UserService userService;
    
    
    @Test
    public void userAuth(){
        User user = userService.findById(1L);
        
        List<Role> roles = userAuthService.findRoles(user);
        
        System.out.println(roles);
    }
}
