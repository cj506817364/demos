package com.ppj.design.factory.abs;


/**
 * 默认没有抽象工厂模式实现逻辑
 */
public class WithoutAbstractFactoryPatternDemo {

    public static void main(String[] args) {

        ProductA productA1 = new ProductA1();
        ProductB productB1 = new ProductB1();

        ProductA productA2 = new ProductA2();
        ProductB productB2 = new ProductB2();

        // 如果我需要创建不同的产品组合 会非常麻烦
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

    public static class ProductB1 implements ProductB  {
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


}
