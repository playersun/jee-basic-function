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
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author PlayerSun
 * @date Nov 14, 2015

@MappedTypes({ Enum.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class Enum2Varchar<E extends Enum<E>> extends BaseTypeHandler<E> {
    
    private Class<E> type;
    
    public Enum2Varchar(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
            JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, parameter.name());
        } else {
            ps.setObject(i, parameter.name(), jdbcType.TYPE_CODE); // see r3589
        }
    }
    
    @Override
    public E getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String s = rs.getString(columnName);
        return s == null ? null : Enum.valueOf(type, s);
    }
    
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String s = rs.getString(columnIndex);
        return s == null ? null : Enum.valueOf(type, s);
    }
    
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String s = cs.getString(columnIndex);
        return s == null ? null : Enum.valueOf(type, s);
    }
    
    protected Set<Long> getChanged(String str) {
        if (str == null) {
            return null;
        }
        
        try {
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
} */
