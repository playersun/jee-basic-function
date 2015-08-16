/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.entity.DataEntity;

/**
 * 资源类
 * @author PlayerSun
 * @date Aug 9, 2015
 */
public class Resource extends DataEntity<Resource> {
    
    private Long parentID;
    private String parentIDs;
    private String name;
    private String identification;
    private String url;
    private String icon;
    private Integer weight;
    private Integer isShow;
    
    public Long getParentID() {
        return parentID;
    }
    
    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }
    
    public String getParentIDs() {
        return parentIDs;
    }
    
    public void setParentIDs(String parentIDs) {
        this.parentIDs = parentIDs;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIdentification() {
        return identification;
    }
    
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
    public Integer getIsShow() {
        return isShow;
    }
    
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
