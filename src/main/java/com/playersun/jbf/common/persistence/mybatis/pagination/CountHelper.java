/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.pagination;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.playersun.jbf.common.persistence.dialect.Dialect;
import com.playersun.jbf.common.persistence.search.SearchableSqlBuilder;

/**
 * 获得分页查询的数据总量，用于计算总页数
 * 
 * @see https://github.com/sogyf/mybatis-pagination
 * 
 * @author poplar.yfyang / PlayerSun
 * @date Aug 22, 2015
 */
public class CountHelper {
    
    private static final Log LOG = LogFactory.getLog(CountHelper.class);
    
    /**
     * 分页时查询总记录数
     * @param mappedStatement
     * @param sqlBuilder
     * @return
     * @throws SQLException
     */
    public static int getCount(final MappedStatement mappedStatement, final SearchableSqlBuilder sqlBuilder) throws SQLException {
        Connection conn = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        
        if (/*LOG.isDebugEnabled()*/true) {
            LOG.debug("the pagination generate count sql  is [" + sqlBuilder.buildCountSql() + "]");
        }
        
        try {
            if (conn == null) {
                conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            }
            
            countStmt = conn.prepareStatement(sqlBuilder.buildCountSql());
            
            BoundSql bSql = sqlBuilder.buildCountSqlSource().getBoundSql(sqlBuilder.getParams());
            
            CountHelper.setParameters(countStmt, mappedStatement, bSql, sqlBuilder.getParams());
            rs = countStmt.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (countStmt != null) {
                countStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
    /**
     * 对SQL参数(?)设值
     * 
     * @param ps
     *            表示预编译的 SQL 语句的对象。
     * @param mappedStatement
     *            MappedStatement
     * @param boundSql
     *            SQL
     * @param parameterObject
     *            参数对象
     * @throws java.sql.SQLException
     *             数据库异常
     */
    @SuppressWarnings("unchecked")
    public static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
            
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) &&
                               boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(
                                    propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName +
                                                    " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }
}
