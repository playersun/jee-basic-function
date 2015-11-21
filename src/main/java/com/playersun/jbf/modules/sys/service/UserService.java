/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.playersun.jbf.common.service.CrudService;
import com.playersun.jbf.common.utils.UserLogUtils;
import com.playersun.jbf.modules.sys.dao.UserDao;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.entity.UserStatus;
import com.playersun.jbf.modules.sys.exception.UserBlockedException;
import com.playersun.jbf.modules.sys.exception.UserNotExistsException;
import com.playersun.jbf.modules.sys.exception.UserPasswordNotMatchException;

/**
 * 用户Service
 * 
 * @author PlayerSun
 * @date Jun 3, 2015
 */
@Service
public class UserService extends CrudService<User> {
    
    @Autowired
    private PasswordService passwordService;
    
    @Autowired
    private UserDao getUserDao() {
        return (UserDao) crudDao;
    }
    
    public User findByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return getUserDao().findByUserName(username);
    }
    
    public User findByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return null;
        }
        return getUserDao().findByEmail(email);
    }
    
    public User findByMobilePhoneNumber(String mobilePhoneNumber) {
        if (StringUtils.isEmpty(mobilePhoneNumber)) {
            return null;
        }
        return getUserDao().findByMobilePhoneNumber(mobilePhoneNumber);
    }
    
    public User findByUserName(String userName) {
        return getUserDao().findByUserName(userName);
    }
    
    public User login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            UserLogUtils.log(username, "loginError", "username is empty");
            throw new UserNotExistsException();
        }
        
        //密码如果不在指定范围内 肯定错误
        if (password.length() < User.PASSWORD_MIN_LENGTH ||
            password.length() > User.PASSWORD_MAX_LENGTH) {
            UserLogUtils.log(username, "loginError",
                    "password length error! password is between {} and {}",
                    User.PASSWORD_MIN_LENGTH, User.PASSWORD_MAX_LENGTH);
            throw new UserPasswordNotMatchException();
        }
        
        User user = null;
        //此处需要走代理对象，目的是能走缓存切面
//        UserService proxyUserService = (UserService) AopContext.currentProxy();
        if (maybeUsername(username)) {
            user = getUserDao().findByUserName(username);
        }
        
        if (user == null && maybeEmail(username)) {
            user = getUserDao().findByEmail(username);
        }
        
        if (user == null && maybeMobilePhoneNumber(username)) {
            user = getUserDao().findByMobilePhoneNumber(username);
        }
        
        if (user == null || Boolean.TRUE.equals(user.getDeleted())) {
            
            throw new UserNotExistsException();
        }
        
        passwordService.validate(user, password);
        
        if (user.getStatus() == UserStatus.blocked) {
            UserLogUtils.log(username, "loginError", "user is blocked!");
            throw new UserBlockedException("user is blocked!");
        }
        
        return user;
    }
    
    private boolean maybeUsername(String username) {
        if (!username.matches(User.USERNAME_PATTERN)) {
            return false;
        }
        //如果用户名不在指定范围内也是错误的
        if (username.length() < User.USERNAME_MIN_LENGTH ||
            username.length() > User.USERNAME_MAX_LENGTH) {
            return false;
        }
        
        return true;
    }
    
    private boolean maybeEmail(String username) {
        if (!username.matches(User.EMAIL_PATTERN)) {
            return false;
        }
        return true;
    }
    
    private boolean maybeMobilePhoneNumber(String username) {
        if (!username.matches(User.MOBILE_PHONE_NUMBER_PATTERN)) {
            return false;
        }
        return true;
    }
    
}
