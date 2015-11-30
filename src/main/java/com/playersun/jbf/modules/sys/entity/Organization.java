/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.entity;

import org.apache.commons.lang3.StringUtils;

import com.playersun.jbf.common.constant.Constant;
import com.playersun.jbf.common.entity.DataEntity;
import com.playersun.jbf.common.plugin.entity.Treeable;

/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class Organization extends DataEntity<Organization> implements Treeable {
    
    private String name;
    
    private OrganizationType type;
    
    private Long parentId;
    
    private String parentIds;
    
    private Integer weight;
    
    private String icon;
    
    private Boolean isShow;
    
    private boolean hasChildren;
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getIcon() {
        if (!StringUtils.isEmpty(icon)) {
            return icon;
        }
        if (isRoot()) {
            return getRootDefaultIcon();
        }
        if (isLeaf()) {
            return getLeafDefaultIcon();
        }
        return getBranchDefaultIcon();
    }
    
    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Override
    public Long getParentId() {
        return parentId;
    }
    
    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public String getParentIds() {
        return parentIds;
    }
    
    @Override
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
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
    public Integer getWeight() {
        return weight;
    }
    
    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
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
    
    public Boolean getIsShow() {
        return isShow;
    }

    
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    
    public OrganizationType getType() {
        return type;
    }

    
    public void setType(OrganizationType type) {
        this.type = type;
    }
    
    
    
}
