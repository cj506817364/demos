package com.ppj.design.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 不使用构造器模式
 * @author
 * @since 2021/4/4 15:29
 */
public class WithoutBuilderPatternDemo {

    public static void main(String[] args) {
        Product product = new Product();
        System.out.println("设置field1前置逻辑");
        product.setField1("f1");
        System.out.println("设置field2前置逻辑");
        product.setField2("f2");
        System.out.println("设置field3前置逻辑");
        product.setField3("f3");
        System.out.println(product);
    }

    @Getter
    @Setter
    @ToString
    public static class Product {
        private String field1;
        private String field2;
        private String field3;
    }
}
