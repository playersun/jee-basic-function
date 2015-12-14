/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.modules.sys.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.playersun.jbf.common.service.CrudService;
import com.playersun.jbf.modules.sys.dao.GroupDao;
import com.playersun.jbf.modules.sys.entity.Group;


/**
 * @author PlayerSun
 * @date Nov 15, 2015
 */
@Service
public class GroupService extends CrudService<Group> {
    @Autowired
    private GroupRelationService groupRelationService;

    private GroupDao getGroupRepository() {
        return (GroupDao) crudDao;
    }
    
    /**
     * 获取可用的的分组编号列表
     *
     * @param userId
     * @param organizationIds
     * @return
     */
    public Set<Long> findShowGroupIds(Long userId, Set<Long> organizationIds) {
        Set<Long> groupIds = Sets.newHashSet();
        groupIds.addAll(getGroupRepository().findDefaultGroupIds());
        groupIds.addAll(groupRelationService.findGroupIds(userId, organizationIds));


        //TODO 如果分组数量很多 建议此处查询时直接带着是否可用的标识去查
        for (Group group : this.findList(null)) {
            if (Boolean.FALSE.equals(group.getIsShow())) {
                groupIds.remove(group.getId());
            }
        }

        return groupIds;
    }
}
