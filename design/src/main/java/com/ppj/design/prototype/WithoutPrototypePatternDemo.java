package com.ppj.design.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 原型模式
 * @author ppj
 * @since 2021/4/5 10:34
 */
public class WithoutPrototypePatternDemo {

    /**
     * 拷贝逻辑暴露在调用方, 如果属性拷贝逻辑被改变 则需要大量修改实行赋值方法的
     * @param args
     */
    public static void main(String[] args) {
        Product product = new Product("测试产品", new Component("测试组件"));

        // 不使用原型模式 需要手动拷贝属性
        System.out.println(new Product(product.getName(),product.getComponent()));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Component {
        private String name;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Product {
        private String name;
        private Component component;
    }


}
