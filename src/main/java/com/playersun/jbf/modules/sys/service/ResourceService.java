/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playersun.jbf.common.persistence.pagination.Page;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.modules.sys.dao.ResourceDao;
import com.playersun.jbf.modules.sys.entity.Resource;

@Service
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;
    
    public Resource findById(Long id) {
        return resourceDao.findById(id);
    }
    
    public void save(Resource resource){
        resourceDao.insert(resource);
    }
    
    public Page<Resource> findPage(Pageable pageable) {
        resourceDao.findList(pageable);
        return null;
    }
}
