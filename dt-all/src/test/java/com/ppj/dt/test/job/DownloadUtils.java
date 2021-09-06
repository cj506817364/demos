package com.ppj.dt.test.job;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author pipi
 * @since 2021/8/20 18:35
 */
public class DownloadUtils {
  public static final String BASE_PATH = "/Users/cj/Downloads/BaiDuPan/download";
  public static final String BASE_SAVE_PATH = "/Users/cj/Downloads/BaiDuPan/downloadFile";

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    List<CompletableFuture<Void>> futureList = new ArrayList<>();
    for (File file : Objects.requireNonNull(FileUtil.file(BASE_PATH).listFiles())) {
      if (!file.getName().endsWith(".txt")) {
        Console.log("skip file name: {}", file.getName());
        continue;
      }
      final List<String> strings = FileUtil.readLines(file, Charset.defaultCharset());
      for (String string : strings) {
        if (!string.startsWith("http")) {
          Console.log("skip url name: {}", string);
          continue;
        }

        final String dest = BASE_SAVE_PATH + File.separator + file.getName();
        FileUtil.mkdir(dest);
        final CompletableFuture<Void> voidCompletableFuture =
            CompletableFuture.runAsync(
                () -> {
                  try {
                    final long l = HttpUtil.downloadFile(string, dest);
                    Console.log("下载成功! url: {}", string);
                  } catch (Exception e) {
                    Console.error("下载失败! url: {}", string, e);
                  }
                });
        futureList.add(voidCompletableFuture);
      }
    }
    for (CompletableFuture<Void> future : futureList) {
      future.get();
    }
  }
}
