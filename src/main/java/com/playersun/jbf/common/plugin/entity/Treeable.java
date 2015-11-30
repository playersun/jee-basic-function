/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.plugin.entity;


/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public interface Treeable {
    public void setName(String name);

    public String getName();

    /**
     * 显示的图标 大小为16×16
     *
     * @return
     */
    public String getIcon();

    public void setIcon(String icon);

    /**
     * 父路径
     *
     * @return
     */
    public Long getParentId();

    public void setParentId(Long parentId);

    /**
     * 所有父路径 如1,2,3,
     *
     * @return
     */
    public String getParentIds();

    public void setParentIds(String parentIds);

    /**
     * 获取 parentIds 之间的分隔符
     *
     * @return
     */
    public String getSeparator();

    /**
     * 把自己构造出新的父节点路径
     *
     * @return
     */
    public String makeSelfAsNewParentIds();

    /**
     * 权重 用于排序 越小越排在前边
     *
     * @return
     */
    public Integer getWeight();

    public void setWeight(Integer weight);

    /**
     * 是否是根节点
     *
     * @return
     */
    public boolean isRoot();

    /**
     * 是否是叶子节点
     *
     * @return
     */
    public boolean isLeaf();

    /**
     * 是否有孩子节点
     *
     * @return
     */
    public boolean isHasChildren();

    /**
     * 根节点默认图标 如果没有默认 空即可  大小为16×16
     */
    public String getRootDefaultIcon();

    /**
     * 树枝节点默认图标 如果没有默认 空即可  大小为16×16
     */
    public String getBranchDefaultIcon();

    /**
     * 树叶节点默认图标 如果没有默认 空即可  大小为16×16
     */
    public String getLeafDefaultIcon();

}
