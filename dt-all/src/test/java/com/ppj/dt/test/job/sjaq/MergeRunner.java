package com.ppj.dt.test.job.sjaq;

import com.google.common.collect.Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pipi
 * @since 2021/10/14 18:38
 */
public class MergeRunner implements Runnable {

  private MsgService msgService = new MsgService();

  private List<MessageModel> buffer = new ArrayList<>();
  private String msg;
  private static ReentrantLock lock = new ReentrantLock();
  private  Condition flushing = lock.newCondition();


  @Override
  public void run() {
    try{
      lock.lock();
      while (true) {
        if (buffer.size() == 30000) {
          buffer.removeIf(msg -> "违规词".equals(msg.msg));
          flushing.await();
          flush();
        }
        // TODO 用guava
        Queues.drain(MergeHandler.queue, buffer, 30000, 100, TimeUnit.MILLISECONDS);
//        buffer.add(MergeHandler.queue.poll());
      }
    } catch (InterruptedException e) {
      // log
    } finally{
      lock.unlock();
    }
  }

  private void flush() {
    this.msg = new String();
    for (MessageModel msg : buffer) {
      String form = "" + msg.form;
      this.msg = msg + form + ":" + msg.msg + "/n";
    }
    msgService = new MsgService();
    msgService.process(this.msg);
    buffer.clear();
//    buffer.removeAll(buffer);
    flushing.signalAll();
  }
}
