package com.ppj.design.chain;

import lombok.AllArgsConstructor;

/**
 * 责任链模式
 *
 * @author pipi
 * @since 2021/4/6 19:06
 */
public class ChainPatternDemo {

  public static void main(String[] args) {
    // 业务1
    new Handler1(new Handler2(new Handler3(null))).exec();
    // 业务2
    new Handler3(new Handler1(new Handler2(null))).exec();

  }

  @AllArgsConstructor
  public static abstract class Handler {

    protected Handler successor;

    public abstract void exec();
  }


  public static class Handler1 extends Handler {

    public Handler1(Handler successor) {
      super(successor);
    }

    @Override
    public void exec() {
      System.out.println("执行功能1");
      if (successor != null) {
        successor.exec();
      }
    }
  }


  public static class Handler2 extends Handler {

    public Handler2(Handler successor) {
      super(successor);
    }

    @Override
    public void exec() {
      System.out.println("执行功能2");
      if (successor != null) {
        successor.exec();
      }
    }
  }


  public static class Handler3 extends Handler {

    public Handler3(Handler successor) {
      super(successor);
    }

    @Override
    public void exec() {
      System.out.println("执行功能3");
      if (successor != null) {
        successor.exec();
      }
    }
  }
}
