/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.DataEntity;


/**
 * @author PlayerSun
 * @date Nov 15, 2015
 */
public class Group extends DataEntity<Group> {
    
    private String name;
    
    private GroupType type;
    
    private Boolean defaultGroup;
    
    private Boolean isShow;

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public GroupType getType() {
        return type;
    }

    
    public void setType(GroupType type) {
        this.type = type;
    }

    
    public Boolean getDefaultGroup() {
        return defaultGroup;
    }

    
    public void setDefaultGroup(Boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    
    public Boolean getIsShow() {
        return isShow;
    }

    
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }


    @Override
    public String toString() {
        return "Group [name=" + name + ", type=" + type + ", defaultGroup=" +
               defaultGroup + ", isShow=" + isShow + ", createBy=" + createBy +
               ", createDate=" + createDate + ", updateBy=" + updateBy +
               ", updateDate=" + updateDate + ", remarks=" + remarks +
               ", deleted=" + deleted + ", id=" + id + "]";
    }
    
    
}
