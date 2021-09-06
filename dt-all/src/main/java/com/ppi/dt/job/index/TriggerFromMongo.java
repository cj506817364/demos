package com.ppi.dt.job.index;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @date 2020/12/21 12:05
 */
public class TriggerFromMongo {

  public static final String BASE_ATLAS_URL = "http://10.1.5.111:10140/index/atlas/trigger/?ids={}";
  public static final String BASE_BLOG_URL = "http://10.1.5.111:10140/index/blog/trigger/?ids={}";

  public static void main(String[] args) {
    triggerAtlas();
    //    triggerBlog();
  }

  private static void triggerBlog() {
    MongoTemplate template =
        new MongoTemplate(
            new SimpleMongoClientDbFactory(
                MongoClients.create("mongodb://10.1.5.71:27017"), "copyright"));
    final List<CopyrightBlog> all = template.findAll(CopyrightBlog.class);
    for (List<CopyrightBlog> copyrightAtlases : CollUtil.split(all, 24)) {
      final String join =
          StrUtil.join(
              ",",
              copyrightAtlases.stream().map(CopyrightBlog::getId).collect(Collectors.toList()));
      final String format = StrUtil.format(BASE_BLOG_URL, join);
      System.out.println(format);
      System.out.println(HttpUtil.get(format));
      ThreadUtil.sleep(50);
    }
  }

  private static void triggerAtlas() {
    MongoTemplate template =
        new MongoTemplate(
            new SimpleMongoClientDbFactory(
                MongoClients.create("mongodb://10.1.5.71:27017"), "copyright"));
    final List<CopyrightAtlas> all = template.findAll(CopyrightAtlas.class);
    for (List<CopyrightAtlas> copyrightAtlases : CollUtil.split(all, 24)) {
      final String join =
          StrUtil.join(
              ",",
              copyrightAtlases.stream().map(CopyrightAtlas::getId).collect(Collectors.toList()));
      final String format = StrUtil.format(BASE_ATLAS_URL, join);
      System.out.println(format);
      System.out.println(HttpUtil.get(format));
      ThreadUtil.sleep(50);
    }
  }

  @Data
  static class BaseCopyright {
    @Id Long id;
  }

  @Data
  @Document("copyright_blog")
  static class CopyrightBlog extends BaseCopyright {
    String copyRightUserId;
  }

  @Data
  @Document("copyright_atlas")
  static class CopyrightAtlas extends BaseCopyright {
    String copyRightUserId;
  }
}
