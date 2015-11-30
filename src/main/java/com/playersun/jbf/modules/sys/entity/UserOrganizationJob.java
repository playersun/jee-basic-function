/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class UserOrganizationJob extends DataEntity<UserOrganizationJob> {
    
    private User user;
    
    private Long organizationId;
    
    private Long jobId;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
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

    @Override
    public String toString() {
        return "UserOrganizationJob [user=" + user + ", organizationId=" +
               organizationId + ", jobId=" + jobId + ", createBy=" + createBy +
               ", createDate=" + createDate + ", updateBy=" + updateBy +
               ", updateDate=" + updateDate + ", remarks=" + remarks +
               ", deleted=" + deleted + ", id=" + id + "]";
    }
}
