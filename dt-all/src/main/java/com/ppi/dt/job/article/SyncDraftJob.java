package com.ppi.dt.job.article;

import cn.hutool.core.collection.CollUtil;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import java.util.List;

/**
 * @author pipi
 * @since 2021/8/12 10:21
 */
@Slf4j
public class SyncDraftJob {

  public static void main(String[] args) {
    MongoTemplate template =
        new MongoTemplate(
            new SimpleMongoClientDbFactory(
                MongoClients.create("mongodb://10.1.5.203:27017"), "pgcDB"));
    final List<ArticleDraftDO> articleDraft =
        template.findAll(ArticleDraftDO.class, "articleDraft");
    System.out.println(articleDraft.size());
    for (List<ArticleDraftDO> articleDraftDOS : CollUtil.split(articleDraft, 1000)) {
      log.info("size: {}", articleDraftDOS.size());
      template.insert(articleDraftDOS, "articleDraftDO");
    }
  }
}
