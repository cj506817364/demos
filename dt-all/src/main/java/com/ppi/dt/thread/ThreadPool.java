package com.ppi.dt.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 现象很奇怪 20秒后 activeCount一直上不去 处于 很小的一个值
 * @author pipi
 * @since 2021/2/24 16:39
 */
public class ThreadPool {

  private static final Integer corePoolSize = 200;
  private static final int keepAliveTime = 30;

  private static final int workQueueSize = 256;

  public static final long overSleepTime = new Date().getTime() + 20*1000;

  public static ExecutorService executorService() {
    final String threadNamePrefix = "blogQueryThreadPool";
    /*32=32 +0 让其永远是 32个线程*/
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(
        corePoolSize,
        corePoolSize,
        keepAliveTime,
        TimeUnit.MINUTES,
        new LinkedBlockingQueue<>(workQueueSize),
        new ThreadFactoryBuilder().setNameFormat(threadNamePrefix + "-%d").build(),
        (r, executor1) -> {
          /*通过自定义的拒绝策略打出更加详细的信息*/
          final int activeCount = executor1.getActiveCount();
          final int size = executor1.getQueue().size();
          String msg = String.format("Thread pool is EXHAUSTED!" +
                  " Thread Name: %s, Queue Size: %d, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
              threadNamePrefix,size
              , executor1.getPoolSize(), activeCount, executor1.getCorePoolSize(),
              executor1.getMaximumPoolSize(), executor1.getLargestPoolSize(),
              executor1.getTaskCount(), executor1.getCompletedTaskCount(), executor1.isShutdown(),
              executor1.isTerminated(), executor1.isTerminating());
            Console.error(msg);
          throw new RejectedExecutionException(msg);
        }
    );
    return executor;
  }

  public static void main(String[] args) {
    final ExecutorService executorService = executorService();
    int index = 0;
    try {
      while (true) {
        try {
          ThreadUtil.sleep(RandomUtil.randomInt(1));
          index++;
          executorService.submit(new TestTask("" + index));
        } catch (Exception e) {
        }
      }
    } catch (Exception e) {
    }
    Console.error("over!");
  }

  static class TestTask implements Runnable {

    private String taskName;

    public TestTask(String taskName) {
      this.taskName = taskName;
    }

    @Override
    public void run() {
      ThreadUtil.sleep(overSleepTime - new Date().getTime());
//      ThreadUtil.sleep(RandomUtil.randomInt(1000));
    }

  }

}
