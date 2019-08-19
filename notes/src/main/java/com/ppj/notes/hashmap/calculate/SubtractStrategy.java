package com.ppj.notes.hashmap.calculate;

import com.ppj.notes.hashmap.calculate.interfaces.Strategy;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 15:19
 * Description:
 */
public class SubtractStrategy implements Strategy {
    @Override
    public int calc(int num1, int num2) {
        return num1 - num2;
    }
}
