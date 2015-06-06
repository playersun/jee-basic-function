/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import java.util.Date;

import com.playersun.jbf.common.entity.BaseEntity;

/**
 * 用户类
 * 
 * @author PlayerSun
 * @date 2015年6月3日
 */
public class User extends BaseEntity<User> {
    
    private String username; //登录用户名
    private String password; //登录密码
    private int salt; //密码盐，加密密码使用
    private String name; //真实姓名
    private String workNumber; //员工工号
    private int sex; //性别。0女、1男
    private Date birthday; //生日
    private String email; //电子邮件
    private String mobile; //手机号
    private String photo; //相片
    private Date createDate; //创建日期
    private long updateBy; //被谁更新
    private Date updateDate; //更新时间
    private String remarks; //备注
    private int deleted; //是否已经删除。0表示没有，1表示删除。不删除数据。
    private int status; //用户状态。0 正常、1 禁止登录
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getSalt() {
        return salt;
    }
    
    public void setSalt(int salt) {
        this.salt = salt;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getWorkNumber() {
        return workNumber;
    }
    
    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }
    
    public int getSex() {
        return sex;
    }
    
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public long getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public int getDeleted() {
        return deleted;
    }
    
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
}
