package com.ppj.notes.thread.foobar;

/**
 * 3. 自旋 + 让出CPU
 *
 * @author pipi
 * @since 2021/3/23 16:39
 */
public class FooBar3 {

  volatile boolean flag = true;
  private int n;

  public FooBar3(int n) {
    this.n = n;
  }

  public static void main(String[] args) throws Exception {
    final FooBar3 fooBar = new FooBar3(10);
    new Thread(() -> {
      try {
        fooBar.foo(() -> System.out.print("foo"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        fooBar.bar(() -> System.out.println("bar"));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    System.in.read();
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; ) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      if (flag) {
        printFoo.run();
        i++;
        flag = false;
      } else {
        Thread.yield();
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; ) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      if (!flag) {
        printBar.run();
        i++;
        flag = true;
      } else {
        Thread.yield();
      }
    }
  }

}
