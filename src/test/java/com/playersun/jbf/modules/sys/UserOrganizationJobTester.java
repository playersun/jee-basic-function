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
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.modules.sys.entity.UserOrganizationJob;
import com.playersun.jbf.modules.sys.service.UserOrganizationJobService;


/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class UserOrganizationJobTester extends BaseTest {
    @Autowired
    private UserOrganizationJobService userOrganizationJobService;
    
    @Test
    public void getList(){
        List<UserOrganizationJob> list = userOrganizationJobService.findList((Pageable)null);
        
        for(UserOrganizationJob uoj : list){
            System.out.println(uoj);
        }
    }
}
