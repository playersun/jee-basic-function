/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.playersun.jbf.common.BaseTest;
import com.playersun.jbf.modules.sys.entity.Job;
import com.playersun.jbf.modules.sys.service.JobService;


/**
 * 
 * @author PlayerSun
 * @date Nov 22, 2015
 */
public class JobTester extends BaseTest {
    
    @Autowired
    private JobService jobService;
    
    @Test
    public void addJob(){
        Job job = new Job();
        Date date = new Date();
        
        job.setName("财务经理");
        job.setParentId(0L);
        job.setParentIds("0/1/");
        job.setWeight(1);
        job.setIsShow(Boolean.TRUE);
        
        job.setCreateBy(1L);
        job.setCreateDate(date);
        job.setUpdateBy(1L);
        job.setUpdateDate(date);
        job.setDeleted(false);
        
        jobService.insert(job);
    }
    
    @Test
    public void getJobById(){
        Job job = jobService.findById(1L);
        System.out.println(job);
    }
    
    @Test
    public void getJobList(){
        List<Job> list = jobService.findList(null);
        for (Job job : list) {
            System.out.println(job);
        }
    }
    
    @Test
    public void delete(){
        Job job = jobService.findById(11L);
        System.out.println(jobService.delete(job));
    }
    
    @Test
    public void deleteById(){
        System.out.println(jobService.deleteById(2L));
    }
    
    @Test
    public void deleteBatch(){
        List<Job> list = jobService.findList(null);
        
        System.out.println(jobService.deleteBatch(list));
    }
    
    
    @Test
    public void updateJob() {
        Job job = jobService.findById(1L);
        job.setRemarks("test update once again");
        System.out.println(jobService.update(job));
    }
}
