/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.utils.sql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 去除SQL多余的无用的工具帮助类.
 * </p>
 * 
 * @author poplar.yfyang
 * @version 1.0 2013-01-02 12:37 PM
 * @since JDK 1.5
 */
public class SqlUtil {
    
    /** Order by 正则表达式 */
    public static final String ORDER_BY_REGEX = "order\\s*by[\\w|\\W|\\s|\\S]*";
    /** Xsql Order by 正则表达式 */
    public static final String XSQL_ORDER_BY_REGEX = "/~.*order\\s*by[\\w|\\W|\\s|\\S]*~/";
    /** From正则表达式 */
    public static final String FROM_REGEX = "\\sfrom\\s";
    
    /** sql contains whre regex. */
    public static final String WHERE_REGEX = "\\s+where\\s+";
    /** sql contains <code>order by </code> regex. */
    public static final String ORDER_REGEX = "order\\s+by";
    
    public static final String ORDER_BY_STR = "order by";
    
    public static boolean containOrder(String sql) {
        return containRegex(sql, ORDER_REGEX);
    }
    
    public static boolean containWhere(String sql) {
        return containRegex(sql, WHERE_REGEX);
    }
    
    public static boolean containRegex(String sql, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        return matcher.find();
    }
    
    private static int indexOfByRegex(String input, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.find()) {
            return m.start();
        }
        return -1;
    }
    
    /**
     * 去除select 子句，未考虑union的情况
     * 
     * @param sql
     *            sql
     * @return 删除掉的selcet的子句
     */
    public static String removeSelect(String sql) {
        int beginPos = indexOfByRegex(sql.toLowerCase(), FROM_REGEX);
        return sql.substring(beginPos);
    }
    
    /**
     * 去除orderby 子句
     * 
     * @param sql
     *            sql
     * @return 去掉order by sql
     */
    public static String removeOrders(String sql) {
        Pattern p = Pattern.compile(ORDER_BY_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    
    public static String removeFetchKeyword(String sql) {
        return sql.replaceAll("(?i)fetch", "");
    }
    
    public static String removeXsqlBuilderOrders(String string) {
        Pattern p = Pattern.compile(XSQL_ORDER_BY_REGEX,
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return removeOrders(sb.toString());
    }
}
