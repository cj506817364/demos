package com.ppj.design.factory.method;

/**
 * @author ppj
 * @since 2021/4/4 14:22
 */
public class FactoryMethodPatternDemo {

    public static void main(String[] args) {
        Product1Factory.get().createProduct().exec();
        Product2Factory.get().createProduct().exec();
        Product3Factory.get().createProduct().exec();
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

    public static abstract class AbstractProductFactory {

        public Product createProduct() {
            commonCreate();
            return specialCreate();
        }

        public void commonCreate() {
            System.out.println("生产产品的通用逻辑");
        }

        protected abstract Product specialCreate();
    }

    public static class Product1Factory extends AbstractProductFactory {

        private static final AbstractProductFactory factory = new Product1Factory();

        private Product1Factory() {

        }
        public static AbstractProductFactory get() {
            return factory;
        }

        @Override
        protected Product specialCreate() {
            System.out.println("生产产品1的特殊逻辑");
            return new ProductImpl1();
        }
    }

    public static class Product2Factory extends AbstractProductFactory {

        private static final AbstractProductFactory factory = new Product2Factory();

        private Product2Factory() {

        }
        public static AbstractProductFactory get() {
            return factory;
        }

        @Override
        protected Product specialCreate() {
            System.out.println("生产产品2的特殊逻辑");
            return new ProductImpl2();
        }
    }

    public static class Product3Factory extends AbstractProductFactory {

        private static final AbstractProductFactory factory = new Product3Factory();

        private Product3Factory() {

        }
        public static AbstractProductFactory get() {
            return factory;
        }

        @Override
        protected Product specialCreate() {
            System.out.println("生产产品3的特殊逻辑");
            return new ProductImpl3();
        }
    }
}
