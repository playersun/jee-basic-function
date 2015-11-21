/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.mybatis.types;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.playersun.jbf.common.constant.Constant;

/**
 * 自定义类型
 * 数据库和1，2，3类型java Set类型转换
 * @author PlayerSun
 * @date Oct 19, 2015
 */
@MappedTypes({ Set.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class Set2Varchar extends BaseTypeHandler<Set<Long>> {
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            Set<Long> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Joiner.on(Constant.COMMA).join(parameter));
    }
    
    @Override
    public Set<Long> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String string = rs.getString(columnName);
        if (string != null) {
            return getChanged(string.split(Constant.COMMA));
        }
        return null;
    }
    
    @Override
    public Set<Long> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String string = rs.getString(columnIndex);
        if (string != null) {
            return getChanged(string.split(Constant.COMMA));
        }
        return null;
    }
    
    @Override
    public Set<Long> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String string = cs.getString(columnIndex);
        if (string != null) {
            return getChanged(string.split(Constant.COMMA));
        }
        return null;
    }
    
    protected Set<Long> getChanged(String[] array) {
        if (array == null) {
            return null;
        }
        
        Set<Long> set = Sets.newHashSet();
        for (String str : array) {
            set.add(Long.parseLong(str));
        }
        return set;
    }
    
}
