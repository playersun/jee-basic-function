/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.Auth;
import com.sun.tools.javac.util.List;

/**
 * @author PlayerSun
 * @date Nov 14, 2015
 */
@DaoMapping
public interface AuthDao extends CrudDao<Auth> {
    
    public List<String> findRoleIds(@Param("userId") Long userId, @Param("groupIds") Set<Long> groupIds,
            @Param("organizationIds") Set<Long> organizationIds, @Param("jobIds") Set<Long> jobIds,
            @Param("organizationJobIds") Set<Long[]> organizationJobIds);
}
