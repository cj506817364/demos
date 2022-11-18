package com.ppj.algorithm.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author cj
 * @since 15/9/22 4:36 下午
 */
public class WorkPool {

  private BlockingDeque<Integer> queue = new LinkedBlockingDeque<>();

  private static Map<Object, Object> resultMap = new HashMap<>();

  static class Worker extends Thread {
    private BlockingDeque<Integer> queue = null;

    private Worker (BlockingDeque<Integer> queue) {
      this.queue = queue;
    }

    @Override
    public void run() {
      while (true) {
        try {
          final Integer val = queue.take();
          if (val == null) {
            continue;
          }
          resultMap.put(val, val * val);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private List<Thread> workers = new ArrayList<>();

  public WorkPool(int i) {
    for (int i1 = i; i1 > 0; i1--) {
      Worker worker = new Worker(queue);
      worker.start();
      workers.add(worker);
    }
  }


  public Object submit(Integer i) {
    final boolean add = queue.add(i);
    if (!add) {
      throw new RuntimeException("queue is full");
    }
    queue.push(i);
    // 等待线程池执行完毕

    while (true) {
      final Object o = resultMap.get(i);
      if (o == null) {
        continue;
      }
      return o;
    }
  }





}
