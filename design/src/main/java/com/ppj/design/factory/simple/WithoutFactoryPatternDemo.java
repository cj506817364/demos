package com.ppj.design.factory.simple;

import lombok.Getter;

/**
 * 默认没有简单工厂模式实现逻辑
 */
public class WithoutFactoryPatternDemo {

    /**
     * 在修改Product这个类的时候， 会导致重复修改大量引用代码， 可以采用工厂模式来屏蔽掉Product类的构造
     * @param args
     */
    public static void main(String[] args) {
        Product product = new Product("测试产品1");
        System.out.println(product);
    }

    @Getter
    public static class Product {
        private String name;

        public Product(String name) {
            this.name = name;
        }
    }
}
