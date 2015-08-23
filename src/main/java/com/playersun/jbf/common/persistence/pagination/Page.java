/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;

import java.util.Iterator;
import java.util.List;

/**
 * 分页的结果，包含查询到的数据和分页的信息
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
@Deprecated
public interface Page<T> extends Iterable<T> {
    
    /**
     * 获得当前是第几页
     * 
     * @return 第几页
     */
    int getNumber();
    
    /**
     * 每页多少条数据.
     * 
     * @return 每页多少条数据
     */
    int getSize();
    
    /**
     * 总共多少页.
     * 
     * @return 总共多少页
     */
    int getTotalPages();
    
    /**
     * 是否有上一页.
     * 
     * @return 是否有上一页
     */
    boolean hasPreviousPage();
    
    /**
     * 是否第一页.
     * 
     * @return 是否第一页
     */
    boolean isFirstPage();
    
    /**
     * 是否有下一页.
     * 
     * @return 是否有下一页
     */
    boolean hasNextPage();
    
    /**
     * 是否最后一页.
     * 
     * @return 是否最后一页
     */
    boolean isLastPage();
    
    Iterator<T> iterator();
    
    /**
     * 获得数据.
     * 
     * @return 包含的数据
     */
    List<T> getContent();
    
    /**
     * 是否有数据.
     * 
     * @return 是否有数据
     */
    boolean hasContent();
}
