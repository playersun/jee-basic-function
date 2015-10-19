/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import java.util.List;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 角色表
 * 
 * @author PlayerSun
 * @date Oct 9, 2015
 */
public class Role extends DataEntity<Role> {
    
    /**
     * 前端显示名称
     */
    private String name;
    
    /**
     * 系统中验证时使用的角色标识
     */
    private String role;
    
    /**
     * 详细描述
     */
    private String description;
    
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    private Boolean isShow;
    
    /**
     * 用户 组织机构 工作职务关联表
     */
    private List<RoleResourcePermission> resourcePermissions;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getIsShow() {
        return isShow;
    }
    
    public void setIsShow(Boolean show) {
        this.isShow = show;
    }

    public List<RoleResourcePermission> getResourcePermissions() {
        return resourcePermissions;
    }

    
    public void setResourcePermissions(
            List<RoleResourcePermission> resourcePermissions) {
        this.resourcePermissions = resourcePermissions;
    }

    @Override
    public String toString() {
        return "Role [name=" + name + ", role=" + role + ", description=" +
               description + ", isShow=" + isShow + ", resourcePermissions=" +
               resourcePermissions + ", createBy=" + createBy +
               ", createDate=" + createDate + ", updateBy=" + updateBy +
               ", updateDate=" + updateDate + ", remarks=" + remarks +
               ", deleted=" + deleted + ", id=" + id + "]";
    }


}
