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
public class InvalidSearchValueException extends SearchException {
    public InvalidSearchValueException() {
        super();
    }
    
    public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
        super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
                "entityProperty [" + entityProperty + "], value [" + value + "]");
    }
    
    
}
