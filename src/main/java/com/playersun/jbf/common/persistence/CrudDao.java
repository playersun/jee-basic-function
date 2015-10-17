/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence;

import com.playersun.jbf.common.persistence.pagination.PageMybatis;
import com.playersun.jbf.common.persistence.pagination.Pageable;

/**
 * <p>
 * 抽象DAO层基类 提供一些简便方法<br />
 * 参考spring data jpa中CrudRepository{@see
 * org.springframework.data.repository.CrudRepository}
 * </p>
 * 
 * @author PlayerSun
 * @date Jun 10, 2015
 */
public interface CrudDao<T> extends BaseDao<T> {
    
    /**
     * 根据id获得实体
     * 
     * @param id
     * @return
     */
    public T findById(Long id);
    
    /**
     * 根据分页参数，获得分页的结果
     * @param pageable
     * @return
     */
    public PageMybatis<T> findList(Pageable pageable);
    
    /**
     * 保存实体到数据库
     * 
     * @param entity
     */
    public int insert(T entity);
    
    /**
     * 更新实体到数据库
     * 
     * @param entity
     * @return
     */
    public int update(T entity);
    
    /**
     * 根据ID删除数据库中实体
     * 
     * @param id
     * @return
     */
    public int delete(Long id);
    
    /**
     * 删除数据库中实体
     * 
     * @param entity
     * @return
     */
    public int delete(T entity);
    
    /**
     * 删除所有
     * 
     * @return
     */
    public int delteAll();
}
