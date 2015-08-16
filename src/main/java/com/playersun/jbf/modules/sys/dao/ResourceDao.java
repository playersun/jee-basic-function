/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import java.util.List;
import java.util.Map;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.annotation.DaoMapping;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.modules.sys.entity.Resource;

/**
 * 资源dao接口
 * @author PlayerSun
 * @date Aug 9, 2015
 */
@DaoMapping
public interface ResourceDao extends CrudDao<Resource> {
    List<Resource> findList (Pageable pageable);
}