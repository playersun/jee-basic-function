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
public class AndCondition implements Condition {
    private List<Condition> andConditions = Lists.newArrayList();

    AndCondition() {
    }

    public AndCondition add(Condition condition) {
        this.andConditions.add(condition);
        return this;
    }

    public List<Condition> getAndConditions() {
        return andConditions;
    }

    @Override
    public String toString() {
        return "AndCondition{" + andConditions + '}';
    }
}
