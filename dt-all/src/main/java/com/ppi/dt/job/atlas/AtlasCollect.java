package com.ppi.dt.job.atlas;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.ppi.dt.common.util.SimpleBatchTools;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * atlas 收藏数
 *
 * @author pipi
 * @since 2021/6/29 10:46
 */
public class AtlasCollect {

  public static final String SKIP_FLAG = "\",\"N";

  public static final String ATLAS_BLOG_IDS_FILE = "/duitang/ppj/tmp/atlas_like/atlas_blog_ids.csv";
  public static final String BLOG_COLLECT_COUNT_FILE =
      "/duitang/ppj/tmp/atlas_like/blog_collect_count.csv";
  public static final String BLOG_ID_PARENT_ID_FILE =
      "/duitang/ppj/tmp/atlas_like/blog_20210628.csv";
  public static final String ATLAS_COLLECT_COUNT_FILE = "/duitang/ppj/tmp/atlas_like/atlas_collect_count.csv";
  public static void main(String[] args) {
    // 计算blog收藏数
    Object2ObjectOpenHashMap<Long, Integer> openHashMap;
    openHashMap = callBlogCollect();

    // 计算atlas收藏数
    callAtlasCollect(openHashMap);
  }

  private static void callAtlasCollect(Object2ObjectOpenHashMap<Long, Integer> blogCollectCount) {
    if (blogCollectCount == null) blogCollectCount = readBlogCollect();
    if (blogCollectCount == null) {
      Console.error("stop for null");
      return;
    }
    Object2ObjectOpenHashMap<Long, Integer> atlasCollectCount = new Object2ObjectOpenHashMap<>();

    Object2ObjectOpenHashMap<Long, Integer> finalBlogCollectCount = blogCollectCount;

    AtomicInteger count = new AtomicInteger();
    AtomicInteger total = new AtomicInteger();
    SimpleBatchTools.batchWithLargeFile(
        new File(ATLAS_BLOG_IDS_FILE),
        100000,
        new Consumer<List<String>>() {
          @Override
          public void accept(List<String> strings) {
            count.addAndGet(strings.size());
            total.addAndGet(strings.size());
            if(count.get() >= 1000000){
              count.set(0);
              Console.log("callAtlasCollect one batch total: {} time: {}", total, DateUtil.date().toString());
            }
            for (String line : strings) {
              final IdAndIds idAndIds = new IdAndIds(line);
              int count = 0;
              for (Long id : idAndIds.getIds()) {
                count += finalBlogCollectCount.getOrDefault(id, 0);
              }
              atlasCollectCount.put(idAndIds.getId(), count);
            }
            writeToFile(atlasCollectCount, ATLAS_COLLECT_COUNT_FILE, false);
          }
        });
    writeToFile(atlasCollectCount, ATLAS_COLLECT_COUNT_FILE, true);
  }

  private static void writeToFile(Object2ObjectOpenHashMap<Long, Integer> map, String file, boolean ignoreSize) {
    int size = 100000;
    if (!ignoreSize && map.size() < size){
      return;
    }
    Collection<String> cList = new ArrayList<>();
    map.forEach((k, v) -> cList.add(k + "," + v));
    FileUtil.writeLines(cList, file, StandardCharsets.UTF_8,true);
    map.clear();
  }

  private static Object2ObjectOpenHashMap<Long, Integer> readBlogCollect() {
    DateTime date = DateUtil.date();
    Console.log("read from file start time: {}", date.toString());

    DateTime date2 = DateUtil.date();
    Console.log("read from file end time: {}", date2.toString());
    Console.log("read from file use time: {}", date2.getTime() - date.getTime());
    return null;
  }

  private static Object2ObjectOpenHashMap<Long, Integer> callBlogCollect() {
    DateTime date = DateUtil.date();
    Console.log("callBlogCollect start time: {}", date.toString());
    Object2ObjectOpenHashMap<Long, Integer> blogCollectCount = new Object2ObjectOpenHashMap<>();

    AtomicInteger count = new AtomicInteger();
    AtomicInteger total = new AtomicInteger();
    SimpleBatchTools.batchWithLargeFile(
        new File(BLOG_ID_PARENT_ID_FILE),
        1000000,
        strings -> {
          count.addAndGet(strings.size());
          total.addAndGet(strings.size());
          if(count.get() >= 100000){
            count.set(0);
            Console.log("callBlogCollect one batch total: {} time: {}", total, DateUtil.date().toString());
          }
          for (String line : strings) {
            if (StrUtil.contains(line, SKIP_FLAG)) {
              continue;
            }
            final IdAndId idAndId = new IdAndId(line);
            Integer orDefault = blogCollectCount.getOrDefault(idAndId.getId2(), 0);
            blogCollectCount.put(idAndId.getId2(), ++orDefault);
          }
        });
    DateTime date2 = DateUtil.date();
    Console.log("callBlogCollect end time: {}", date2.toString());
    Console.log("callBlogCollect use time: {}", date2.getTime() - date.getTime());

    Collection<String> cList = new ArrayList<>();
    blogCollectCount.forEach((k, v) -> cList.add(k + "," + v));
    FileUtil.writeLines(cList, BLOG_COLLECT_COUNT_FILE, StandardCharsets.UTF_8);
    return blogCollectCount;
  }
}
