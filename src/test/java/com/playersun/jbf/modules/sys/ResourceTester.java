/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.common.persistence.pagination.PageMybatis;
import com.playersun.jbf.common.persistence.pagination.PageRequest;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.modules.sys.entity.Resource;
import com.playersun.jbf.modules.sys.service.ResourceService;

public class ResourceTester extends BaseTest {
    
    @Autowired
    private ResourceService resourceService;
    
    @Test
    public void findPage() {
        
        //Resource resourc = resourceService.findById(1L);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentID", 2);
        
        Resource r = new Resource();
        r.setParentID(1L);
        
        Pageable pageable = new PageRequest(3, 3, map);
        PageMybatis<Resource> p = resourceService.findList(pageable);
        System.out.print(p);
    }
}
