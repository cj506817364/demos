package com.ppj.notes.hashmap;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-09 15:48
 * Description:
 */

public class TestKey {

    private String name;

    @Override
    public int hashCode() {
        return 123;
    }

    public TestKey(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
