/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;

import java.util.List;

/**
 * 抽象的请求分页接口
 * @author PlayerSun
 * @date Aug 15, 2015
 */
public interface Pageable {
    /**
     * 第几页.
     * 
     * @return 第几页.
     */
    int getPageNumber();
    
    /**
     * 获得每一页的个数.
     * 
     * @return 每页个数
     */
    int getPageSize();
    
    /**
     * 获得偏移，即PageNumber * PageSize
     * @return 偏移量
     */
    int getOffset();
    
    /**
     * 是否有前一页
     * @return
     */
    boolean hasPrevious();
    
    /**
     * 获得查询条件
     * @return
     */
    Object getCondition();
    
    /**
     * 获得排序条件
     * @return
     */
    List<SortField> getSortField();
}
