/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.exception;

/**
 * 异常，没有这个用户
 * @author PlayerSun
 * @date Oct 4, 2015
 */
public class UserNotExistsException extends UserException {
    
    public UserNotExistsException() {
        super("user.not.exists", null);
    }
    
}
