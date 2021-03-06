/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.persistence.CrudDao;
import com.playersun.jbf.common.persistence.mybatis.pagination.PageMybatis;
import com.playersun.jbf.common.persistence.pagination.Pageable;
import com.playersun.jbf.common.persistence.search.Searchable;

/**
 * 抽象service，通过CrudDao完成数据库操作
 * 
 * @author PlayerSun
 * @date Sep 20, 2015
 */
public class CrudService<T> {
    
    @Autowired
    protected CrudDao<T> crudDao;
    
    /**
     * 根据ID获得实体
     * 
     * @param id
     * @return
     */
    public T findById(Long id) {
        return crudDao.findById(id);
    }
    
    /**
     * 查询所有
     * @return
     
    public List<T> findAll(){
        return crudDao.findAll();
    }*/
    
    public PageMybatis<T> findList(Searchable searchable){
        return crudDao.findList(searchable);
    }
    
    /**
     * 根据分页参数，获得分页的结果
     * 
     * @param pageable
     * @return
     */
    public PageMybatis<T> findList(Pageable pageable) {
        return crudDao.findList(pageable);
    }
    
    /**
     * 保持实体得到数据库
     * 
     * @param entity
     *            需要保持的实体
     * @return 返回影响行数
     */
    public int insert(T entity) {
        return crudDao.insert(entity);
    }
    
    /**
     * 更新单个实体
     * @param entity
     * @return
     */
    public int update(T entity) {
        return crudDao.update(entity);
    }
    
    /**
     * 根据ID删除数据库中实体
     * 
     * @param id
     * @return
     */
    public int deleteById(Long id){
        return crudDao.deleteById(id);
    }
    
    /**
     * 批量删除
     * @param list
     * @return
     */
    public int deleteBatch(List<T> list){
        return crudDao.deleteBatch(list);
    }
    
    /**
     * 删除数据库中实体
     * 
     * @param id
     * @return
     */
    public int delete(T entity){
        return crudDao.delete(entity);
    }
    
    /**
     * 删除所有
     * 
     * @return
     */
    public int delteAll(){
        return crudDao.delteAll();
    }
}
