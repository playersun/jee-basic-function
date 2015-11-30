/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.UserOrganizationJob;

/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
@DaoMapping
public interface UserOrganizationJobDao extends CrudDao<UserOrganizationJob> {
    
}
