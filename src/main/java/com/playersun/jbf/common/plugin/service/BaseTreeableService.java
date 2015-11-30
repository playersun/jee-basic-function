/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.plugin.service;

import com.playersun.jbf.common.entity.DataEntity;
import com.playersun.jbf.common.plugin.entity.Treeable;
import com.playersun.jbf.common.service.CrudService;



/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class BaseTreeableService<T extends DataEntity<T> & Treeable> extends CrudService<T> {
    
}
