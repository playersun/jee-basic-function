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
import com.playersun.jbf.modules.sys.entity.Organization;
import com.playersun.jbf.modules.sys.entity.OrganizationType;
import com.playersun.jbf.modules.sys.service.OrganizationService;

/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class OrganizationTester extends BaseTest {
    
    @Autowired
    private OrganizationService organizationService;
    
    @Test
    public void addOrganization(){
        Organization organization = new Organization();
        Date date = new Date();
        
        organization.setName("数码事业部");
        organization.setType(OrganizationType.branch_office);
        organization.setParentId(1L);
        organization.setParentIds("0/1/");
        organization.setWeight(1);
        organization.setIsShow(Boolean.TRUE);
        
        organization.setCreateBy(1L);
        organization.setCreateDate(date);
        organization.setUpdateBy(1L);
        organization.setUpdateDate(date);
        organization.setDeleted(false);
        
        organizationService.insert(organization);
    }
    
    @Test
    public void getOrganizationById(){
        Organization organization = organizationService.findById(1L);
        System.out.println(organization);
    }
    
    @Test
    public void getOrganizationList(){
        List<Organization> list = organizationService.findList(null);
        for (Organization organization : list) {
            System.out.println(organization);
        }
    }
    
    @Test
    public void delete(){
        Organization organization = organizationService.findById(2L);
        System.out.println(organizationService.delete(organization));
    }
    
    @Test
    public void deleteById(){
        System.out.println(organizationService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Organization> list = organizationService.findList(null);
        
        System.out.println(organizationService.deleteBatch(list));
    }
    
    
    @Test
    public void updateOrganization() {
        Organization organization = organizationService.findById(1L);
        organization.setRemarks("test update once again");
        System.out.println(organizationService.update(organization));
    }
}
