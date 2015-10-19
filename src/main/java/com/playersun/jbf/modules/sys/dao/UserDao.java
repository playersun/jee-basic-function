/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.dao;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.annotation.DaoMapping;
import com.playersun.jbf.modules.sys.entity.User;

/**
 * 用户DAO接口
 * 
 * @author PlayerSun
 * @date Jun 3, 2015
 */
@DaoMapping
//标记Dao让MyBatis认识并和mapping xml对应，一定要加上。
public interface UserDao extends CrudDao<User> {
    
    /**
     * 根据用户名获得用户
     * 
     * @param userName
     *            登录用户名
     * @return User 用户实例
     */
    public User findByUserName(String userName);
    
    /**
     * 根据邮箱获得用户
     * 
     * @param email
     *            邮箱
     * @return
     */
    public User findByEmail(String email);
    
    /**
     * 根据手机号获得用户
     * 
     * @param mobileNum
     *            手机号
     * @return
     */
    public User findByMobilePhoneNumber(String mobileNum);
    
}
