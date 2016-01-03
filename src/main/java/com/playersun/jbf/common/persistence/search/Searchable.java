/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.util.Collection;
import java.util.Map;

import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.exception.InvalidSearchPropertyException;
import com.playersun.jbf.common.persistence.search.exception.InvalidSearchValueException;
import com.playersun.jbf.common.persistence.search.exception.SearchException;

/**
 * 
 * @author PlayerSun
 * @date Dec 13, 2015
 */
public interface Searchable {
    
    /**
     * 添加过滤条件 如key="parent.id_eq" value = 1 如果添加时不加操作符 默认是custom 即如key=parent 实际key是parent_custom
     * 
     * @param key
     *            如 name_like
     * @param value
     *            如果是in查询 多个值之间","分隔
     * @return
     */
    public Searchable addSearchParam(final String key, final Object value) throws SearchException;
    
    /**
     * 添加一组查询参数
     * 
     * @param searchParams
     * @return
     */
    public Searchable addSearchParams(final Map<String, Object> searchParams) throws SearchException;
    
    /**
     * 添加过滤条件
     * 
     * @param searchProperty
     *            查询的属性名
     * @param operator
     *            操作运算符
     * @param value
     *            值
     */
    public Searchable addCondition(final String searchProperty, final SearchOperator operator, final Object value)
            throws SearchException;
    
    public Searchable addCondition(final Condition searchFilter);
    
    /**
     * 添加多个and连接的过滤条件
     * 
     * @param searchFilters
     * @return
     */
    public Searchable addConditions(final Collection<? extends Condition> searchFilters);
    
    /**
     * 添加多个or连接的过滤条件
     * 
     * @param first
     *            第一个
     * @param others
     *            其他
     * @return
     */
    public Searchable or(final Condition first, final Condition... others);
    
    /**
     * 添加多个and连接的过滤条件
     * 
     * @param first
     * @param others
     * @return
     */
    public Searchable and(final Condition first, final Condition... others);
    
    /**
     * 移除指定key的过滤条件
     * 
     * @param key
     */
    public Searchable removeCondition(final String key);
    
    /**
     * 移除指定属性 和 操作符的过滤条件
     * 
     * @param searchProperty
     * @param operator
     * @return
     */
    public Searchable removeCondition(String searchProperty, SearchOperator operator);
    
    /**
     * 设置分页参数
     * @param page
     * @return
     */
    public Searchable setPage(final Pageable page);
    
    /**
     * @param pageNumber
     *            分页页码 索引从 0 开始
     * @param pageSize
     *            每页大小
     * @return
     */
    public Searchable setPage(final int pageNumber, final int pageSize);
    
    /**
     * 设置排序参数
     * @param sort
     * @return
     */
    public Searchable addSort(final Sort sort);
    
    /**
     * 设置排序参数
     * @param direction
     * @param property
     * @return
     */
    public Searchable addSort(final Sort.Direction direction, String property);
    
    /**
     * 获取查询过滤条件
     * 
     * @return
     */
    public Collection<Condition> getConditions();
    
    /**
     * 是否有查询参数
     * 
     * @return
     */
    public boolean hasCondition();
    
    /**
     * 是否有排序
     * 
     * @return
     */
    public boolean hashSort();
    
    /**
     * 删除排序参数
     */
    public void removeSort();
    
    /**
     * 是否有分页
     * 
     * @return
     */
    public boolean hasPageable();
    
    /**
     * 删除分页参数
     */
    public void removePageable();
    
    /**
     * 获取分页和排序信息
     * 
     * @return
     */
    public Pageable getPage();
    
    /**
     * 获取排序信息
     * 
     * @return
     */
    public Sort getSort();
    
    /**
     * 是否包含查询键 如 name_like 包括 or 和 and的
     * 
     * @param key
     * @return
     */
    public boolean containsSearchKey(final String key);
    
    /**
     * 获取查询属性对应的值 不能获取or 或 and 的
     * 
     * @param key
     * @return
     */
    public <T> T getValue(final String key);
    
    /**
     * 设置供Mybatis mapper.xml中所使用的参数
     * 例如：select * from abc where id = #{id} 在Executehaddle之前就会被转换成预查询语句
     * 所以要先给对应的参数。
     * 
     * 后面的Condition中的参数在分析完之后也会append到这些参数的后面，最后一起当作整条预查询语句的参数
     * 
     * @param o
     * @return
     */
    public Searchable setParamObject(Object o);
    
    /**
     * 获得查询参数
     * @return
     */
    public Object getParamObject();
}
