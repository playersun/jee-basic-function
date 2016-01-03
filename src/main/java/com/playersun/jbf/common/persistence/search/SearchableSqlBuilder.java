/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;

import com.google.common.collect.Maps;
import com.playersun.jbf.common.constant.Constant;
import com.playersun.jbf.common.persistence.dialect.Dialect;
import com.playersun.jbf.common.persistence.mybatis.builder.SearchableSqlSourceBuilder;
import com.playersun.jbf.common.persistence.search.exception.SearchException;
import com.playersun.jbf.common.utils.reflex.Reflections;

/**
 * 将Searchable 组织成SQL语句
 * 
 * Searchable中包含分页，排序，条件等查询参数
 * 
 * 第一步:
 * 分析这些参数并组织成mybatis类型语句
 * select count(0) from x_table where x_column=#{propert_1}   分页需要获得总记录数语句
 * select * from x_table where y_column=#{propert_2}  order by y_column limit n m   物理分页语句
 * 
 * 第二部:
 * 用SearchableSqlSourceBuilder {@link org.apache.ibatis.builder.SqlSourceBuilder}
 * 将mybatis语句和Searchable中参数的值生成JDBC预查询语句
 * select count(0) from x_table where x_column=?   分页需要获得总记录数语句
 * select * from x_table where x_column=?}  order by y_column limit n m   物理分页语句
 * 和对于参数的map
 * [{propert_1，1}，{propert_2，2}]
 * 
 * @author PlayerSun
 * @date Jan 1, 2016
 */
public class SearchableSqlBuilder {
    
    private static final Log LOG = LogFactory.getLog(SearchableSqlBuilder.class);
    
    public static final String COUNT = "select count(1) from (%s) as countTable %s";
    public static final String PAGED = "select * from (%s) as pageTable %s %s";
    
    private MappedStatement mappedStatement;
    private Searchable searchable;
    private SearchableSqlSourceBuilder sssb;
    
    //原始的sql。其实就是mapper.xml中的语句
    private String origin;
    private StringBuffer where = new StringBuffer(" where 1=1 ");
    private StringBuffer orderby = new StringBuffer();
    
    private Map<String, Object> params = Maps.newHashMap();
    private SqlSource countSql;
    private SqlSource pagedSql;
    
    public SearchableSqlBuilder(MappedStatement ms, Searchable searchable) {
        this.mappedStatement = ms;
        this.searchable = searchable;
        this.sssb = new SearchableSqlSourceBuilder(ms.getConfiguration());
    }
    
    public Map<String, Object> getParams() {
        return params;
    }
    
    public SqlSource buildCountSqlSource() {
        return countSql;
    }
    
    /**
     * 获得查询总记录数的语句
     * 
     * @return
     */
    public String buildCountSql() {
        return countSql.getBoundSql(params).getSql();
    }
    
    public SqlSource buildPagedSqlSource() {
        return buildPagedSqlSource(0, null);
    }
    
    public SqlSource buildPagedSqlSource(int count, Dialect dialect) {
        if (pagedSql == null) {
            if (dialect != null) {
                pagedSql = parseSqlSource(dialect.getLimitString(String.format(PAGED, origin, where, orderby),
                        searchable.getPage().getOffset(), searchable.getPage().getPageSize(), count));
            } else {
                pagedSql = parseSqlSource(String.format(PAGED, origin, where, orderby));
            }
        }
        return pagedSql;
    }
    
    /**
     * 获得查询分页录的语句
     * 
     * @return
     */
    public String buildPagedSql(int count, Dialect dialect) {
        if (pagedSql == null) {
            throw new SearchException("PagedSqlSource is null , please check SearchableSqlBuilder !");
        }
        return buildPagedSqlSource(count, dialect).getBoundSql(params).getSql();
    }
    
    /**
     * 构建查询语句
     * 
     * @return
     */
    public SearchableSqlBuilder build() {
        paramObj2Map();
        buildWhere();
        buildSort();
        origin = mappedStatement.getBoundSql(searchable.getParamObject()).getSql();
        
        countSql = parseSqlSource(String.format(COUNT, origin, where));
        
        return this;
    }
    
    /**
     * 见 {@link com.playersun.jbf.common.persistence.search.Searchable#setParamObject(Object)}
     * 将param 和 condition的参数组织到一起
     */
    private void paramObj2Map() {
        Object object = searchable.getParamObject();
        if (object instanceof Map) {
            params.putAll((Map) object);
        } else {
            params.putAll(Reflections.bean2Map(object));
        }
    }
    
    /**
     * 构建where条件
     */
    private void buildWhere() {
        List<ResultMap> list = mappedStatement.getResultMaps();
        if (list == null || list.isEmpty()) {
            //mapper.xml中的select一定要有ResultMap
            //因为需要resultmap来找到对应的column
            throw new SearchException("Select in mapper.xml must have at least one ResultMap ! ");
        }
        //其实Mybatis中存在有多个resultmap的情况，这里约定只取第一个。其他的都不管
        List<ResultMapping> rmsResultMapping = list.get(0).getPropertyResultMappings();
        int paramIndex = 1;
        for (Condition cdt : searchable.getConditions()) {
            if (cdt instanceof CommonCondition) {
                CommonCondition cc = (CommonCondition) cdt;
                //自定义先的不管
                if (cc.getOperator().equals(SearchOperator.custom)) {
                    continue;
                }
            }
            where.append(" and ");
            paramIndex = genSql(paramIndex, cdt, rmsResultMapping);
        }
    }
    
    /**
     * 生成语句
     * @param pIdx
     * @param condition
     * @param rms
     * @return
     */
    private int genSql(int pIdx, Condition condition, List<ResultMapping> rms) {
        boolean needAppendBracket = condition instanceof OrCondition || condition instanceof AndCondition;
        
        if (needAppendBracket) {
            where.append("(");
        }
        
        if (condition instanceof CommonCondition) {
            CommonCondition cc = (CommonCondition) condition;
            String column = findInResultMapping(cc.getSearchProperty(), rms);
            String idxProp = cc.getSearchProperty() + pIdx++;
            where.append(column);
            where.append(Constant.BLANK_STR);
            where.append(cc.getOperatorStr());
            where.append(Constant.BLANK_STR);
            
            if (cc.getOperator() == SearchOperator.in || cc.getOperator() == SearchOperator.notIn) {
                //针对 in 语句
                //将数组，集合，‘1，2，3’类型的in 条件组织成sql语句
                //最后结果是 select * from table where id in (?,?,?...?)
                where.append("(");
                Object inParams = cc.getValue();
                if (inParams instanceof String) {
                    inParams = Arrays.asList(((String) inParams).split(","));
                } else if (inParams instanceof Integer[]) {
                    inParams = Arrays.asList((Integer[])inParams);
                } else if (inParams instanceof int[]) {
                    inParams = Arrays.asList(ArrayUtils.toObject((int[])inParams));
                } else if (inParams instanceof long[]) {
                    inParams = Arrays.asList(ArrayUtils.toObject((long[])inParams));
                } else if (inParams instanceof Long[]) {
                    inParams = Arrays.asList((Long[])inParams);
                } else if (inParams instanceof String[]) {
                    inParams = Arrays.asList((String[])inParams);
                } else if (inParams instanceof Object[]) {
                    inParams = Arrays.asList((Object[])inParams);
                } else {
                    throw new SearchException("In parameter must be Collection or Arry or String like 1,2,3");
                }
                
                if (inParams instanceof Collection) {
                    Iterator iterator = ((Collection)inParams).iterator();
                    for (int i = 0; iterator.hasNext(); i++) {
                        String inProp = idxProp + "_" + i;
                        if (i < ((Collection)inParams).size() - 1) {
                            where.append(String.format("#{%s},", inProp));
                        } else {
                            where.append(String.format("#{%s}", inProp));
                        }
                        params.put(inProp, iterator.next());
                    }
                }
                where.append(")");
                return pIdx;
            } else {
                where.append(String.format("#{%s}", idxProp));
            }
            
            if (!cc.isUnaryFilter()) {
                params.put(idxProp, formatValue(cc.getOperator(), cc.getValue()));
            }
            return pIdx;
        } else if (condition instanceof OrCondition) {
            
            boolean isFirst = true;
            for (Condition orCondition : ((OrCondition) condition).getOrConditions()) {
                if (!isFirst) {
                    where.append(" or ");
                }
                //OrCondition其实是个集合，需要递归组织语句
                pIdx = genSql(pIdx, orCondition, rms);
                isFirst = false;
            }
            
        } else if (condition instanceof AndCondition) {
            
            boolean isFirst = true;
            for (Condition orCondition : ((AndCondition) condition).getAndConditions()) {
                if (!isFirst) {
                    where.append(" and ");
                }
                //AndCondition其实是个集合，需要递归组织语句
                pIdx = genSql(pIdx, orCondition, rms);
                isFirst = false;
            }
        }
        
        if (needAppendBracket) {
            where.append(")");
        }
        
        return pIdx;
    }
    
    /**
     * 根据参数和ResultMapping，找到对应的column
     * 因为有时候参数和column并不一样，比如参数是parentId，而column是parent_id
     * @param prop
     * @param resultMappings
     * @return
     */
    private String findInResultMapping(String prop, List<ResultMapping> resultMappings) {
        for (ResultMapping rsm : resultMappings) {
            if (prop.equals(rsm.getProperty())) {
                return rsm.getColumn();
            }
        }
        return prop;
    }
    
    /**
     * 生成order by
     */
    private void buildSort() {
        if (searchable.hashSort()) {
            orderby.append(" order by ");
            for (Sort.Order order : searchable.getSort()) {
                orderby.append(String.format("%s %s, ", order.getProperty(), order.getDirection().name().toLowerCase()));
            }
            
            orderby.delete(orderby.length() - 2, orderby.length());
        }
    }
    
    private Object formatValue(SearchOperator operator, Object value) {
        
        if (operator == SearchOperator.like || operator == SearchOperator.notLike) {
            return "%" + value + "%";
        }
        if (operator == SearchOperator.prefixLike || operator == SearchOperator.prefixNotLike) {
            return value + "%";
        }
        
        if (operator == SearchOperator.suffixLike || operator == SearchOperator.suffixNotLike) {
            return "%" + value;
        }
        
        return value;
    }
    
    /*
     * 重新组织prepareStatement需要的语句和参数 
     * 例如：将 select * from tmp where id=#{id} and is_show=#{isShow} 
     * 转换成 select * from tmp where id=? and is_show=? 
     * 同时生产ParameterMappings : [{id,1},{isShow,2}]
     */
    private SqlSource parseSqlSource(String sql) {
        return sssb.parse(sql, params.getClass(), params, mappedStatement.getBoundSql(params).getParameterMappings());
    }
    
    public static enum WitchSql {
        count, paged;
    }
}
