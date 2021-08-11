package com.ppj.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WebApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }

  @RequestMapping("test/")
  public String test() {
    System.out.println("test");
    return "test";
  }

  @RequestMapping("hello")
  public String hello() {
    System.out.println("hello");
    return "hello";
  }

  //  public static void main(String[] args) {
  //    //
  //    final HikariDataSource dataSource = createDataSource("cash_db_user", "l9iGfQOoEQN@*z$y",
  //
  // "jdbc:mysql://10.1.5.96:3306/cash?zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2b8");
  //    try (final Connection connection = dataSource.getConnection();      final Statement
  // statement = connection.createStatement();){
  //
  //      final ResultSet resultSet = statement.executeQuery("select id from cargo where
  // metaStatus=0;");
  //      while (resultSet.next()){
  //
  //        final int id = resultSet.getInt("id");
  //        System.out.println("id: " + id);
  //        connection.createStatement().executeUpdate("update cash.cargo set updateAt=NOW() where
  // metaStatus=0 AND id="+id+" order by id limit 1;");
  //        Thread.sleep(1);
  //      }
  //    } catch (Exception e) {
  //      e.printStackTrace();
  //    }
  //  }
  //
  //  private static HikariDataSource createDataSource(String username, String password, String url)
  // {
  //    HikariDataSource ds = new HikariDataSource();
  //    ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
  //    ds.setAutoCommit(true);
  //    ds.setMaximumPoolSize(2);
  //    ds.setMinimumIdle(1);
  //    ds.setJdbcUrl(url);
  //    ds.setUsername(username);
  //    ds.setPassword(password);
  //    return ds;
  //  }
}
