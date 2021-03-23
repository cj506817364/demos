package com.ppj.notes.thread;

/**
 * @author pipi
 * @since 2021/3/22 17:54
 */
public class Foo {

  private volatile int a;
  public Foo() {
    a = 0;
  }

  public void first(Runnable printFirst) throws InterruptedException {
    while (a != 0){}
    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();
        a = 1;
  }

  public void second(Runnable printSecond) throws InterruptedException {
    while (a != 1){}
    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();
        a = 2;
  }

  public void third(Runnable printThird) throws InterruptedException {
    while (a != 2){}
    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();
        a = 0;
  }
}
