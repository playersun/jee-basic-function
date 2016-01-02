/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search.exception;

import com.playersun.jbf.common.persistence.search.SearchOperator;


/**
 * 
 * @author PlayerSun
 * @date Dec 27, 2015
 */
public class InvlidSearchOperatorException extends SearchException {
    public InvlidSearchOperatorException() {
        super();
    }
    
    public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }
    
    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Object value) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator());
    }
}
