/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.pagination;

/**
 * <p>
 * the sort's filed define.
 * </p>
 * 
 * @author mumu@yfyang
 * @version 1.0 2013-09-05 10:39 PM
 * @since JDK 1.5
 */
public final class SortField {
    
    /** field name */
    private final String field;
    /** sort direction */
    private final SortDirection direction;
    
    /**
     * Instantiates a new Sort field.
     * 
     * @param field
     *            the field
     * @param direction
     *            the direction
     */
    public SortField(String field, SortDirection direction) {
        this.field = field;
        this.direction = direction;
    }
    
    /**
     * Instantiates a new Sort field.
     * 
     * @param field
     *            the field
     * @param direction
     *            the direction
     */
    public SortField(String field, String direction) {
        this.field = field;
        this.direction = SortDirection.valueOfCaseInsensitive(direction);
    }
    
    /**
     * Gets field.
     * 
     * @return the field
     */
    public String getField() {
        return field;
    }
    
    /**
     * Gets direction.
     * 
     * @return the direction
     */
    public SortDirection getDirection() {
        return direction;
    }
}
