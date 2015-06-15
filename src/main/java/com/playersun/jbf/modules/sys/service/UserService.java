/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playersun.jbf.modules.sys.dao.UserDao;
import com.playersun.jbf.modules.sys.entity.User;

/**
 * 用户Service
 * 
 * @author PlayerSun
 * @date Jun 3, 2015
 */
@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;
    
    public User findById(Long id){
        return userDao.findById(id);
    }
    
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
