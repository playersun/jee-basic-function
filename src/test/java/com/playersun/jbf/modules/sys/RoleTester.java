/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;
import java.util.List;

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
        
        role.setName("shaoqing.mei");
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
    public void getRoleById(){
        Role role = roleService.findById(1L);
        System.out.println(role);
    }
    
    @Test
    public void getRoleList(){
        List<Role> list = roleService.findList(null);
        for(Role role : list){
            System.out.println(role);
        }
    }
    
    @Test
    public void delete(){
        Role role = roleService.findById(3L);
        System.out.println(roleService.delete(role));
    }
    
    @Test
    public void deleteById(){
        System.out.println(roleService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Role> list = roleService.findList(null);
        
        System.out.println(roleService.deleteBatch(list));
    }
    
    
    @Test
    public void updateRole() {
        Role role = roleService.findById(1L);
        role.setRemarks("test update once again");
        System.out.println(roleService.update(role));
    }
}
