package com.ppi.dt.job.cms;

import com.mongodb.client.MongoClients;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author pipi
 * @since 2021/7/14 16:55
 */
public class UpdateSomeCloumn {
  public static void main(String[] args) {
    //
    MongoTemplate template =
        new MongoTemplate(
            new SimpleMongoClientDbFactory(
                MongoClients.create("mongodb://10.1.5.203:27017"), "static_html"));
    final Query query =
        Query.query(Criteria.where("cate").is("column"))
            .addCriteria(Criteria.where("urid").is("da_ren2"));
    final Body bodies = template.findOne(query, Body.class);
    assert bodies != null;
  }

  @Data
  @Document(collection = "static_html")
  static class Body {
    @Id String _id;
    String body;
  }



}
