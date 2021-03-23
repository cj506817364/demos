package com.ppj.test.thread;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import cn.hutool.core.thread.ThreadUtil;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pipi
 * @since 2021/3/23 12:20
 */
@Slf4j
public class ConditionTest {

  Lock lock = new ReentrantLock();
  Condition condition = lock.newCondition();

  @Test
  public void conditionLock() {
    final Thread t1 = new Thread(() -> {
      try {
        log.info("开始获取锁");
        lock.lock();
        log.info("成功获取锁");
        log.info("开始condition.await");
        condition.await();
        log.info("结束condition.await");
//        log.info("开始释放锁");
//        lock.unlock();
//        log.info("成功释放锁");
      } catch (Exception e){

      }
    });
    t1.start();
    final Thread t2 = new Thread(() -> {
      log.info("开始获取锁");
      lock.lock();
      log.info("成功获取锁");
      log.info("开始释放锁");
      lock.unlock();
      log.info("成功释放锁");
    });
    t2.start();
    ThreadUtil.sleep(5 * 1000);
  }
}
