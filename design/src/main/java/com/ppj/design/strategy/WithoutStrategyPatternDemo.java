package com.ppj.design.strategy;

import cn.hutool.core.lang.Console;

/**
 * 不用策略模式
 *
 * @author pipi
 * @since 2021/4/6 16:49
 */
public class WithoutStrategyPatternDemo {

  public static void main(String[] args) {
    // 有一个参数 discountStyle 决定了优惠计价方式
    DiscountStyle style = Integer.valueOf("1").equals(1) ? DiscountStyle.TWO : DiscountStyle.ONE;
    switch (style) {
      case ONE:
        Console.log("style: {}", DiscountStyle.ONE);
        break;
      case TWO:
        Console.log("style: {}", DiscountStyle.TWO);
        break;
      case THREE:
        Console.log("style: {}", DiscountStyle.THREE);
        break;
    }
  }

  public enum DiscountStyle {
    ONE, TWO, THREE;
  }

}
