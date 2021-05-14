package com.ppj.test;

import java.util.Date;

/**
 * @author pipi
 * @since 2021/5/13 11:30
 */
public class Test extends Date {
  public static void main(String[] args) {
    //
    new Test().test();
  }

  public void test() {
    System.out.println(super.getClass().getSimpleName());
    System.out.println(super.getClass().getName());
  }
}
