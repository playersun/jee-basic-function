/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import com.opensymphony.module.sitemesh.Page;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageRequest;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.exception.InvalidSearchPropertyException;
import com.playersun.jbf.common.persistence.search.exception.InvalidSearchValueException;
import com.playersun.jbf.common.persistence.search.exception.SearchException;
import com.playersun.jbf.common.utils.reflex.Reflections;


/**
 * 
 * @author PlayerSun
 * @date Dec 20, 2015
 */
public class SearchRequest implements Searchable {
    private final Map<String, Condition> conditionMap = Maps.newHashMap();
    
    //使用这个的目的是保证拼sql的顺序是按照添加时的顺序
    private final List<Condition> conditions = Lists.newArrayList();

    //分页参数
    private Pageable page;

    //排序参数
    private Sort sort;
    
    //Mybatis参数
    private Object paramObj;
    
    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     */
    public SearchRequest() {
        this(null, null, null);
    }
    
    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     
    public SearchRequest(String key, Object value) {
        Map<String, Object> map = new HashMap<String, Object>();
    }*/
    
    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     */
    public SearchRequest(Pageable page) {
        this(null, page, null);
    }
    
    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     */
    public SearchRequest(final Map<String, Object> searchParams) {
        this(searchParams, null, null);
    }
    
    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     */
    public SearchRequest(final Map<String, Object> searchParams, final Pageable page) {
        this(searchParams, page, null);
    }

    /**
     * @param searchParams
     * @see SearchRequest#SearchRequest(java.util.Map<java.lang.String,java.lang.Object>, Pageable, sort)
     */
    public SearchRequest(final Map<String, Object> searchParams, final Sort sort) throws SearchException {
        this(searchParams, null, sort);
    }
    
    /**
     * <p>根据查询参数拼Search<br/>
     * 查询参数格式：property_op=value 或 customerProperty=value<br/>
     * customerProperty查找规则是：1、先查找domain的属性，2、如果找不到查找domain上的SearchPropertyMappings映射规则
     * 属性、操作符之间用_分割，op可省略/或custom，省略后值默认为custom，即程序中自定义<br/>
     * 如果op=custom，property也可以自定义（即可以与domain的不一样）,
     * </p>
     *
     * @param searchParams 查询参数组
     * @param page         分页
     * @param sort         排序
     */
    public SearchRequest(final Map<String, Object> searchParams, final Pageable page, final Sort sort)
            throws SearchException {

        toSearchConditions(searchParams);

        merge(sort, page);
    }
    
    private void toSearchConditions(final Map<String, Object> searchParams) throws SearchException {
        if (searchParams == null || searchParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            addCondition(CommonCondition.newCondition(key, value));
        }
    }
    
    private void merge(Sort sort, Pageable page) {
        if (sort == null) {
            sort = this.sort;
        }
        if (page == null) {
            page = this.page;
        }

        //合并排序
        if (sort == null) {
            this.sort = page != null ? page.getSort() : null;
        } else {
            this.sort = (page != null ? sort.and(page.getSort()) : sort);
        }
        //把排序合并到page中
        if (page != null) {
            this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), this.sort);
            setParamObject(page.getParmObject());
        } else {
            this.page = null;
        }
    }

    @Override
    public Searchable addSearchParam(String key, Object value)
            throws SearchException {
        addCondition(CommonCondition.newCondition(key, value));
        return this;
    }

    @Override
    public Searchable addSearchParams(Map<String, Object> searchParams)
            throws SearchException {
        toSearchConditions(searchParams);
        return this;
    }

    @Override
    public Searchable addCondition(String searchProperty,
            SearchOperator operator, Object value) throws SearchException {
        addCondition(CommonCondition.newCondition(searchProperty, operator,value));
        return null;
    }

    @Override
    public Searchable addCondition(Condition condition) {
        if (condition == null) {
            return this;
        }
        if (condition instanceof CommonCondition) {
            CommonCondition commonCondition = (CommonCondition) condition;
            String key = commonCondition.getKey();
            conditionMap.put(key, condition);
        }
        int index = conditions.indexOf(condition);
        if(index != -1) {
            conditions.set(index, condition);
        } else {
            conditions.add(condition);
        }
        return this;
    }

    @Override
    public Searchable addConditions(
            Collection<? extends Condition> cdts) {
        if (CollectionUtils.isEmpty(cdts)) {
            return this;
        }
        for (Condition condition : cdts) {
            addCondition(condition);
        }
        return this;
    }

    @Override
    public Searchable or(Condition first, Condition... others) {
        OrCondition orCondition = new OrCondition();
        orCondition.getOrConditions().add(first);
        if (ArrayUtils.isNotEmpty(others)) {
            orCondition.getOrConditions().addAll(Arrays.asList(others));
        }
        return addCondition(orCondition);
    }

    @Override
    public Searchable and(Condition first, Condition... others) {
        AndCondition andCondition = new AndCondition();
        andCondition.getAndConditions().add(first);
        if (ArrayUtils.isNotEmpty(others)) {
            andCondition.getAndConditions().addAll(Arrays.asList(others));
        }
        return addCondition(andCondition);
    }

    @Override
    public Searchable removeCondition(String key) {
        if (key == null) {
            return this;
        }

        Condition condition = conditionMap.remove(key);

        if (condition == null) {
            condition = conditionMap.remove(getCustomKey(key));
        }

        if (condition == null) {
            return this;
        }

        conditions.remove(condition);

        return this;
    }

    @Override
    public Searchable removeCondition(String searchProperty,
            SearchOperator operator) {
        this.removeCondition(searchProperty + CommonCondition.separator + operator);
        return this;
    }

    @Override
    public Searchable setPage(Pageable page) {
        merge(sort, page);
        return this;
    }

    @Override
    public Searchable setPage(int pageNumber, int pageSize) {
        merge(sort, new PageRequest(pageNumber, pageSize));
        return this;
    }

    @Override
    public Searchable addSort(Sort sort) {
        merge(sort, page);
        return this;
    }

    @Override
    public Searchable addSort(Sort.Direction direction, String property) {
        merge(new Sort(direction, property), page);
        return this;
    }

    @Override
    public Collection<Condition> getConditions() {
        return Collections.unmodifiableCollection(conditions);
    }

    @Override
    public boolean hasCondition() {
        return conditions.size() > 0;
    }

    @Override
    public boolean hashSort() {
        return this.sort != null && this.sort.iterator().hasNext();
    }

    @Override
    public void removeSort() {
        this.sort = null;
        if (this.page != null) {
            this.page = new PageRequest(page.getPageNumber(), page.getPageSize(), null);
        }
    }

    @Override
    public boolean hasPageable() {
        return this.page != null && this.page.getPageSize() > 0;
    }

    @Override
    public void removePageable() {
        this.page = null;
    }

    @Override
    public Pageable getPage() {
        return page;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public boolean containsSearchKey(String key) {
        boolean contains =
                conditionMap.containsKey(key) ||
                        conditionMap.containsKey(getCustomKey(key));

        if(contains) {
            return true;
        }

        //否则检查其中的or 和 and
        return containsSearchKey(conditions, key);
    }

    @Override
    public Object getValue(String key) {
        Condition cdt = conditionMap.get(key);
        if (cdt == null) {
            cdt = conditionMap.get(getCustomKey(key));
        }
        if (cdt == null) {
            return null;
        }

        if (cdt instanceof CommonCondition) {
            CommonCondition condt = (CommonCondition) cdt;
            return condt.getValue();
        }

        return null;
    }
    
    private String getCustomKey(String key) {
        return key + CommonCondition.separator + SearchOperator.eq;
    }
    
    private boolean containsSearchKey(List<Condition> cdts, String key) {
        boolean contains = false;
        for(Condition cdt : cdts) {
            if(cdt instanceof OrCondition) {
                OrCondition orCondition = (OrCondition) cdt;
                contains = containsSearchKey(orCondition.getOrConditions(), key);
            }
            if(cdt instanceof AndCondition) {
                AndCondition andCondition = (AndCondition) cdt;
                contains = containsSearchKey(andCondition.getAndConditions(), key);
            }

            if(cdt instanceof CommonCondition) {
                CommonCondition condition = (CommonCondition) cdt;
                contains = condition.getKey().equals(key) || condition.getSearchProperty().equals(key);
            }

            if(contains) {
                return true;
            }
        }

        return contains;
    }

    @Override
    public Searchable setParamObject(Object o) {
        this.paramObj = Reflections.bean2Map(this.paramObj,o);
        return this;
    }

    @Override
    public Object getParamObject() {
        return paramObj;
    }
}
