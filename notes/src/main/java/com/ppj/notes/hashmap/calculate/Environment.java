package com.ppj.notes.hashmap.calculate;

import com.ppj.notes.hashmap.calculate.interfaces.Strategy;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 15:19
 * Description:
 */
public class Environment {

    private Strategy strategy;

    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calulate(int a,int b){
        return strategy.calc(a,b);
    }
}
