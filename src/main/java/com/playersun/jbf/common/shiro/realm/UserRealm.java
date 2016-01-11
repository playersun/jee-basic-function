/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.utils.LogUtils;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.exception.UserBlockedException;
import com.playersun.jbf.modules.sys.exception.UserException;
import com.playersun.jbf.modules.sys.exception.UserNotExistsException;
import com.playersun.jbf.modules.sys.exception.UserPasswordNotMatchException;
import com.playersun.jbf.modules.sys.exception.UserPasswordRetryLimitExceedException;
import com.playersun.jbf.modules.sys.service.UserAuthService;
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
    
    @Autowired
    private UserAuthService userAuthService;
    
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        User user = userService.findByUserName(username);
        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        
        String username = upToken.getUsername().trim();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }

        User user = null;
        try {
            user = userService.login(username, password);
        } catch (UserNotExistsException e) {
            throw new UnknownAccountException(e.getMessage(), e);
        } catch (UserPasswordNotMatchException e) {
            throw new AuthenticationException(e.getMessage(), e);
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new ExcessiveAttemptsException(e.getMessage(), e);
        } catch (UserBlockedException e) {
            throw new LockedAccountException(e.getMessage(), e);
        } catch (Exception e) {
            LogUtils.logError("login error", e);
            throw new AuthenticationException(new UserException("user.unknown.error", null));
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), password.toCharArray(), getName());
        return info;
    }
    
}
