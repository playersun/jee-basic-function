/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.playersun.jbf.modules.sys.entity.Role;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.entity.UserOrganizationJob;


/**
 * 分组、组织机构、用户、新增、修改
 * <p/>
 * 获取用户授权的角色及组装好的权限
 * @author PlayerSun
 * @date Nov 29, 2015
 */
@Service
public class UserAuthService {
    @Autowired
    private GroupService groupService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private JobService jobService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private PermissionService permissionService;
    
    /**
     * 获得用户角色
     * @param user
     * @return
     */
    public Set<Role> findRoles(User user) {
        if (user == null) {
            return Sets.newHashSet();
        }
        
        Set<Long[]> organizationJobIds = Sets.newHashSet();
        Set<Long> organizationIds = Sets.newHashSet();
        Set<Long> jobIds = Sets.newHashSet();
        
        Long userId = user.getId();
        
        /**
         * 列出用户的部门（Organization）和职位（Job）
         */
        for (UserOrganizationJob o : user.getOrganizationJobs()) {
            Long organizationId = o.getOrganizationId();
            Long jobId = o.getJobId();

            if (organizationId != null && jobId != null && organizationId != 0L && jobId != 0L) {
                organizationJobIds.add(new Long[]{organizationId, jobId});
            }
            organizationIds.add(organizationId);
            jobIds.add(jobId);
        }
        
        return null;
    }
    
    public Set<String> findStringRoles(User user) {
        return null;
    }
    
    public Set<String> findStringPermissions(User user) {
        return null;
    }
}
