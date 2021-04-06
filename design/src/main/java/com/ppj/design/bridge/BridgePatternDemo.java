package com.ppj.design.bridge;

import lombok.AllArgsConstructor;

/**
 * 桥接模式
 *
 * 一个代码组件 基于另一个代码组件的接口去调用, 那个这个接口 就相当于一个桥梁
 * @author pipi
 * @since 2021/4/6 19:20
 */
public class BridgePatternDemo {

  public static void main(String[] args) {
    final Implementor impl = new ConcreteImpl();
    new RefinedAbstraction(impl).exec();

  }
  public interface Implementor {

    void exec();
  }

  public static class ConcreteImpl implements Implementor {

    @Override
    public void exec() {
      System.out.println("执行功能 逻辑");
    }
  }

  @AllArgsConstructor
  public static abstract class Abstraction {

    protected Implementor implementor;

    public abstract void exec();
  }

  public static class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
      super(implementor);
    }

    @Override
    public void exec() {
      implementor.exec();
    }
  }

}
