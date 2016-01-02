/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

import com.google.common.collect.Maps;

/**
 * 
 * @author PlayerSun
 * @date Jan 1, 2016
 */
public class SearchableSqlBuilder {
    
    public static final String COUNT = "select count(1) from (%s) as countTable %s";
    public static final String PAGED = "select * from (%s) as pageTable %s %s";
    
    private MappedStatement mappedStatement;
    private Searchable searchable;
    
    private String origin;
    private StringBuffer where = new StringBuffer(" where 1=1 ");
    private StringBuffer orderby = new StringBuffer();

    private Map<String,Object> params = Maps.newHashMap();
    private String countSql;
    private String pagedSql;
    
    public SearchableSqlBuilder(MappedStatement ms, Searchable searchable) {
        this.mappedStatement = ms;
        this.searchable = searchable;
    }
    
    
    public Map<String, Object> getParams() {
        return params;
    }
    
    /**
     * 获得查询总记录数的语句
     * @return
     */
    public String buildCountSql() {
        return build(WitchSql.count);
    }
    
    /**
     * 获得查询分页录的语句
     * @return
     */
    public String buildPagedSql() {
        return build(WitchSql.paged);
    }

    /**
     * 获得哪种查询语句
     * @param ws
     * @return
     */
    public String build(WitchSql ws) {
        if (StringUtils.isEmpty(countSql) ||
            StringUtils.isEmpty(pagedSql)) {
            build();
        }
        switch (ws) {
            case count:
                return countSql;
            case paged:
                return pagedSql;
            default:
                return null;
        }
    }
    
    /**
     * 构建查询语句
     * @return
     */
    public SearchableSqlBuilder build() {
        paramObj2Map();
        buildWhere();
        buildSort();
        origin = mappedStatement.getBoundSql(searchable.getParamObject()).getSql();
        
        countSql = String.format(COUNT, origin, where);
        pagedSql = String.format(PAGED, origin, where, orderby);
        
        return this;
    }
    
    private void paramObj2Map(){
        Object object = searchable.getParamObject();
        if (object instanceof Map) {
            params.putAll((Map)object);;
        } else {
            for(Field field : object.getClass().getFields()){
                try {
                    params.put(field.getName(), field.get(object));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
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
            throw new RuntimeException(
                    "Select in mapper.xml must have at least one ResultMap ! ");
        }
        //其实Mybatis中存在有多个resultmap的情况，这里约定只取第一个。其他的都不管
        List<ResultMapping> rmsResultMapping = list.get(0)
                .getPropertyResultMappings();
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
    
    private int genSql(int pIdx, Condition condition, List<ResultMapping> rms) {
        boolean needAppendBracket = condition instanceof OrCondition ||
                                    condition instanceof AndCondition;
        
        if (needAppendBracket) {
            where.append("(");
        }
        
        if (condition instanceof CommonCondition) {
            CommonCondition cc = (CommonCondition) condition;
            String column = findInResultMapping(cc.getSearchProperty(), rms);
            String idxProp = cc.getSearchProperty() + ++pIdx;
            where.append(column)
                    .append(cc.getOperatorStr()).append(String.format("#{%s}", idxProp));
            params.put(idxProp, cc.getValue());
            return pIdx;
        } else if (condition instanceof OrCondition) {
            
            boolean isFirst = true;
            for (Condition orCondition : ((OrCondition) condition)
                    .getOrConditions()) {
                if (!isFirst) {
                    where.append(" or ");
                }
                pIdx = genSql(pIdx, orCondition, rms);
                isFirst = false;
            }
            
        } else if (condition instanceof AndCondition) {
            
            boolean isFirst = true;
            for (Condition orCondition : ((AndCondition) condition)
                    .getAndConditions()) {
                if (!isFirst) {
                    where.append(" and ");
                }
                pIdx = genSql(pIdx, orCondition, rms);
                isFirst = false;
            }
        }
        
        if (needAppendBracket) {
            where.append(")");
        }
        
        return pIdx;
    }
    
    private String findInResultMapping(String prop,
            List<ResultMapping> resultMappings) {
        for (ResultMapping rsm : resultMappings) {
            if (prop.equals(rsm.getProperty())) {
                return rsm.getColumn();
            }
        }
        return prop;
    }
    
    private void buildSort() {
        if (searchable.hashSort()) {
            orderby.append(" order by ");
            for (Sort.Order order : searchable.getSort()) {
                orderby.append(String.format("%s %s, ", order.getProperty(),
                        order.getDirection().name().toLowerCase()));
            }
            
            orderby.delete(orderby.length() - 2, orderby.length());
        }
    }
    
    public static enum WitchSql {
        count, paged;
    }
}
