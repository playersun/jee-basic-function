/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.playersun.jbf.common.persistence.search.exception.InvlidSearchOperatorException;
import com.playersun.jbf.common.persistence.search.exception.SearchException;


/**
 * 
 * @author PlayerSun
 * @date Dec 27, 2015
 */
public class CommonCondition implements Condition {
  //查询参数分隔符
    public static final String separator = "_";

    private String key;
    private String searchProperty;
    private SearchOperator operator;
    private Object value;
    
    
    /**
     * @param searchProperty 属性名
     * @param operator       操作
     * @param value          值
     */
    private CommonCondition(final String searchProperty, final SearchOperator operator, final Object value) {
        this.searchProperty = searchProperty;
        this.operator = operator;
        this.value = value;
        this.key = this.searchProperty + separator + this.operator;
    }
    
    /**
     * 根据查询key和值生成Condition
     *
     * @param key   如 name_like
     * @param value
     * @return
     */
    public static Condition newCondition(final String key, final Object value) throws SearchException {

        Assert.notNull(key, "Condition key must not null");

        String[] searchs = StringUtils.split(key, separator);

        if (searchs.length == 0) {
            throw new SearchException("Condition key format must be : property or property_op");
        }

        String searchProperty = searchs[0];

        SearchOperator operator = null;
        if (searchs.length == 1) {
            operator = SearchOperator.eq;
        } else {
            try {
                operator = SearchOperator.valueOf(searchs[1]);
            } catch (IllegalArgumentException e) {
                throw new InvlidSearchOperatorException(searchProperty, searchs[1]);
            }
        }

        boolean allowBlankValue = SearchOperator.isAllowBlankValue(operator);
        boolean isValueBlank = (value == null);
        isValueBlank = isValueBlank || (value instanceof String && StringUtils.isBlank((String) value));
        isValueBlank = isValueBlank || (value instanceof List && ((List) value).size() == 0);
        //过滤掉空值，即不参与查询
        if (!allowBlankValue && isValueBlank) {
            return null;
        }

        return new CommonCondition(searchProperty, operator, value);
    }
    
    /**
     * 根据查询属性、操作符和值生成Condition
     *
     * @param searchProperty
     * @param operator
     * @param value
     * @return
     */
    public static Condition newCondition(final String searchProperty, final SearchOperator operator, final Object value) {
        return new CommonCondition(searchProperty, operator, value);
    }

    
    public String getKey() {
        return key;
    }

    
    public void setKey(String key) {
        this.key = key;
    }

    
    public String getSearchProperty() {
        return searchProperty;
    }

    
    public void setSearchProperty(String searchProperty) {
        this.searchProperty = searchProperty;
    }

    
    public SearchOperator getOperator() {
        return operator;
    }

    
    public void setOperator(SearchOperator operator) {
        this.operator = operator;
    }

    
    public Object getValue() {
        return value;
    }

    
    public void setValue(Object value) {
        this.value = value;
    }

    
    public static String getSeparator() {
        return separator;
    }
    
    
    /**
     * 获取自定义查询使用的操作符
     * 1、首先获取前台传的
     * 2、返回空
     *
     * @return
     */
    public String getOperatorStr() {
        if (operator != null) {
            return operator.getSymbol();
        }
        return "";
    }
    
    /**
     * 是否是一元过滤 如is null is not null
     *
     * @return
     */
    public boolean isUnaryFilter() {
        String operatorStr = getOperator().getSymbol();
        return StringUtils.isNotEmpty(operatorStr) && operatorStr.startsWith("is");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonCondition that = (CommonCondition) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "searchProperty='" + searchProperty + '\'' +
                ", operator=" + operator +
                ", value=" + value +
                '}';
    }
}
