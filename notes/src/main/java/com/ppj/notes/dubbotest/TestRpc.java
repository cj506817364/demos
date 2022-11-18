package com.ppj.notes.dubbotest;

import java.util.concurrent.CompletableFuture;

/**
 * @author cj
 * @since 26/9/22 5:45 下午
 */
public class TestRpc {

  public static void main(String[] args) throws Exception {
    final TestRpc rpc = new TestRpc();

    final CompletableFuture<Boolean> future = CompletableFuture
        .supplyAsync(() -> {
          try {
            return rpc.task();
          } catch (Exception e) {
            e.printStackTrace();
          }
          return false;
        });
    // todo....

    System.out.println(future.get());
  }

  public Boolean task() throws Exception {
    Thread.sleep(1000);
    return true;
  }
}
