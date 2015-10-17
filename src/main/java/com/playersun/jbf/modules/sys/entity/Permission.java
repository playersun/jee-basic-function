/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 权限类
 * 
 * @author PlayerSun
 * @date Oct 9, 2015
 */
public class Permission extends DataEntity<Permission> {
    
    /**
     * 前端显示名称
     */
    private String name;
    
    /**
     * 系统中验证时使用的权限标识
     */
    private String permission;
    
    /**
     * 详细描述
     */
    private String description;
    
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    private Boolean show;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getShow() {
        return show;
    }
    
    public void setShow(Boolean show) {
        this.show = show;
    }
    
}
