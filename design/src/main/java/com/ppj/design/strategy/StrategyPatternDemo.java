package com.ppj.design.strategy;

import java.util.Objects;

/**
 * 策略模式
 *
 * @author pipi
 * @since 2021/4/6 16:48
 */
public class StrategyPatternDemo {

  public static void main(String[] args) {
    Objects.requireNonNull(DisCalStrategyFactory.getDisCalStrategy(DiscountStyle.ONE)).cal();
  }

  public enum DiscountStyle {
    ONE, TWO, THREE;
  }

  // 优惠计价策略
  public interface DisCalStrategy {

    // 计算方式
    void cal();
  }

  public static class DisCalStrategyA implements DisCalStrategy {

    public void cal() {
      System.out.println("优惠计价方式A的逻辑");
    }
  }

  public static class DisCalStrategyB implements DisCalStrategy {

    public void cal() {
      System.out.println("优惠计价方式B的逻辑");
    }
  }

  public static class DisCalStrategyC implements DisCalStrategy {

    public void cal() {
      System.out.println("优惠计价方式C的逻辑");
    }
  }

  public static class DisCalStrategyFactory {

    public static DisCalStrategy getDisCalStrategy(DiscountStyle style) {
      switch (style) {
        case ONE:
          return new DisCalStrategyA();
        case TWO:
          return new DisCalStrategyB();
        case THREE:
          return new DisCalStrategyC();
      }
      return null;
    }
  }
}
