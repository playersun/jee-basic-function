/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageMybatis;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageRequest;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.CommonCondition;
import com.playersun.jbf.common.persistence.search.SearchOperator;
import com.playersun.jbf.common.persistence.search.SearchRequest;
import com.playersun.jbf.common.persistence.search.Searchable;
import com.playersun.jbf.common.persistence.search.Sort;
import com.playersun.jbf.common.persistence.search.Sort.Direction;
import com.playersun.jbf.modules.sys.entity.Resource;
import com.playersun.jbf.modules.sys.service.ResourceService;

/**
 * 
 * @author PlayerSun
 * @date Nov 14, 2015
 */
public class ResourceTester extends BaseTest {
    
    @Autowired
    private ResourceService resourceService;
    
    @Test
    public void addResource() {
        Resource resource = new Resource();
        
        Date date = new Date();
        
        resource.setName("我是资源");
        resource.setParentId(1L);
        resource.setParentIds("0/1/");
        resource.setIdentification("imResource");
        resource.setUrl("/admin/sys/imResource");
        resource.setWeight(1);
        
        resource.setCreateBy(1L);
        resource.setCreateDate(date);
        resource.setUpdateBy(1L);
        resource.setUpdateDate(date);
        resource.setDeleted(false);
        
        resourceService.insert(resource);
    }
    
    @Test
    public void getResourceById() {
        Resource resource = resourceService.findById(1L);
        System.out.println(resource);
    }
    
    @Test
    public void getResourceList() {
        List<Resource> list = resourceService.findList((Pageable) null);
        for (Resource resource : list) {
            System.out.println(resource);
        }
    }
    
    @Test
    public void delete() {
        Resource resource = resourceService.findById(1L);
        System.out.println(resourceService.delete(resource));
    }
    
    @Test
    public void deleteById() {
        System.out.println(resourceService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch() {
        List<Resource> list = resourceService.findList((Pageable) null);
        
        System.out.println(resourceService.deleteBatch(list));
    }
    
    @Test
    public void updateResource() {
        Resource resource = resourceService.findById(1L);
        resource.setRemarks("test update once again");
        System.out.println(resourceService.update(resource));
    }
    
    @Test
    public void findPage() {
        
        //        Resource resourc = resourceService.findById(1L);
        //        
        //        Map<String, Object> map = new HashMap<String, Object>();
        //        map.put("deleted", 0);
        //        map.put("parentId", 2);
        
        Resource r = new Resource();
        r.setParentId(1L);
        r.setDeleted(false);
        
        Pageable pageable = new PageRequest(6, 3, r, new Sort(Direction.DESC, "id"));
        PageMybatis<Resource> p = resourceService.findList(pageable);
        System.out.print(p);
    }
    
    @Test
    public void findResourceWithOrAnd() {
        Searchable searchable = new SearchRequest();
        
        Map<Object, Object> m1 = Maps.newHashMap();
        m1.put("deleted", 0);
        
        Pageable pageable = new PageRequest(2, 3, m1, new Sort(Direction.DESC, "id"));
        Map<Object, Object> m2 = Maps.newHashMap();
        m2.put("parentId", 2);
        
        searchable.setPage(pageable).setParamObject(m2).addSearchParam("isShow", 1);
        
        searchable.or(CommonCondition.newCondition("id_lte", 5), CommonCondition.newCondition("id_gt", 7));
        searchable.and(CommonCondition.newCondition("weight", SearchOperator.ne, 8),
                CommonCondition.newCondition("weight", SearchOperator.gte, 2));
        
        PageMybatis<Resource> p = resourceService.findList(searchable);
    }
    
    @Test
    public void findResourceWithLike() {
        Searchable searchable = new SearchRequest();
        
        searchable.addSearchParam("deleted", 0);
        
        searchable.or(CommonCondition.newCondition("url_like", "monitor"),
                CommonCondition.newCondition("identification", SearchOperator.prefixLike, "im"));
        
        PageMybatis<Resource> p = resourceService.findList(searchable);
    }
    
    @Test
    public void findResourceIN(){
        Searchable searchable = new SearchRequest();
        
        int [] ids = {1,2,3,4,5};
        
        String str_ids = "1,2,3,4,5";
        
        searchable.addSearchParam("id_in", str_ids);
        
        PageMybatis<Resource> p = resourceService.findList(searchable);
    }
}
