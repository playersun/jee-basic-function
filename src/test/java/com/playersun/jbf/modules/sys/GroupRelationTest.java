/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;
import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.modules.sys.entity.GroupRelation;
import com.playersun.jbf.modules.sys.service.GroupRelationService;


/**
 * 
 * @author PlayerSun
 * @date Nov 15, 2015
 */
public class GroupRelationTest extends BaseTest {
    
    @Autowired
    private GroupRelationService groupRelationService;
    
    @Test
    public void addGroupRelation(){
        GroupRelation groupRelation = new GroupRelation();
        
        Date date = new Date();
        
        groupRelation.setGroupId(2L);
        groupRelation.setOrganizationId(2L);
        groupRelation.setUserId(2L);
        
        groupRelation.setCreateBy(2L);
        groupRelation.setCreateDate(date);
        groupRelation.setUpdateBy(2L);
        groupRelation.setUpdateDate(date);
        groupRelation.setDeleted(false);
        
        groupRelationService.insert(groupRelation);
    }
    
    @Test
    public void getGroupRelationById(){
        GroupRelation groupRelation = groupRelationService.findById(1L);
        System.out.println(groupRelation);
    }
    
    @Test
    public void getGroupRelationList(){
        List<GroupRelation> list = groupRelationService.findList((Pageable)null);
        for (GroupRelation groupRelation : list) {
            System.out.println(groupRelation);
        }
    }
    
    @Test
    public void findGroupIds(){
        Set<Long> set = Sets.newHashSet();
        set.add(1L);
        set.add(2L);
        Set<Long> groupIds = groupRelationService.findGroupIds(1L, set);
        System.out.println(groupIds);
    }
    
    @Test
    public void delete(){
        GroupRelation groupRelation = groupRelationService.findById(1L);
        System.out.println(groupRelationService.delete(groupRelation));
    }
    
    @Test
    public void deleteById(){
        System.out.println(groupRelationService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<GroupRelation> list = groupRelationService.findList((Pageable)null);
        
        System.out.println(groupRelationService.deleteBatch(list));
    }
    
    
    @Test
    public void updateAuth() {
        GroupRelation groupRelation = groupRelationService.findById(2L);
        groupRelation.setRemarks("test update once again");
        System.out.println(groupRelationService.update(groupRelation));
    }
}
