package com.ppj.design.factory.method;

/**
 * @author ppj
 * @since 2021/4/4 14:18
 */
public class WithoutFactoryMethodPatternDemo {

    // 通用逻辑未抽取出来, 如果要修改通用逻辑 则很麻烦
    public static void main(String[] args) {
        ProductFactory.createProduct1().exec();
        ProductFactory.createProduct2().exec();
        ProductFactory.createProduct3().exec();
    }

    public interface Product {
        void exec();
    }

    public static class ProductImpl1 implements Product {
        @Override
        public void exec() {
            System.out.println("产品1");
        }
    }

    public static class ProductImpl2 implements Product {
        @Override
        public void exec() {
            System.out.println("产品2");
        }
    }

    public static class ProductImpl3 implements Product {
        @Override
        public void exec() {
            System.out.println("产品3");
        }
    }

    public static class ProductFactory {

        public static Product createProduct1(){
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品1的特殊逻辑");
            return new ProductImpl1();
        }

        public static Product createProduct2(){
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品2的特殊逻辑");
            return new ProductImpl2();
        }

        public static Product createProduct3(){
            System.out.println("生产产品的通用逻辑");
            System.out.println("生产产品3的特殊逻辑");
            return new ProductImpl3();
        }

    }
}
