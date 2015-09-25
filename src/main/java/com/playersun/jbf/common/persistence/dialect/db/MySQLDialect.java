/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package com.playersun.jbf.common.persistence.dialect.db;

import com.playersun.jbf.common.persistence.dialect.Dialect;

/**
 * Mysql方言的实现
 * 
 * @author poplar.yfyang
 * @version 1.0 2010-10-10 下午12:31
 * @since JDK 1.5
 */
public class MySQLDialect implements Dialect {
    
    @Override
    public String getLimitString(String sql, int offset, int limit, int count) {
        /* 这里要判断下offset，因为offset是根据分页请求参数中(pageNumber - 1) * this.pageSize计算得出
         * 然后分页插件会先查询出总数据条数count，如果offset比count还要大，那后面的分页sql语句的结果就会出错
         * 所以这里要判断offset，如果比count大，就要取最后一页的offset
         */
        if (offset >= count) {
            offset = ((int) Math.ceil((double) count / (double) limit) - 1) *
                     limit;
        }
        return getLimitString(sql, offset, Integer.toString(offset),
                Integer.toString(limit));
    }
    
    public boolean supportsLimit() {
        return true;
    }
    
    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符号(placeholder)替换.
     * 
     * <pre>
     * 如mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
     * select * from user limit :offset,:limit
     * </pre>
     * 
     * @param sql
     *            实际SQL语句
     * @param offset
     *            分页开始纪录条数
     * @param offsetPlaceholder
     *            分页开始纪录条数－占位符号
     * @param limitPlaceholder
     *            分页纪录条数占位符号
     * @return 包含占位符的分页sql
     */
    public String getLimitString(String sql, int offset,
            String offsetPlaceholder, String limitPlaceholder) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append(" limit ");
        if (offset > 0) {
            stringBuilder.append(offsetPlaceholder).append(",")
                    .append(limitPlaceholder);
        } else {
            stringBuilder.append(limitPlaceholder);
        }
        return stringBuilder.toString();
    }
    
}
