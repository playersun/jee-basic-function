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
import com.playersun.jbf.modules.sys.entity.Permission;
import com.playersun.jbf.modules.sys.service.PermissionService;

/**
 * 
 * @author PlayerSun
 * @date Nov 14, 2015
 */
public class PermissionTester extends BaseTest {
    
    @Autowired
    private PermissionService persistenceService;
    
    @Test
    public void addPermission(){
        Permission permission = new Permission();
        
        permission.setName("所有");
        permission.setPermission("*");
        permission.setDescription("所有数据操作的权限");
        permission.setIsShow(true);
        
        permission.setCreateBy(1L);
        permission.setUpdateBy(1L);
        Date date = new Date();
        permission.setCreateDate(date);
        permission.setUpdateDate(date);
        permission.setDeleted(false);
        
        persistenceService.insert(permission);
    }
    
    @Test
    public void getPermission(){
        Permission permission = persistenceService.findById(1L);
        System.out.println(permission);
    }
}
