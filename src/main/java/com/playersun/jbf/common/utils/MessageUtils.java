/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.utils;

import org.springframework.context.MessageSource;

public class MessageUtils {
    
    private static MessageSource messageSource;
    
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     * 
     * @param code
     *            消息键
     * @param args
     *            参数
     * @return
     */
    public static String message(String code, Object... args) {
        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }
        return messageSource.getMessage(code, args, null);
    }
    
}
