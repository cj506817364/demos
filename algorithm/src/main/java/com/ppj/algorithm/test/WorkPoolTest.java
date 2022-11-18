package com.ppj.algorithm.test;

/**
 * @author cj
 * @since 15/9/22 4:36 下午
 */
public class WorkPoolTest {

  public static void main(String[] args) {
    WorkPool pool = NewPool(10);
    System.out.println(pool.submit(2));
    System.out.println(pool.submit(3));
    System.out.println(pool.submit(4));
  }

  private static WorkPool NewPool(int i) {
    return new WorkPool(i);
  }
}
