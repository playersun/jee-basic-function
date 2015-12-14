/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import java.util.List;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.Group;


/**
 * @author PlayerSun
 * @date Nov 15, 2015
 */
@DaoMapping
public interface GroupDao extends CrudDao<Group> {
    List<Long> findDefaultGroupIds();
}
