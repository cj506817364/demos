package com.ppj.dt.test.job.sjaq;

import com.mysql.cj.protocol.MessageHeader;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author pipi
 * @since 2021/10/14 18:38
 */
public class MergeHandler {
  Thread t1 = new Thread(new MergeRunner());
  Thread t2 = new Thread(new MergeRunner());

  public MergeHandler() {
    t1.start();
    t2.start();
  }

  public static BlockingQueue<MessageModel> queue = new ArrayBlockingQueue<>(500000);

  public void accept(MessageModel msg) {
    queue.offer(msg);
  }

  public void close(){
    t1.interrupt();
    t2.interrupt();
  }

}
