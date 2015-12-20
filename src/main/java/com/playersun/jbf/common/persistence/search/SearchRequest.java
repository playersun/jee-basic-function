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
 * @date Dec 20, 2015
 */
public class SearchRequest implements Searchable {

    @Override
    public Searchable addSearchParam(String key, Object value)
            throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addSearchParams(Map<String, Object> searchParams)
            throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addCondition(String searchProperty,
            SearchOperator operator, Object value) throws SearchException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addCondition(Condition searchFilter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addConditions(
            Collection<? extends Condition> searchFilters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable or(Condition first, Condition... others) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable and(Condition first, Condition... others) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable removeCondition(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable removeCondition(String searchProperty,
            SearchOperator operator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Searchable convert(Class<T> entityClass)
            throws InvalidSearchValueException, InvalidSearchPropertyException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable markConverted() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable setPage(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable setPage(int pageNumber, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addSort(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Searchable addSort(SortDirection direction, String property) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Condition> getConditions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isConverted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasCondition() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hashSort() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeSort() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasPageable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removePageable() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Pageable getPage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Sort getSort() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean containsSearchKey(String key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public <T> T getValue(String key) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
