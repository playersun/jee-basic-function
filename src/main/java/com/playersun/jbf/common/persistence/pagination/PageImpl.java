/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 一个真正分页数据
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
@Deprecated
public class PageImpl<T> implements Page<T>, Serializable {
    
    private static final long serialVersionUID = -4688151086846220724L;
    
    private List<T> content = new ArrayList<T>();
    private Pageable pageable;
    private long total;
    
    private PageImpl() {
    }
    
    /* public */private PageImpl(List<T> content, Pageable pageable, long total) {
        
        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        }
        
        this.content.addAll(content);
        this.total = total;
        this.pageable = pageable;
    }
    
    @Override
    public int getNumber() {
        return pageable == null ? 0 : pageable.getPageNumber();
    }
    
    @Override
    public int getSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }
    
    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total /
                                                    (double) getSize());
    }
    
    @Override
    public Iterator<T> iterator() {
        return content.iterator();
    }
    
    @Override
    public boolean hasPreviousPage() {
        return getNumber() > 0;
    }
    
    @Override
    public boolean isFirstPage() {
        return !hasPreviousPage();
    }
    
    @Override
    public boolean hasNextPage() {
        return getNumber() + 1 < getTotalPages();
    }
    
    @Override
    public boolean isLastPage() {
        return !hasNextPage();
    }
    
    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }
    
    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }
    
    @Override
    public String toString() {
        
        String contentType = "UNKNOWN";
        
        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }
        
        return String.format("Page %s of %d containing %s instances",
                getNumber(), getTotalPages(), contentType);
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof PageImpl<?>)) {
            return false;
        }
        
        PageImpl<?> that = (PageImpl<?>) obj;
        
        boolean totalEqual = this.total == that.total;
        boolean contentEqual = this.content.equals(that.content);
        boolean pageableEqual = this.pageable == null ? that.pageable == null
                : this.pageable.equals(that.pageable);
        
        return totalEqual && contentEqual && pageableEqual;
    }
    
}
