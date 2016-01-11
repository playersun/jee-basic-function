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
import com.playersun.jbf.common.service.CrudService;
import com.playersun.jbf.modules.sys.dao.AuthDao;
import com.playersun.jbf.modules.sys.entity.Auth;


/**
 * @author PlayerSun
 * @date Nov 14, 2015
 */
@Service
public class AuthService extends CrudService<Auth> {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;
    
    private AuthDao getAuthDao() {
        return (AuthDao) crudDao;
    }
    
    
    /**
     * 根据用户信息获取 角色
     * 1.1、用户  根据用户绝对匹配
     * 1.2、组织机构 根据组织机构绝对匹配 此处需要注意 祖先需要自己获取
     * 1.3、工作职务 根据工作职务绝对匹配 此处需要注意 祖先需要自己获取
     * 1.4、组织机构和工作职务  根据组织机构和工作职务绝对匹配 此处不匹配祖先
     * 1.5、组  根据组绝对匹配
     *
     * @param userId             必须有
     * @param groupIds           可选
     * @param organizationIds    可选
     * @param jobIds             可选
     * @param organizationJobIds 可选
     * @return
     */
    public Set<Long> findRoleIds(Long userId, Set<Long> groupIds, Set<Long> organizationIds, Set<Long> jobIds, Set<Long[]> organizationJobIds) {
        Set<Long> ids = Sets.newHashSet();
        getAuthDao().findRoleIds(userId, groupIds, organizationIds, jobIds, organizationJobIds);
        return ids;
    }
}
