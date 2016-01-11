/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import java.util.List;
import java.util.Set;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.Role;

/**
 * 
 * @author PlayerSun
 * @date Oct 17, 2015
 */
@DaoMapping
public interface RoleDao extends CrudDao<Role> {
    public List<Role> findShowRoles(Set<Long> roleIds);
}
