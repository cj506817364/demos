package com.ppi.dt.job.copyright;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Tuple;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.UpdateResult;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author pipi
 * @since 2021/7/22 11:49
 */
public class UpdateOriginal {
  public static void main(String[] args) {
    long total = 0;
      MongoTemplate template =
              new MongoTemplate(
                      new SimpleMongoClientDbFactory(
                              MongoClients.create("mongodb://10.1.5.71:27017"), "copyright"));

    final List<Blog> all = template.find(new Query(), Blog.class);
    final List<List<Blog>> split = CollUtil.split(all, 1000);
    for (List<Blog> blogs : split) {
      final List<User> userList = template.find(new Query().addCriteria(Criteria.where("_id").in(blogs.stream().map(Blog::getCopyRightUserId).distinct().collect(Collectors.toList()))), User.class);
      final Map<String, User> map = userList.stream().collect(Collectors.toMap(User::getId, a->a));
      List<Long> zeroList = new ArrayList<>();
      List<Long> oneList = new ArrayList<>();
      for (Blog blog : blogs) {
        final User user = map.get(blog.getCopyRightUserId());
        final Integer update = update(blog, user);
        if (update == 1) {
          oneList.add(blog.getId());
        }else {
          zeroList.add(blog.getId());
        }
      }
      if (CollUtil.isNotEmpty(zeroList)){
        final UpdateResult updateResult = template.updateMulti(Query.query(Criteria.where("_id").in(zeroList)), new Update().set("original", 0), Blog.class);
        final long modifiedCount = updateResult.getModifiedCount();
        total += modifiedCount;
      }
      if (CollUtil.isNotEmpty(oneList)){
        final long modifiedCount = template.updateMulti(Query.query(Criteria.where("_id").in(oneList)), new Update().set("original", 1), Blog.class).getModifiedCount();
        total += modifiedCount;
      }
    }
    System.out.println(total);
  }

  public static Integer update(Blog blog, User user) {
    return user == null ? 0 : blog.senderId.equals(user.getDtUserId()) ? 1 : 0;
  }

  @Data
  @Document(value = "copyright_blog")
  static class Blog {
    @Id
    Long id;
    String copyRightUserId;
    Long senderId;

    private Integer original;
    public void update(User user) {
      this.original = user == null ? 0 : this.senderId.equals(user.getDtUserId()) ? 1 : 0;
    }

  }

  @Data
  @Document(value = "copyright_user")
  static class User {
    @Id
    private String id;
    private Long dtUserId;
  }
}
