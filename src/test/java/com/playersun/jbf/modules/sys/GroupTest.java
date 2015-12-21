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
import com.playersun.jbf.modules.sys.entity.Group;
import com.playersun.jbf.modules.sys.entity.GroupType;
import com.playersun.jbf.modules.sys.service.GroupService;


/**
 * @author PlayerSun
 * @date Nov 15, 2015
 */
public class GroupTest extends BaseTest {
    
    @Autowired
    private GroupService groupService;
    
    @Test
    public void addGroup(){
        Group group = new Group();
        
        Date date = new Date();
        
        group.setName("管理员");
        group.setType(GroupType.user);
        group.setIsShow(true);
        group.setDefaultGroup(true);
        
        group.setCreateBy(1L);
        group.setCreateDate(date);
        group.setUpdateBy(1L);
        group.setUpdateDate(date);
        group.setDeleted(false);
        
        groupService.insert(group);
    }
    
    @Test
    public void getGroupById(){
        Group group = groupService.findById(1L);
        System.out.println(group);
    }
    
    @Test
    public void updateGroup() {
        Group group = groupService.findById(2L);
        group.setName("模块管理员");
        System.out.println(groupService.update(group));
    }
    
    @Test
    public void getGroupList(){
        List<Group> list = groupService.findList((Pageable)null);
        for (Group group : list) {
            System.out.println(group);
        }
    }
    
    @Test
    public void delete(){
        Group group = groupService.findById(1L);
        System.out.println(groupService.delete(group));
    }
    
    @Test
    public void deleteById(){
        System.out.println(groupService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Group> list = groupService.findList((Pageable)null);
        
        System.out.println(groupService.deleteBatch(list));
    }
}
