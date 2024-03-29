package com.ppj.notes.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author pipi
 * @since 2021/3/23 17:02
 */
public class ZeroEvenOdd {

  private int n;
  private Semaphore zero = new Semaphore(1);
  private Semaphore even = new Semaphore(0);
  private Semaphore odd = new Semaphore(0);

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i < n + 1; i++) {
      zero.acquire();
      printNumber.accept(0);
      if (i % 2 == 1){
        odd.release();
      }else {
        even.release();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    for (int i = 2; i < n + 1; i+=2) {
      even.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    for (int i = 1; i < n + 1; i+=2) {
      odd.acquire();
      printNumber.accept(i);
      zero.release();
    }
  }

  public static void main(String[] args) {
    ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
    new Thread(() -> {
      try {
        zeroEvenOdd.zero(System.out::print);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        zeroEvenOdd.even(System.out::print);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        zeroEvenOdd.odd(System.out::print);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    ThreadUtil.sleep(2000);
  }

}
