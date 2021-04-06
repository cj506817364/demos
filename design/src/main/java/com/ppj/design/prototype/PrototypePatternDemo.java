package com.ppj.design.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 浅拷贝, 子对象还是原来的对象
 * 深拷贝, 子对象是被拷贝的对象
 *
 * @author
 * @since 2021/4/5 10:37
 */
public class PrototypePatternDemo {

    public static void main(String[] args) {
        Product product = new Product("测试产品", new Component("测试组件"));

        // 不使用原型模式 需要手动拷贝属性
        System.out.println(product.clone().equals(product));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Component {
        private String name;

        @Override
        protected Component clone() {
            return new Component(name);
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Product {
        private String name;
        private Component component;

        @Override
        protected Product clone() {
            // 浅拷贝
//            return new Product(getName(), getComponent());
            // 深拷贝
            return new Product(getName(), component == null ? null : component.clone());
        }
    }
}
