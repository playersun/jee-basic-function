/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.Role;

@DaoMapping
public interface RoleDao extends CrudDao<Role> {
    
}