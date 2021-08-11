package com.ppi.dt.job.album;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.sql.SqlExecutor;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/8/5 18:33
 */
public class CountByAlbumId {

  public static final String BASE_SQL =
      "select id, album_id, sender_id from message_message  where add_datetime > '2021-05-01 00:00:00' and album_id in ({})";

  public static void main(String[] args)
      throws SQLException, ExecutionException, InterruptedException {
    final String s = ResourceUtil.readStr("temp.txt", StandardCharsets.UTF_8);
    final List<Integer> collect =
        Arrays.stream(s.split("\n")).map(Integer::valueOf).collect(Collectors.toList());
    Connection conn = DSFactory.get("blogdb_backup").getConnection();
    Set<Integer> 图片数 = new HashSet<>();
    Set<Integer> 专辑 = new HashSet<>();
    Set<Integer> 人数 = new HashSet<>();
    List<CompletableFuture<Void>> futureList = new ArrayList<>();
    for (List<Integer> integers : CollUtil.split(collect, 10)) {
      final CompletableFuture<Void> voidCompletableFuture =
          CompletableFuture.runAsync(
              () -> {
                try {
                  List<AlbumRes> queryList =
                      SqlExecutor.query(
                          conn,
                          StrUtil.format(BASE_SQL, CollUtil.join(integers, ",")),
                          new BeanListHandler<>(AlbumRes.class));
                  for (AlbumRes albumRes : queryList) {
                    图片数.add(albumRes.getId());
                    专辑.add(albumRes.getAlbum_id());
                    人数.add(albumRes.getSender_id());
                  }
                  Console.log("{} 图片数: {} 专辑: {} 人数: {}",DateUtil.now(), 图片数.size(), 专辑.size(), 人数.size());
                } catch (SQLException e) {
                  e.printStackTrace();
                }
              });

      futureList.add(voidCompletableFuture);
    }
    for (CompletableFuture<Void> future : futureList) {
      future.get();
    }

    Console.log("{} 图片数: {} 专辑: {} 人数: {}",DateUtil.now(), 图片数.size(), 专辑.size(), 人数.size());
  }
}
