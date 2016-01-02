/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.player.sun.jbf.common.persistence.search;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageRequest;
import com.playersun.jbf.common.persistence.search.CommonCondition;
import com.playersun.jbf.common.persistence.search.SearchOperator;
import com.playersun.jbf.common.persistence.search.SearchRequest;
import com.playersun.jbf.common.persistence.search.Searchable;
import com.playersun.jbf.common.persistence.search.Sort;
import com.playersun.jbf.common.persistence.search.exception.InvlidSearchOperatorException;


/**
 * 
 * @author PlayerSun
 * @date Dec 29, 2015
 */
public class SearchableTest {
    @Test
    public void testNewSearchable() {

        Map<String, Object> searchParams1 = Maps.newHashMap();
        searchParams1.put("name_like", "123");
        searchParams1.put("name_like", "234");
        searchParams1.put("age_eq", 1);
        Searchable searchable1 = new SearchRequest(searchParams1);

        Assert.assertTrue(searchable1.containsSearchKey("name_like"));
        Assert.assertTrue(searchable1.containsSearchKey("age_eq"));
        Assert.assertEquals("234", searchable1.getValue("name_like"));
        Assert.assertEquals(1, searchable1.getValue("age_eq"));
        Assert.assertEquals(2, searchable1.getConditions().size());


        Searchable searchable2 = new SearchRequest(
                null,
                new PageRequest(0, 1),
                new Sort(Sort.Direction.DESC, "uuid"));

        Assert.assertTrue(searchable2.hasPageable());
        Assert.assertTrue(searchable2.hashSort());

        Searchable searchable3 = new SearchRequest(
                null,
                new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "uuid")));

        Assert.assertTrue(searchable3.hasPageable());
        Assert.assertTrue(searchable3.hashSort());

    }
    
    @Test(expected = InvlidSearchOperatorException.class)
    public void testNewSearchableWithErrorOperator() {

        Map<String, Object> searchParams1 = Maps.newHashMap();
        searchParams1.put("name_lik", "123");

        new SearchRequest(searchParams1);

    }
    
    @Test
    public void testAddParam(){
        Searchable searchable = new SearchRequest();
        searchable.addSearchParam("id_eq", 2);
        searchable.addSearchParam("bc_like", "23");
        searchable.addSearchParam("name", "iverson");
        
        Assert.assertTrue(searchable.containsSearchKey("id_eq"));
        Assert.assertTrue(searchable.containsSearchKey("name"));
        Assert.assertTrue(searchable.containsSearchKey("bc_like"));
    }
    
    @Test
    public void testAddAllParams() {
        Map<String, Object> searchParams1 = Maps.newHashMap();
        searchParams1.put("name_like", "123");
        searchParams1.put("name_like", "234");
        searchParams1.put("age_eq", 1);
        
        Searchable searchable = new SearchRequest();
        searchable.addSearchParams(searchParams1);
        searchable.addSearchParam("bc_like", "23");
        
        Assert.assertEquals(3, searchable.getConditions().size());

        Assert.assertTrue(searchable.containsSearchKey("name_like"));
        Assert.assertTrue(searchable.containsSearchKey("age_eq"));
        Assert.assertTrue(searchable.containsSearchKey("bc_like"));
    }
    
    @Test
    public void testAddSearchConditon() {

        Searchable searchable = new SearchRequest();

        searchable.addCondition("age", SearchOperator.like, "123");
        searchable.addCondition("name", SearchOperator.custom, "234");

        searchable.addCondition(CommonCondition.newCondition("sex_like", "male"));
        searchable.addCondition(CommonCondition.newCondition("birthday_custom", "2012"));
        searchable.addCondition(CommonCondition.newCondition("realname", "123"));

        searchable.addCondition(CommonCondition.newCondition("a", SearchOperator.eq, "234"));
        searchable.addCondition(CommonCondition.newCondition("b", SearchOperator.custom, "234"));


        Assert.assertTrue(searchable.containsSearchKey("age_like"));
        Assert.assertTrue(searchable.containsSearchKey("name"));
        Assert.assertTrue(searchable.containsSearchKey("name_custom"));

        Assert.assertTrue(searchable.containsSearchKey("sex_like"));
        Assert.assertTrue(searchable.containsSearchKey("birthday"));
        Assert.assertTrue(searchable.containsSearchKey("birthday_custom"));
        Assert.assertTrue(searchable.containsSearchKey("realname"));
        Assert.assertTrue(searchable.containsSearchKey("realname_eq"));

        Assert.assertTrue(searchable.containsSearchKey("a_eq"));
        Assert.assertTrue(searchable.containsSearchKey("b_custom"));
        Assert.assertTrue(searchable.containsSearchKey("b"));

    }
    
    @Test
    public void testAddSearchConditons() {

        Searchable searchable = new SearchRequest();

        searchable.addConditions(Lists.newArrayList(
                CommonCondition.newCondition("sex_like", "male"),
                CommonCondition.newCondition("birthday_custom", "2012"),
                CommonCondition.newCondition("realname", "123")
        ));


        Assert.assertTrue(searchable.containsSearchKey("sex_like"));
        Assert.assertTrue(searchable.containsSearchKey("birthday"));
        Assert.assertTrue(searchable.containsSearchKey("birthday_custom"));
        Assert.assertTrue(searchable.containsSearchKey("realname"));
        Assert.assertTrue(searchable.containsSearchKey("realname_eq"));


    }
    
    @Test
    public void testOrSearchConditons() {

        Searchable searchable = new SearchRequest();

        searchable.or(
                CommonCondition.newCondition("sex_like", "male"),
                CommonCondition.newCondition("birthday_custom", "2012"),
                CommonCondition.newCondition("realname", "123")
        );


        Assert.assertEquals(1, searchable.getConditions().size());

        Assert.assertTrue(searchable.containsSearchKey("sex_like"));

        Assert.assertTrue(searchable.containsSearchKey("realname"));


    }
    
    
    @Test
    public void testAndSearchConditons() {

        Searchable searchable = new SearchRequest();

        searchable.and(
                CommonCondition.newCondition("sex_like", "male"),
                CommonCondition.newCondition("birthday_custom", "2012"),
                CommonCondition.newCondition("realname", "123")
        );


        Assert.assertEquals(1, searchable.getConditions().size());

        Assert.assertTrue(searchable.containsSearchKey("sex_like"));

        Assert.assertTrue(searchable.containsSearchKey("realname"));


    }
    
    
    @Test
    public void testRemoveSearchFilter() {

        Searchable searchable = new SearchRequest();

        searchable.addSearchParam("a", "123");
        searchable.addSearchParam("b", "123");
        searchable.addSearchParam("c", "123");

        searchable.or(
                CommonCondition.newCondition("sex_like", "male"),
                CommonCondition.newCondition("birthday_custom", "2012"),
                CommonCondition.newCondition("realname", "123"),
                CommonCondition.newCondition("name_custom", "123"),
                CommonCondition.newCondition("age_eq", 1)
        );


        searchable.removeCondition("a");
        searchable.removeCondition("b_eq");
        searchable.removeCondition("sex_like");
        searchable.removeCondition("realname_custom");
        searchable.removeCondition("name");

        Assert.assertEquals(2, searchable.getConditions().size());
    }
    
    @Test
    public void testPageAndSort() {
        Searchable searchable = new SearchRequest();
        searchable.setPage(0, 10);

        searchable.addSort(Sort.Direction.DESC, "uuid");
        searchable.addSort(Sort.Direction.ASC, "name");


        Assert.assertEquals(0, searchable.getPage().getPageNumber());
        Assert.assertEquals(10, searchable.getPage().getPageSize());

        Assert.assertEquals("name", searchable.getPage().getSort().iterator().next().getProperty());

        Assert.assertEquals("name", searchable.getSort().iterator().next().getProperty());

    }
}
