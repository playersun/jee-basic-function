/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.service.UserService;

/**
 * 用户认证授权类
 * 
 * @author PlayerSun
 * @date Aug 30, 2015
 */
public class UserRealm extends AuthorizingRealm {
    
    @Autowired
    private UserService userService;
    
    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findByUserName(username);
        
        return null;
    }
    
    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken Token = (UsernamePasswordToken) token;
        return null;
    }
    
}
