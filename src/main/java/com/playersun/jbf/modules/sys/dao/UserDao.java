/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import com.playersun.jbf.common.persistence.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.User;

/**
 * 用户DAO接口
 * 
 * @author PlayerSun
 * @date Jun 3, 2015
 */
@DaoMapping
//标记Dao让MyBatis认识并和mapping xml对应，一定要加上。
public interface UserDao {
    
    /**
     * 根据用户ID获得用户
     * 
     * @param id
     *            数据库中的主键ID
     * @return User 用户实例
     */
    public User findById(long id);
    
    /**
     * 根据用户名获得用户
     * 
     * @param userName
     *            登录用户名
     * @return User 用户实例
     */
    public User findByUserName(String userName);
}
