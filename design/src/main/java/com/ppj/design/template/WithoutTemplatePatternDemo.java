package com.ppj.design.template;

/**
 * 默认没有模板模式实现逻辑
 */
public class WithoutTemplatePatternDemo {

    /**
     * 通用逻辑代码会重复写多次, 修改通用逻辑简直是一场灾难
     * @param args
     */
    public static void main(String[] args) {
        new DiscountCalculator1().cal();
        new DiscountCalculator2().cal();
        new DiscountCalculator3().cal();
    }
    public static class DiscountCalculator1 {
        public void cal(){
            System.out.println("通用逻辑");
            System.out.println("计算器1的逻辑");
        }
    }


    public static class DiscountCalculator2 {
        public void cal(){
            System.out.println("通用逻辑");
            System.out.println("计算器2的逻辑");
        }
    }


    public static class DiscountCalculator3 {
        public void cal(){
            System.out.println("通用逻辑");
            System.out.println("计算器3的逻辑");
        }
    }

}
