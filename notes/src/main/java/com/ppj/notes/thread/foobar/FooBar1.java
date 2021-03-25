package com.ppj.notes.thread.foobar;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 1. BLOCKING Queue
 *
 * @author pipi
 * @since 2021/3/22 18:11
 */
//BLOCKING Queue
@Slf4j
public class FooBar1 {

  private int n;
  private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
  private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);

  public FooBar1(int n) {
    this.n = n;
  }


  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      bar.put(1);
      printFoo.run();
      foo.put(1);
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      foo.take();
      printBar.run();
      bar.take();
    }
  }

  public static void main(String[] args) throws Exception {
    final FooBar1 fooBar = new FooBar1(10);
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

}