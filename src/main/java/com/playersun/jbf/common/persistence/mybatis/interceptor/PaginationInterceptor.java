/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.interceptor;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.playersun.jbf.common.persistence.dialect.DBMS;
import com.playersun.jbf.common.persistence.dialect.Dialect;
import com.playersun.jbf.common.persistence.dialect.DialectClient;
import com.playersun.jbf.common.persistence.mybatis.pagination.CountHelper;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageMybatis;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.SearchRequest;
import com.playersun.jbf.common.persistence.search.Searchable;
import com.playersun.jbf.common.persistence.search.SearchableSqlBuilder;

/**
 * 数据库分页插件
 * 
 * @author PlayerSun
 * @date Aug 15, 2015
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class PaginationInterceptor implements Interceptor, Serializable {
    
    private static final long serialVersionUID = -891217777766024561L;
    
    //数据库方言
    protected Dialect dialect;
    
    private static final Log LOG = LogFactory.getLog(CountHelper.class);
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        
        boolean hasSearch = false;
        boolean hasPageable = false;
        
        //判断传进来的参数是否是搜索条件
        Searchable searchable = (hasSearch = (args[1] != null && args[1] instanceof Searchable)) ? (Searchable) args[1]
                : null;
        //获得分页条件
        Pageable pageable = hasSearch ? searchable.getPage()
                : (args[1] != null && args[1] instanceof Pageable) ? (Pageable) args[1] : null;
        
        hasPageable = pageable != null;
        
        //判断是否需要拦截处理搜索或分页
        if (hasSearch || hasPageable) {
            
            if (!hasSearch && hasPageable) {
                searchable = new SearchRequest(pageable);
            }
            
            //获得到Statement
            final MappedStatement mappedStatement = (MappedStatement) args[0];
            
            //生成分页需要的查询语句，包括count和paged
            SearchableSqlBuilder sBuilder = new SearchableSqlBuilder(mappedStatement, searchable).build();
            PageMybatis<?> p = null;
            int count = 0;
            if (hasPageable) {
                
                //如果要查询分页数据，先查询出来总记录数
                count = CountHelper.getCount(mappedStatement, sBuilder);
                
                LOG.debug("count : " + count);
                
                //用新的sqlsource，创建新的mappedstatement
                args[0] = copyFromMappedStatement(mappedStatement, sBuilder.buildPagedSqlSource(count, dialect));
                
            } else {
                //用新的sqlsource，创建新的mappedstatement
                args[0] = copyFromMappedStatement(mappedStatement, sBuilder.buildPagedSqlSource());
            }
            
            args[1] = sBuilder.getParams();
            
            Object o = invocation.proceed();
            if (o instanceof List) {
                if (hasPageable) {
                    p = new PageMybatis((List<?>) o, pageable, count);
                } else {
                    p = new PageMybatis((List<?>) o);
                }
            }
            
            LOG.debug("Page : " + p);
            
            return p;
        }
        
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties p) {
        String dialectClass = p.getProperty("dialectClass");
        DBMS dbms;
        if (StringUtils.isEmpty(dialectClass)) {
            String dialect = p.getProperty("dbms");
            dbms = DBMS.valueOf(dialect.toUpperCase());
        } else {
            dbms = DBMS.MYSQL;
        }
        
        dialect = DialectClient.getDbmsDialect(dbms);
    }
    
    /**
     * 用新的SqlSource，生成新的MappedStatement
     * 
     * 主要是重新组织预查询语句 {@link com.playersun.jbf.common.persistence.search.SearchableSqlBuilder}
     * 
     * @param ms
     * @param newSqlSource
     * @return
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
                ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }
    
    public static class BoundSqlSqlSource implements SqlSource {
        
        BoundSql boundSql;
        
        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }
        
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
    
}
