package com.ppj.notes.hashmap.hit.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 15:11
 * Description:
 */
public class 刀 implements 武器{

    @Override
    public void hit(动物 animal) {
        animal.叫(this);
    }
}
