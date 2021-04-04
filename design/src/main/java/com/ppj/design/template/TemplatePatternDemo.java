package com.ppj.design.template;

/**
 * 模板模式简单实现
 * @author ppj
 * @since 2021/4/4
 */
public class TemplatePatternDemo {

    public static void main(String[] args) {
        new DiscountCalculator1().cal();
        new DiscountCalculator2().cal();
        new DiscountCalculator3().cal();
    }

    public interface DiscountCalculator {
        void cal();
    }

    public static abstract class AbstractDiscountCalculator implements DiscountCalculator {

        @Override
        public void cal() {
            commonCal();
            specialCal();
        }

        private void commonCal() {
            System.out.println("通用逻辑");
        }

        protected abstract void specialCal();
    }

    public static class DiscountCalculator1 extends AbstractDiscountCalculator{

        @Override
        protected void specialCal() {
            System.out.println("计算器1的逻辑");
        }
    }


    public static class DiscountCalculator2 extends AbstractDiscountCalculator {
        @Override
        protected void specialCal() {
            System.out.println("计算器2的逻辑");
        }
    }


    public static class DiscountCalculator3 extends AbstractDiscountCalculator {
        @Override
        protected void specialCal() {
            System.out.println("计算器3的逻辑");
        }
    }
}
