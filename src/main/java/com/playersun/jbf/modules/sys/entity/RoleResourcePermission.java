/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import java.util.Set;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 角色，资源，权限表
 * @author PlayerSun
 * @date Oct 14, 2015
 */
public class RoleResourcePermission extends DataEntity<RoleResourcePermission> {
    
    /**
     * 角色
     */
    private Role role;
    
    /**
     * 资源id
     */
    private Long resourceId;
    
    /**
     * 权限id列表 数据库通过字符串存储 逗号分隔
     */
    private Set<Long> permissionIds;
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public Long getResourceId() {
        return resourceId;
    }
    
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
    
    public Set<Long> getPermissionIds() {
        return permissionIds;
    }
    
    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Override
    public String toString() {
        return "RoleResourcePermission [role=" + role.getId() + ", resourceId=" +
               resourceId + ", permissionIds=" + permissionIds + ", createBy=" +
               createBy + ", createDate=" + createDate + ", updateBy=" +
               updateBy + ", updateDate=" + updateDate + ", remarks=" +
               remarks + ", deleted=" + deleted + ", id=" + id + "]";
    }
    
}
