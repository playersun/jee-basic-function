/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.BaseEntity;


/**
 * 
 * @author PlayerSun
 * @date Nov 15, 2015
 */
public class Job extends BaseEntity<Job> {
    private String name;
    
    private Long parentId;
    
    private String parentIds;
    
    private Integer weight;
    
    private String icon;
    
    private Boolean isShow;
}
