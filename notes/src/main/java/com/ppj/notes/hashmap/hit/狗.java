package com.ppj.notes.hashmap.hit;

import com.ppj.notes.hashmap.hit.interfaces.动物;
import com.ppj.notes.hashmap.hit.interfaces.武器;
import com.ppj.notes.hashmap.hit.interfaces.策略;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 14:21
 * Description:
 */
public class 狗 implements 动物 {

    private 策略 策略;

    public 狗(com.ppj.notes.hashmap.hit.interfaces.策略 策略) {
        this.策略 = 策略;
    }

    @Override
    public void 叫(武器 武器) {
        策略.叫();
    }
}
