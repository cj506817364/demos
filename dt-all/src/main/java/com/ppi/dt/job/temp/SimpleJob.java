package com.ppi.dt.job.temp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author pipi
 * @since 2021/7/8 12:18
 */
public class SimpleJob {

  public static void main(String[] args) {
      final List<String> strings = FileUtil.readLines("/duitang/ppj/tmp/logstash/identity_info_user_id.csv", StandardCharsets.UTF_8);
      final String join = CollUtil.join(strings,",");
    System.out.println(join);
  }
}
