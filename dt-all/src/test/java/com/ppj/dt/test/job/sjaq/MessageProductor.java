package com.ppj.dt.test.job.sjaq;

/**
 * @author pipi
 * @since 2021/10/14 18:37
 */
public class MessageProductor {

  private MergeHandler mergeHandler;

  public synchronized MergeHandler getHandler(){
    if (mergeHandler == null) {
      mergeHandler = new MergeHandler();
    }
    return mergeHandler;
  }

  public void sendToHandler(MessageModel msg) {
    getHandler().accept(msg);
  }

  public static void main(String[] args) {
    final MessageProductor msgProductor = new MessageProductor();
    while (true){
      final MessageModel 违规词 = new MessageModel(1, "违规词");
      msgProductor.sendToHandler(违规词);

    }
  }
}


