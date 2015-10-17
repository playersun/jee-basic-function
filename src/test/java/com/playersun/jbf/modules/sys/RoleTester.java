/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.modules.sys.entity.Role;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.service.RoleService;
import com.playersun.jbf.modules.sys.service.UserService;

public class RoleTester extends BaseTest {
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void addRole() {
        User user = userService.findById(1L);
        
        System.out.println(user);
        
        Role role = new Role();
        Date date = new Date();
        
        role.setName("超级管理员");
        role.setRole("admin");
        role.setDescription("拥有所有权限");
        role.setIsShow(true);
        role.setCreateBy(user.getId());
        role.setCreateDate(date);
        role.setUpdateBy(user.getId());
        role.setUpdateDate(date);
        role.setDeleted(false);
        
        int i = roleService.insert(role);
        
        System.out.println(i);
    }
    
    @Test
    public void getRole(){
        Role role = roleService.findById(13L);
        System.out.println(role);
    }
}
