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
        
        groupRelation.setGroupId(1L);
        groupRelation.setOrganizationId(1L);
        groupRelation.setUserId(1L);
        
        groupRelation.setCreateBy(1L);
        groupRelation.setCreateDate(date);
        groupRelation.setUpdateBy(1L);
        groupRelation.setUpdateDate(date);
        groupRelation.setDeleted(false);
        
        groupRelationService.insert(groupRelation);
    }
}
