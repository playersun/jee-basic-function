/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.pagination;

import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.Sort;

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
    private Object paramObj;
    
    //排序条件
    private Sort sort;
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     */
    public PageRequest(int number, int size) {
        this(number, size, null);
    }
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     * @param condition where条件的参数
     * @param sort 排队条件
     */
    public PageRequest(int number, int size, Sort sort) {
        this(number, size, null, sort);
    }
    
    /**
     * 创建一个分页参数 
     * @param number    第几页
     * @param size      每页多少条数据
     * @param condition where条件的参数
     * @param sort 排队条件
     */
    public PageRequest(int number, int size, Object param, Sort sort) {
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
        this.paramObj = param;
        this.sort = sort;
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
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Object getParmObject() {
        return paramObj;
    }
    
}
