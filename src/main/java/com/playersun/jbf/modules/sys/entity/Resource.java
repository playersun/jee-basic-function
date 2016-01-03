/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import com.playersun.jbf.common.constant.Constant;
import com.playersun.jbf.common.entity.DataEntity;
import com.playersun.jbf.common.plugin.entity.Treeable;

/**
 * 资源类
 * 
 * @author PlayerSun
 * @date Aug 9, 2015
 */
public class Resource extends DataEntity<Resource> implements Treeable {
    
    private Long parentId;
    
    private String parentIds;
    
    private String name;
    
    private String identification;
    
    private String url;
    
    private String icon;
    
    private Integer weight;
    
    private Boolean isShow;
    
    private boolean hasChildren;
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public String getParentIds() {
        return parentIds;
    }
    
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
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
    
    public Boolean getIsShow() {
        return isShow;
    }
    
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
    
    @Override
    public String toString() {
        return "Resource [parentId=" + parentId + ", parentIds=" + parentIds +
               ", name=" + name + ", identification=" + identification +
               ", url=" + url + ", icon=" + icon + ", weight=" + weight +
               ", isShow=" + isShow + "]";
    }
    
    @Override
    public String getSeparator() {
        return Constant.COMMA;
    }
    
    @Override
    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }
    
    @Override
    public boolean isRoot() {
        if (getParentId() != null && getParentId() == 0) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isLeaf() {
        if (isRoot()) {
            return false;
        }
        if (isHasChildren()) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public boolean isHasChildren() {
        return hasChildren;
    }
    
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
    
    @Override
    public String getRootDefaultIcon() {
        return Constant.TREE_ROOT_OPEN;
    }
    
    @Override
    public String getBranchDefaultIcon() {
        return Constant.TREE_BRANCH;
    }
    
    @Override
    public String getLeafDefaultIcon() {
        return Constant.TREE_LEAF;
    }
    
}
