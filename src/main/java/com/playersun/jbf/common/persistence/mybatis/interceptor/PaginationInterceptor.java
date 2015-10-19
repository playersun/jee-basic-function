/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.interceptor;

import java.io.Serializable;
import java.util.Iterator;
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
import com.playersun.jbf.common.persistence.pagination.SortField;
import com.playersun.jbf.common.utils.sql.SqlUtil;

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
    
    //数据库方言
    protected Dialect dialect;
    
    private static final Log LOG = LogFactory.getLog(CountHelper.class);
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        
        //判断是否需要分页
        if (args[1] != null && args[1] instanceof Pageable) {
            
            final Pageable pageable = (Pageable) args[1];
            
            final MappedStatement mappedStatement = (MappedStatement) args[0];
            
            //获得分页对象中的查询条件
            //然后从MappedStatemnt中获得boundSql
            BoundSql boundSql = mappedStatement.getBoundSql(pageable);
            
            //原始的sql语句
            String originalSql = boundSql.getSql().trim();
            
            //用原始的sql语句获得总数据
            int count = CountHelper
                    .getCount(originalSql, null, mappedStatement,
                            pageable.getCondition(), boundSql, dialect);
            
            LOG.debug(String.valueOf(count));
            
            String newSql = originalSql;
            List<SortField> sf = pageable.getSortField();
            if (sf != null && sf.size() > 0) {
                newSql = buildeNewSql(originalSql, sf.iterator());
            }
            
            //分页查询 本地化对象 修改数据库注意修改实现
            String pageSql = dialect.getLimitString(newSql,
                    pageable.getOffset(), pageable.getPageSize(), count);
            
            LOG.debug(pageSql);
            
            args[2] = new RowBounds(RowBounds.NO_ROW_OFFSET,
                    RowBounds.NO_ROW_LIMIT);
            
            BoundSql newBoundSql = new BoundSql(
                    mappedStatement.getConfiguration(), pageSql,
                    boundSql.getParameterMappings(), pageable.getCondition());
            
            args[1] = pageable.getCondition();
            
            MappedStatement newMs = copyFromMappedStatement(mappedStatement,
                    new BoundSqlSqlSource(newBoundSql));
            
            args[0] = newMs;
            
            Object o = invocation.proceed();
            
            PageMybatis<?> p = null;
            if (o instanceof List) {
                p = new PageMybatis((List<?>) o, pageable, count);
            }
            
            return p;
        }
        
        return invocation.proceed();
    }
    
    private String buildeNewSql(String originalSql, Iterator<SortField> iterator) {
        StringBuffer strBuf = new StringBuffer(originalSql);
        SortField sf = null;
        if (iterator.hasNext()) {
            sf = iterator.next();
            strBuf.append(SqlUtil.BLANK_STR).append(SqlUtil.ORDER_BY_STR)
                    .append(SqlUtil.BLANK_STR);
            strBuf.append(sf.getField()).append(SqlUtil.BLANK_STR)
                    .append(sf.getDirection());
            
            while (iterator.hasNext()) {
                sf = iterator.next();
                strBuf.append(SqlUtil.COMMA);
                strBuf.append(sf.getField()).append(SqlUtil.BLANK_STR)
                        .append(sf.getDirection());
            }
        }
        return strBuf.toString();
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
    
    private MappedStatement copyFromMappedStatement(MappedStatement ms,
            SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(
                ms.getConfiguration(), ms.getId(), newSqlSource,
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
