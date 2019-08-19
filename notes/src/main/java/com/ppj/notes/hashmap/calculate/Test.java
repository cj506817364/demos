package com.ppj.notes.hashmap.calculate;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-04-01 15:22
 * Description:
 */
public class Test {

    public static void main(String[] args) {

        Environment environment = new Environment(new AddStrategy());
        int result = environment.calulate(1,2);
        System.out.println(result);
    }
}
