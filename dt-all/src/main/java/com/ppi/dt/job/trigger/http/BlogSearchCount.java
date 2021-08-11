package com.ppi.dt.job.trigger.http;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

/**
 * @author pipi
 * @since 2021/7/27 13:32
 */
public class BlogSearchCount {
  public static final String BASE_URL = "http://10.1.1.51:6969/cron/blog/search/count/build/?pt=";

  public static void main(String[] args) {
    DateTime dateTime = DateUtil.parse("20210710", "yyyyMMdd");
    while (true) {
      final String yyyyMMdd = dateTime.toString("yyyyMMdd");
      final String url = BASE_URL + yyyyMMdd;
      System.out.println(url);
      if (!dateTime.before(DateUtil.yesterday())) {
        System.out.println("break");
        break;
      }
      String body = null;
      do {
        if (body != null) {
          System.out.println(body);
          ThreadUtil.sleep(10000);
        }
        body = HttpUtil.createGet(url).timeout(3600 * 1000).execute().body();
      } while (!"ok".equals(body));
      dateTime.offset(DateField.DAY_OF_YEAR, 1);
      System.out.println(dateTime);
    }
  }
}
