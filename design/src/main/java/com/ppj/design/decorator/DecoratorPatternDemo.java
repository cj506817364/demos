package com.ppj.design.decorator;

import lombok.AllArgsConstructor;

/**
 * 装饰器模式
 *
 * @author pipi
 * @since 2021/4/6 18:52
 */
public class DecoratorPatternDemo {

  public static void main(String[] args) {
    final ConcreteComponent concreteComponent = new ConcreteComponent();

    new Decorator(new ConcreteComponent()).exec();
  }

  public interface Component {

    void exec();
  }

  public static class ConcreteComponent implements Component {

    @Override
    public void exec() {
      System.out.println("执行基础功能");
    }
  }

  @AllArgsConstructor
  public static class Decorator implements Component {

    private Component component;

    @Override
    public void exec() {
      System.out.println("前置方法");
      component.exec();
      System.out.println("后置方法");
    }
  }
}
