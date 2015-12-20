/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search.exception;


/**
 * 
 * @author PlayerSun
 * @date Dec 20, 2015
 */
public class InvalidSearchPropertyException extends SearchException {
    public InvalidSearchPropertyException() {
        super();
    }
    
    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Object value) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]");
    }
}
