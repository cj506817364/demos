package com.ppj.notes.hashmap.lock;

import cn.hutool.core.lang.Console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * final ReentrantLock reentrantLock = new ReentrantLock();
 *
 * @author pipi
 * @since 2021/3/22 14:18
 */
public class TestDemo {

  public static final int THREAD_NUM = 10;

  public static volatile ReentrantLock reentrantLock = new ReentrantLock();

  public static void main(String[] args) throws IOException {
    List<Thread> threadList = new ArrayList<>();
    for (int i = 0; i < THREAD_NUM; i++) {
      int finalI = i;
      threadList.add(new Thread(() -> {
        final boolean res = reentrantLock.tryLock();
        Console.log("num: {} res: {}", finalI, res);
        if (res) {
          reentrantLock.unlock();
        }
      }));
    }

    threadList.forEach(Thread::start);

    System.in.read();
  }

}
