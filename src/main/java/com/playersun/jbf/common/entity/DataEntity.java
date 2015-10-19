/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.entity;

import java.util.Date;

/**
 * 
 * @author PlayerSun
 * @date Aug 9, 2015
 */
public class DataEntity<T> extends BaseEntity<T> {
    
    private static final long serialVersionUID = 3237337207884624331L;
    
    protected Long createBy; //谁新建的
    protected Date createDate; //创建日期
    protected Long updateBy; //被谁更新
    protected Date updateDate; //更新时间
    protected String remarks; //备注
    protected Boolean deleted; //是否已经删除。0表示没有，1表示删除。不删除数据。
    
    public Long getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Long getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
