/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.playersun.jbf.common.utils.UserLogUtils;
import com.playersun.jbf.common.utils.security.Md5Utils;
import com.playersun.jbf.modules.sys.entity.User;
import com.playersun.jbf.modules.sys.exception.UserPasswordNotMatchException;
import com.playersun.jbf.modules.sys.exception.UserPasswordRetryLimitExceedException;

/**
 * 密码服务类
 * @author PlayerSun
 * @date Oct 5, 2015
 */
@Service
public class PasswordService {
    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount = 10;
    
    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    
    @Autowired
    private CacheManager ehcacheManager;

    private Cache loginRecordCache;
    
    @PostConstruct
    public void init() {
        loginRecordCache = ehcacheManager.getCache("loginRecordCache");
    }
    
    public void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }
    
    public int getMaxRetryCoun() {
        return this.maxRetryCount;
    }
    
    public void validate(User user, String password) {
        String username = user.getUsername();

        int retryCount = 0;

        Element cacheElement = loginRecordCache.get(username);
        if (cacheElement != null) {
            retryCount = (Integer) cacheElement.getObjectValue();
            if (retryCount >= maxRetryCount) {
                UserLogUtils.log(
                        username,
                        "passwordError",
                        "password error, retry limit exceed! password: {},max retry count {}",
                        password, maxRetryCount);
                throw new UserPasswordRetryLimitExceedException(maxRetryCount);
            }
        }

        if (!matches(user, password)) {
            loginRecordCache.put(new Element(username, ++retryCount));
            UserLogUtils.log(
                    username,
                    "passwordError",
                    "password error! password: {} retry count: {}",
                    password, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(User user, String newPassword) {
//        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, user.getSalt()));
        return user.getPassword().equals(newPassword);
    }
    
    public String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }
}
