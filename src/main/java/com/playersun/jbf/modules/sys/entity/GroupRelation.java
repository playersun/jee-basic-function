/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.DataEntity;


/**
 * @author PlayerSun
 * @date Nov 15, 2015
 */
public class GroupRelation extends DataEntity<GroupRelation> {
    
    private Long groupId;
    
    private Long organizationId;
    
    private Long userId;
    
    private Long startUserId;
    private Long endUserId;
    
    public Long getGroupId() {
        return groupId;
    }
    
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    
    public Long getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getStartUserId() {
        return startUserId;
    }
    
    public void setStartUserId(Long startUserId) {
        this.startUserId = startUserId;
    }
    
    public Long getEndUserId() {
        return endUserId;
    }
    
    public void setEndUserId(Long endUserId) {
        this.endUserId = endUserId;
    }

    @Override
    public String toString() {
        return "GroupRelation [groupId=" + groupId + ", organizationId=" +
               organizationId + ", userId=" + userId + ", startUserId=" +
               startUserId + ", endUserId=" + endUserId + ", createBy=" +
               createBy + ", createDate=" + createDate + ", updateBy=" +
               updateBy + ", updateDate=" + updateDate + ", remarks=" +
               remarks + ", deleted=" + deleted + ", id=" + id + "]";
    }
    
    
}
