package com.ppi.dt.job.theme;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.mongo.MongoDS;
import cn.hutool.db.nosql.mongo.MongoFactory;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pipi
 * @date 2020/12/23 16:03
 */
public class FilterThemeId {

  public static final String THEME_FILE_PATH = "/duitang/ppj/tmp/suggest_result.csv";

  public static void main(String[] args) {
    List<String> themeIdList = new ArrayList<>();
    final List<String> targetList = FileUtil
        .readLines(new File(THEME_FILE_PATH), Charset.defaultCharset());
    for (String target : targetList) {
      if (StrUtil.isEmpty(target)) {
        continue;
      }
      final String group1 = ReUtil.getGroup1("theme_id=([0-9|a-z|A-Z]+)", target);
      Console.log("theme_id: {}", group1);
      themeIdList.add(group1);
    }
    MongoTemplate template = new MongoTemplate(new SimpleMongoClientDbFactory(MongoClients.create("mongodb://10.1.5.143:27017"), "watercave"));
    final List<ThemeDO> all = template.findAll(ThemeDO.class,"theme");
    Set<String> dbThemeIdList = new HashSet<>();
    for (ThemeDO themeDO : all) {
      dbThemeIdList.add(themeDO.getId());
    }
    for (String id : themeIdList) {
      if (dbThemeIdList.contains(id)) {
        continue;
      }
      Console.log("no exist themeId: {}", id);
    }
  }

}
