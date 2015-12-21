/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.SearchRequest;
import com.playersun.jbf.common.persistence.search.Searchable;
import com.playersun.jbf.modules.sys.entity.Resource;
import com.playersun.jbf.modules.sys.service.ResourceService;

/**
 * 
 * @author PlayerSun
 * @date Nov 14, 2015
 */
public class ResourceTester extends BaseTest {
    
    @Autowired
    private ResourceService resourceService;
    
    @Test
    public void addResource(){
        Resource resource = new Resource();
        
        Date date = new Date();

        
        resource.setName("我是资源");
        resource.setParentId(1L);
        resource.setParentIds("0/1/");
        resource.setIdentification("imResource");
        resource.setUrl("/admin/sys/imResource");
        resource.setWeight(1);
        
        resource.setCreateBy(1L);
        resource.setCreateDate(date);
        resource.setUpdateBy(1L);
        resource.setUpdateDate(date);
        resource.setDeleted(false);
        
        resourceService.insert(resource);
    }
    
    @Test
    public void getResourceById(){
        Resource resource = resourceService.findById(1L);
        System.out.println(resource);
    }
    
    @Test
    public void getResourceList(){
        List<Resource> list = resourceService.findList((Pageable)null);
        for (Resource resource : list) {
            System.out.println(resource);
        }
    }
    
    @Test
    public void delete(){
        Resource resource = resourceService.findById(1L);
        System.out.println(resourceService.delete(resource));
    }
    
    @Test
    public void deleteById(){
        System.out.println(resourceService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Resource> list = resourceService.findList((Pageable)null);
        
        System.out.println(resourceService.deleteBatch(list));
    }
    
    
    @Test
    public void updateResource() {
        Resource resource = resourceService.findById(1L);
        resource.setRemarks("test update once again");
        System.out.println(resourceService.update(resource));
    }
    
    @Test
    public void findResourceWithSearchable(){
        Searchable searchable = new SearchRequest();
        resourceService.findList(searchable);
    }
    
    @Test
    public void findPage() {
        
        //Resource resourc = resourceService.findById(1L);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentID", 2);
        
        Resource r = new Resource();
        r.setParentId(1L);
        
        /*List<SortField> list = new ArrayList<SortField>();
        list.add(new SortField("id", SortDirection.DESC));
        
        Pageable pageable = new PageRequest(6, 3, map, list);
        PageMybatis<Resource> p = resourceService.findList(pageable);
        System.out.print(p);*/
    }
}
