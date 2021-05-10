package com.ppj.notes.java;

import lombok.AllArgsConstructor;

/**
 * ==比较的其实是栈中的值:
 *  - 如果是对象: 栈中存放的是对象的内存地址, 地址一样则相等. 判断依据 可以通过System.identityHashCode验证
 *  - 如果是常量: 栈中存放的是是常量值, 常量值相等则相等.
 * @author pipi
 * @since 2021/5/10 15:53
 */
public class 等等比较的是什么 {

  public static void main(String[] args) {
    final A s = new A("s");
    final A b = new A("b");

    System.out.println(s.equals(b));
    System.out.println(System.identityHashCode(s) == System.identityHashCode(b));
    System.out.println(s == b);

    String s1 = "1";
    String s2 = "1";
    System.out.println(s1.equals(s2));
    System.out.println(System.identityHashCode(s1) == System.identityHashCode(s2));
    System.out.println(s1 == s2);
  }

  @AllArgsConstructor
  static class A extends Object {
    String name;

    @Override
    public String toString() {
      return "A";
    }

    @Override
    public boolean equals(Object o) {
      return true;
    }

    @Override
    public int hashCode() {
      return 1;
    }
  }
}
