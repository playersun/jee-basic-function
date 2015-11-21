/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import java.util.Set;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 用户，部门，职位 角色关系表
 * @author PlayerSun
 * @date Nov 14, 2015
 */
public class Auth extends DataEntity<Permission> {
    
    private Long organizationId;
    
    private Long jobId;
    
    private Long userId;
    
    private Long groupId;
    
    private Set<Long> roleIds;
    
    private AuthType type;

    
    public Long getOrganizationId() {
        return organizationId;
    }

    
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    
    public Long getJobId() {
        return jobId;
    }

    
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    
    public Long getUserId() {
        return userId;
    }

    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    public Long getGroupId() {
        return groupId;
    }

    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    
    public Set<Long> getRoleIds() {
        return roleIds;
    }

    
    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
    
    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Auth [organizationId=" + organizationId + ", jobId=" + jobId +
               ", userId=" + userId + ", groupId=" + groupId + ", roleIds=" +
               roleIds + ", type=" + type + ", createBy=" + createBy +
               ", createDate=" + createDate + ", updateBy=" + updateBy +
               ", updateDate=" + updateDate + ", remarks=" + remarks +
               ", deleted=" + deleted + ", id=" + id + "]";
    }

    
}
