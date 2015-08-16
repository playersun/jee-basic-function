/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.interceptor;

import java.io.Serializable;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.playersun.jbf.common.persistence.pagination.Pageable;

/**
 * 数据库分页插件
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor, Serializable {
    
    private static final long serialVersionUID = -891217777766024561L;
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        
        if (args[1] instanceof Pageable) {
            
            final MappedStatement mappedStatement = (MappedStatement) args[0];

            BoundSql boundSql = mappedStatement
                    .getBoundSql(((Pageable) args[1]).getCondition());
            Object parameterObject = boundSql.getParameterObject();
            System.out.print(parameterObject);
        }
        
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
        
    }
    
}
