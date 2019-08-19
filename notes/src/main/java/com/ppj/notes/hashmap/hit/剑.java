package com.ppj.notes.hashmap.hit;

import com.ppj.notes.hashmap.hit.interfaces.动物;
import com.ppj.notes.hashmap.hit.interfaces.武器;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 14:19
 * Description:
 */
public class 剑 implements 武器 {

    @Override
    public void hit(动物 animal) {
        animal.叫(this);
    }

}
