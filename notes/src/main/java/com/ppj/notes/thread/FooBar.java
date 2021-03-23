package com.ppj.notes.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. BLOCKING Queue
 * 2. CyclicBarrier 控制先后
 * 3. 自旋 + 让出CPU
 * 4. 可重入锁 + Condition
 * 5. synchronized + 标志位 + 唤醒
 * 6. 信号量 适合控制顺序
 *
 * @author pipi
 * @since 2021/3/22 18:11
 */
//BLOCKING Queue
@Slf4j
public class FooBar {

  private int n;
  private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
  private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);

  public FooBar(int n) {
    this.n = n;
  }

  public static void main(String[] args) throws Exception {
    final FooBar4 fooBar = new FooBar4(10);
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
}

//CyclicBarrier 控制先后
class FooBar2 {

  CyclicBarrier cb = new CyclicBarrier(2);
  volatile boolean fin = true;
  private int n;

  public FooBar2(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      while (!fin) {
        ;
      }
      printFoo.run();
      fin = false;
      try {
        cb.await();
      } catch (BrokenBarrierException e) {
      }

    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      try {
        cb.await();
      } catch (BrokenBarrierException e) {
      }
      printBar.run();
      fin = true;
    }

  }
}

//自旋 + 让出CPU
class FooBar3 {

  volatile boolean permitFoo = true;
  private int n;

  public FooBar3(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; ) {
      if (permitFoo) {
        printFoo.run();
        i++;
        permitFoo = false;
      } else {
        Thread.yield();
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; ) {
      if (!permitFoo) {
        printBar.run();
        i++;
        permitFoo = true;
      } else {
        Thread.yield();
      }
    }
  }
}

// 可重入锁 + Condition
class FooBar4 {

  Lock lock = new ReentrantLock();
  private final Condition fooCondition = lock.newCondition();
  volatile boolean flag = true;
  private int n;

  public FooBar4(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      lock.lock();
      try {
        while (!flag) {
          fooCondition.await();
        }
        printFoo.run();
        flag = false;
        fooCondition.signal();
      } finally {
        lock.unlock();
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      lock.lock();
      try {
        while (flag) {
          fooCondition.await();
        }
        printBar.run();
        flag = true;
        fooCondition.signal();
      } finally {
        lock.unlock();
      }
    }
  }
}

//synchronized + 标志位 + 唤醒
class FooBar5 {

  private int n;

  public FooBar5(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
    }
  }
}

class FooBar6 {

  private int n;

  public FooBar6(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printFoo.run() outputs "foo". Do not change or remove this line.
      printFoo.run();
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
      // printBar.run() outputs "bar". Do not change or remove this line.
      printBar.run();
    }
  }
}