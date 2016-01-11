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
import com.playersun.jbf.modules.sys.dao.RoleDao;
import com.playersun.jbf.modules.sys.entity.Role;

/**
 * 
 * @author PlayerSun
 * @date Oct 17, 2015
 */
@Service
public class RoleService extends CrudService<Role> {
    private RoleDao getRoleDao() {
        return (RoleDao) crudDao;
    }
    
    public List<Role> findShowRoles(Set<Long> roleIds){
        return getRoleDao().findShowRoles( roleIds);
    }
}
