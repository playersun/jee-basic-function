/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 标记DAO，方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}
 * 的扫描，将DAO和MyBatis的mapping xml文件对应
 * 
 * @author PlayerSun
 * @date 2015年6月3日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface DaoMapping {
    
    String value() default "";
}
