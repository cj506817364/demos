package com.ppj.design.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 构造器模式
 * @author
 * @since 2021/4/4 15:29
 */
public class BuilderPatternDemo {

    public static void main(String[] args) {
        Product product = new Director<Product>(new ProductBuilder()).build("f1", "f2", "f3");
        System.out.println(product);
    }

    public static class Director<T> {
        private Builder<T>builder;

        public Director(Builder<T> builder) {
            this.builder = builder;
        }

        public T build(String f1, String f2, String f3) {
            return (T) builder.field1(f1).field2(f2).field3(f3).create();
        }
    }

    public interface Builder<T> {
        Builder field1(String f1);

        Builder field2(String f2);

        Builder field3(String f3);

        T create();

    }

    public static class ProductBuilder implements Builder<Product> {

        private Product product = new Product();

        @Override
        public Builder field1(String f1) {
            System.out.println("设置field1前置逻辑");
            product.setField1(f1);
            return this;
        }

        @Override
        public Builder field2(String f2) {
            System.out.println("设置field2前置逻辑");
            product.setField2(f2);
            return this;
        }

        @Override
        public Builder field3(String f3) {
            System.out.println("设置field3前置逻辑");
            product.setField3(f3);
            return this;
        }

        @Override
        public Product create() {
            return product;
        }
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
