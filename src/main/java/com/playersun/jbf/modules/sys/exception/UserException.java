/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.exception;

import com.playersun.jbf.common.exception.BaseException;

/**
 * 
 * @author PlayerSun
 * @date Aug 30, 2015
 */
public class UserException extends BaseException {
    
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
