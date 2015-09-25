/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 查询到的，已经做好分页的数据
 * 
 * @see https://github.com/sogyf/mybatis-pagination
 * 
 * @author poplar.yfyang / PlayerSun
 * @date 2015年8月20日
 */
public class PageMybatis<T> extends ArrayList<T> {
    
    private static final long serialVersionUID = 3658482596114391317L;
    /**
     * 查询到的内容
     */
    private List<T> content = new ArrayList<T>();
    
    /**
     * 请求分页的参数，包括第几页，每页多少条数据
     */
    private Pageable pageable;
    
    /**
     * 分页需要的总数据
     */
    private long total;
    
    /**
     * 创建并初始化一个已经分好页的对象.
     * 
     * @param content
     *            the content
     * @param pageable
     *            the pageable
     * @param total
     *            the total
     */
    public PageMybatis(Collection<? extends T> content, Pageable pageable,
            long total) {
        super(content);
        
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }
    
    /**
     * 第几页
     * 
     * @return 第几页
     */
    public int getPageNumber() {
        return pageable == null ? 0
                : pageable.getPageNumber() > getTotalPages() ? getTotalPages()
                        : pageable.getPageNumber();
    }
    
    /**
     * 每页多少条数据
     * 
     * @return 每页多少条数据
     */
    public int getPageSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }
    
    /**
     * 根据数据总数和每页多少数据算出总页数
     * 
     * @return 总页数
     */
    public int getTotalPages() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total /
                                                        (double) getPageSize());
    }
    
    public Iterator<T> iterator() {
        return content.iterator();
    }
    
    /**
     * 是否有上一页
     * 
     * @return 是否有上一页
     */
    public boolean hasPreviousPage() {
        return getPageNumber() > 0;
    }
    
    /**
     * 是否是第一页
     * 
     * @return 是否是第一页
     */
    public boolean isFirstPage() {
        return !hasPreviousPage();
    }
    
    /**
     * 是否有下一页
     * 
     * @return 是否有下一页
     */
    public boolean hasNextPage() {
        return getPageNumber() + 1 < getTotalPages();
    }
    
    /**
     * 是否是最后一页
     * 
     * @return 是否是最后一页
     */
    public boolean isLastPage() {
        return !hasNextPage();
    }
    
    /**
     * 已经分好也的数据；当前页的数据
     * 
     * @return 分页数据
     */
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }
    
    /**
     * 是否有实际数据
     * 
     * @return 是否有实际数据
     */
    public boolean hasContent() {
        return !content.isEmpty();
    }
    
    @Override
    public String toString() {
        
        /*
         * String contentType = "UNKNOWN"; if (content.size() > 0) { contentType
         * = content.get(0).getClass().getName(); }
         */
        
        StringBuffer out = new StringBuffer();
        
        out.append("Current page : \t").append(this.getPageNumber())
                .append("\r\n").append("Page size : \t")
                .append(this.getPageSize()).append("\r\n")
                .append("Total page : \t").append(this.getTotalPages())
                .append("\r\n").append("Total data : \t").append(this.total)
                .append("\r\n").append("containing data type : \t")
                .append(content.toString());
        
        //        return String.format("Page %s of %d containing %s instances",
        //                getPageNumber(), getTotalPages(), contentType);
        return out.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof PageMybatis<?>)) {
            return false;
        }
        
        PageMybatis<?> that = (PageMybatis<?>) obj;
        
        boolean totalEqual = this.total == that.total;
        boolean contentEqual = this.content.equals(that.content);
        boolean pageableEqual = this.pageable == null ? that.pageable == null
                : this.pageable.equals(that.pageable);
        
        return totalEqual && contentEqual && pageableEqual;
    }
}
