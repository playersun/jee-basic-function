/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import java.util.List;

import com.playersun.jbf.common.entity.BaseEntity;


public class RoleResourcePermission extends BaseEntity<RoleResourcePermission> {
    /**
     * 角色
     */
    private Role role;
    
    /**
     * 资源id
     */
    private Long resourceId;
    
    /**
     * 权限id列表
     * 数据库通过字符串存储 逗号分隔
     
    private List<Long> permissionIds;*/
}
