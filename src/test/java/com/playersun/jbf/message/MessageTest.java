/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.message;

import java.util.Locale;

import org.junit.Test;
import org.springframework.context.MessageSource;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.common.utils.SpringUtils;

public class MessageTest extends BaseTest {
    
    @Test
    public void messages() {
        MessageSource ms = SpringUtils.getBean(MessageSource.class);
        Object [] o = {"用户名"};
        System.out.println(ms.getMessage("not.null", o, null, Locale.TAIWAN));
    }
}
