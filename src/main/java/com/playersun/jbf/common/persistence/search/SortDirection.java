/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

/**
 * <p>
 * sort's direction.
 * </p>
 * 
 * @author mumu@yfyang
 * @version 1.0 2013-09-05 10:41 PM
 * @since JDK 1.5
 */
public enum SortDirection {
    /**
     * The ASC.
     */
    ASC("asc"),
    /**
     * The DESC.
     */
    DESC("desc");
    
    private String direction;
    
    private SortDirection(String direction) {
        this.direction = direction;
    }
    
    /**
     * Value of case insensitive.
     * 
     * @param value
     *            the value
     * @return the sort direction
     */
    public static SortDirection valueOfCaseInsensitive(String value) {
        String valueUpper = value.toUpperCase();
        return SortDirection.valueOf(valueUpper);
    }
    
    /**
     * Gets direction.
     * 
     * @return the direction
     */
    public String getDirection() {
        return this.direction;
    }
}
