/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;


/**
 * 请求分页参数
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
public class PageRequest implements Pageable {
    
    private int pageNumber;
    
    private int pageSize;
    
    private Object condition;
    
    public PageRequest(int number, int size) {
        this(number, size, null);
    }
    
    public PageRequest(int number, int size, Object condition) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException(
                    "Page index must not be less than zero!");
        }
        
        if (pageSize < 0) {
            throw new IllegalArgumentException(
                    "Page size must not be less than zero!");
        }
        
        this.pageNumber = number;
        this.pageSize = size;
        this.condition = condition;
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
        return (this.pageNumber * this.pageSize);
    }
    
    @Override
    public boolean hasPrevious() {
        return false;
    }
    
    @Override
    public Object getCondition() {
        return this.condition;
    }
    
}
