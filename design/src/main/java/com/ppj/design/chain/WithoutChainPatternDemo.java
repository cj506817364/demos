package com.ppj.design.chain;

/**
 * 不用责任链模式
 *
 * @author pipi
 * @since 2021/4/6 19:06
 */
public class WithoutChainPatternDemo {

  public static void main(String[] args) {
    // 业务1
    System.out.println("执行功能1");
    System.out.println("执行功能2");
    System.out.println("执行功能3");

    // 业务2
    System.out.println("执行功能3");
    System.out.println("执行功能1");
    System.out.println("执行功能2");


  }
}
