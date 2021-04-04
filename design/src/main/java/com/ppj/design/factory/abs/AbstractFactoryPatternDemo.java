package com.ppj.design.factory.abs;

/**
 * 通过抽象工程的方式 有多个不同的工厂实现 来创建不同的对象组合
 * @author ppj
 * @since 2021/4/4 14:46
 */
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {
        ProductA productA1 = Factory1.get().createProductA();
        ProductB productB1 = Factory1.get().createProductB();

        ProductA productA2 = Factory2.get().createProductA();
        ProductB productB2 = Factory2.get().createProductB();
    }


    public interface ProductA {
        void exec();
    }

    public interface ProductB {
        void exec();
    }

    public static class ProductA1 implements ProductA {
        public void exec(){
            System.out.println("产品A1");
        }
    }

    public static class ProductA2 implements ProductA {
        public void exec(){
            System.out.println("产品A2");
        }
    }

    public static class ProductA3 implements ProductA {
        public void exec(){
            System.out.println("产品A3");
        }
    }

    public static class ProductB1 implements ProductB {
        public void exec(){
            System.out.println("产品B1");
        }
    }

    public static class ProductB2 implements ProductB {
        public void exec(){
            System.out.println("产品B2");
        }
    }

    public static class ProductB3 implements ProductB {
        public void exec(){
            System.out.println("产品B3");
        }
    }

    public interface Factory {
        ProductA createProductA();
        ProductB createProductB();
    }

    public static class Factory1 implements Factory {

        private static final Factory factory = new Factory1();

        private Factory1() {
        }

        public static Factory get(){
            return factory;
        }

        @Override
        public ProductA createProductA() {
            return new ProductA1();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB1();
        }
    }

    public static class Factory2 implements Factory {

        private static final Factory factory = new Factory2();

        private Factory2() {
        }

        public static Factory get(){
            return factory;
        }

        @Override
        public ProductA createProductA() {
            return new ProductA2();
        }

        @Override
        public ProductB createProductB() {
            return new ProductB2();
        }
    }
}
