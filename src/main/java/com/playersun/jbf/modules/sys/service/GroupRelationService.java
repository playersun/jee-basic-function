/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.playersun.jbf.common.service.CrudService;
import com.playersun.jbf.modules.sys.dao.GroupRelationDao;
import com.playersun.jbf.modules.sys.entity.GroupRelation;

/**
 * 
 * @author PlayerSun
 * @date Nov 15, 2015
 */
@Service
public class GroupRelationService extends CrudService<GroupRelation> {
    
    private GroupRelationDao getGroupRelationRepository() {
        return (GroupRelationDao) crudDao;
    }
    
    public List<Long> findGroupIds(Long userId, Set<Long> organizationIds) {
        
        return getGroupRelationRepository().findGroupIds(userId, organizationIds);
        
    }
}
