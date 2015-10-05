/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.exception;

public class UserBlockedException extends UserException {
    
    public UserBlockedException(String reason) {
        super("user.blocked", new Object[] { reason });
    }
}
