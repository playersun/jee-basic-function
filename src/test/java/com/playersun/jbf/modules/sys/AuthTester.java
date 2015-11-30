/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.modules.sys.entity.Auth;
import com.playersun.jbf.modules.sys.entity.AuthType;
import com.playersun.jbf.modules.sys.service.AuthService;


/**
 * @author PlayerSun
 * @date Nov 14, 2015
 */
public class AuthTester extends BaseTest {
    
    @Autowired
    private AuthService authService;
    
    @Test
    public void addAuth(){
        Auth auth = new Auth();
        
        Date date = new Date();
        
        Set<Long> set = Sets.newHashSet();
        set.add(1L);
        set.add(2L);
        
        auth.setJobId(1L);
        auth.setGroupId(1L);
        auth.setUserId(1L);
        auth.setRoleIds(set);
        auth.setOrganizationId(1L);
        auth.setType(AuthType.user);
        
        auth.setCreateBy(1L);
        auth.setCreateDate(date);
        auth.setUpdateBy(1L);
        auth.setUpdateDate(date);
        auth.setDeleted(false);
        
        authService.insert(auth);
    }
    
    @Test
    public void getAuthById(){
        Auth auth = authService.findById(1L);
        System.out.println(auth);
    }
    
    @Test
    public void getAuthList(){
        List<Auth> list = authService.findList(null);
        for (Auth auth : list) {
            System.out.println(auth);
        }
    }
    
    @Test
    public void delete(){
        Auth auth = authService.findById(1L);
        System.out.println(authService.delete(auth));
    }
    
    @Test
    public void deleteById(){
        System.out.println(authService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Auth> list = authService.findList(null);
        
        System.out.println(authService.deleteBatch(list));
    }
    
    
    @Test
    public void updateAuth() {
        Auth auth = authService.findById(1L);
        auth.setRemarks("test update once again");
        System.out.println(authService.update(auth));
    }
}
