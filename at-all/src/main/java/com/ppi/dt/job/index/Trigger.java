package com.ppi.dt.job.index;

import com.ppi.dt.common.util.SimpleBatchTools;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @date 2020/12/21 12:05
 */
public class Trigger {

  public static final String BASE_URL = "http://10.1.5.111:9539/index/user/trigger/?ids={}";
  public static final String BASE_FILE_PATH = "/duitang/ppj/tmp/user_id.csv";

  public static void main(String[] args) {
    final int[] total = {0};
    AtomicLong start = new AtomicLong(System.currentTimeMillis());
    SimpleBatchTools.batchWithLargeFile(new File(BASE_FILE_PATH), 240000, stringsList -> {
      List<CompletableFuture<Void>> futureList = new ArrayList<>();
      long end = System.currentTimeMillis();
      Console.log("currentTime: {} id: {} useTime: {}", DateUtil.now(), stringsList.get(0),
          end - start.get());
      start.set(end);
      for (List<String> strings : CollUtil.split(stringsList, 24)) {
        if (strings.contains("24286837")) {
          Console.log("need stop!");
        }
        final String join = CollUtil
            .join(strings.stream().map(s -> s.replaceAll("\"", "")).collect(Collectors.toList()),
                ",");
        final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
          String url = StrUtil.format(BASE_URL, join);
          final String res = HttpUtil.get(url);
          if (!"ok".equals(res)) {
            Console.error(res);
          }
        });
        futureList.add(future);
      }
      int i = 0;
      for (CompletableFuture<Void> future : futureList) {
        try {
          if (i >= 1000) {
            total[0] += i;
            i = 0;
//            Console.log("{} total: {}", DateUtil.now(), total[0]);
          }
          future.get();
          i++;
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    });
  }


}
