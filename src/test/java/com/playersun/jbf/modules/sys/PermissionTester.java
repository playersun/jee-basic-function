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
import com.playersun.jbf.common.persistence.pagination.Pageable;
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
    public void getPermissionById(){
        Permission permission = persistenceService.findById(7L);
        System.out.println(permission);
    }
    
    @Test
    public void getPermissionList(){
        List<Permission> list = persistenceService.findList((Pageable)null);
        for (Permission permission : list) {
            System.out.println(permission);
        }
    }
    
    @Test
    public void delete(){
        Permission permission = persistenceService.findById(8L);
        System.out.println(persistenceService.delete(permission));
    }
    
    @Test
    public void deleteById(){
        System.out.println(persistenceService.deleteById(9L));
    }
    
    @Test
    public void deleteBatch(){
        List<Permission> list = persistenceService.findList((Pageable)null);
        
        System.out.println(persistenceService.deleteBatch(list));
    }
    
    
    @Test
    public void updatePermission() {
        Permission permission = persistenceService.findById(1L);
        permission.setRemarks("test update once again");
        System.out.println(persistenceService.update(permission));
    }
}
