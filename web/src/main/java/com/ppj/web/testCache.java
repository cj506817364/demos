package com.ppj.web;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author pipi
 * @since 2021/8/11 16:21
 */
public class testCache {
  public static final LoadingCache<String, Object> caches =
      CacheBuilder.newBuilder()
          .maximumSize(100)
          .refreshAfterWrite(1000, TimeUnit.MILLISECONDS)
          .build(
              new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                  return generateValueByKey(key);
                }
              });

  public static void main(String[] args) throws ExecutionException {

    for (int i = 0; i < 100; i++) {
      System.out.println(caches.get("key-zorro"));
      ThreadUtil.safeSleep(1000);
    }
  }

  private static String generateValueByKey(String key) {
    return RandomUtil.randomString(key.length());
  }
}
