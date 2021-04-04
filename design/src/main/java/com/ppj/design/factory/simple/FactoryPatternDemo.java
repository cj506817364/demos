package com.ppj.design.factory.simple;

/**
 * 简单工厂模式简单实现
 * @author ppj
 * @since 2021-04-04 13:51:59
 */
public class FactoryPatternDemo {

    public static void main(String[] args) {
        Product product = ProductFactory.create();
        product.exec();
    }

    public interface Product {
        void exec();
    }

    public static class ProductImpl1 implements Product {

        @Override
        public void exec() {
            System.out.println("产品1实现");
        }
    }

    public static class ProductFactory {

        public static Product create() {
            return new ProductImpl1();
        }
    }
}
