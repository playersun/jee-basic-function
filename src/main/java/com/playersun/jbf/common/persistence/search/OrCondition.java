/**
 * Copyright (c) 2014-2016 https://github.com/playersun
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.playersun.jbf.common.persistence.search;

import java.util.List;

import com.google.common.collect.Lists;


/**
 * 
 * @author PlayerSun
 * @date Dec 29, 2015
 */
public class OrCondition implements Condition {
    private List<Condition> orConditions = Lists.newArrayList();

    OrCondition() {
    }

    public OrCondition add(Condition condition) {
        this.orConditions.add(condition);
        return this;
    }

    public List<Condition> getOrConditions() {
        return orConditions;
    }

    @Override
    public String toString() {
        return "OrCondition{" + orConditions + '}';
    }
}
