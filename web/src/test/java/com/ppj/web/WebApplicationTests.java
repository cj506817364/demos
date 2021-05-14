package com.ppj.web;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

  public static void main(String[] args) {
    final HttpRequest get = HttpUtil.createGet("http://127.0.0.1:8080/hello");
    get.form("event_type", 6);
    System.out.println(get.execute().body());
  }

  @Test
  void contextLoads() {}
}
