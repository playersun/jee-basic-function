/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.pagination;

import java.util.List;

import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.pagination.SortField;

/**
 * 请求分页参数
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
public class PageRequest implements Pageable {
    
    //第几页
    private int pageNumber;
    
    //每页多少数据
    private int pageSize;
    
    //查询条件
    private Object condition;
    
    //排序条件
    private List<SortField> sortField;
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     */
    public PageRequest(int number, int size) {
        this(number, size, null, null);
    }
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     * @param condition where条件的参数
     */
    public PageRequest(int number, int size, Object condition) {
        this(number, size, condition, null);
    }
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     * @param condition where条件的参数
     * @param sortField 排队条件
     */
    public PageRequest(int number, int size, Object condition, List<SortField> sortField) {
        if (number < 0) {
            throw new IllegalArgumentException(
                    "Page index must not be less than zero!");
        }
        
        if (size < 0) {
            throw new IllegalArgumentException(
                    "Page size must not be less than zero!");
        }
        
        this.pageNumber = number;
        this.pageSize = size;
        this.condition = condition;
        this.sortField = sortField;
    }
    
    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }
    
    @Override
    public int getPageSize() {
        return this.pageSize;
    }
    
    @Override
    public int getOffset() {
        return (this.pageNumber - 1) * this.pageSize;
    }
    
    @Override
    public boolean hasPrevious() {
        return false;
    }
    
    @Override
    public Object getCondition() {
        return this.condition;
    }

    @Override
    public List<SortField> getSortField() {
        return this.sortField;
    }
    
}
