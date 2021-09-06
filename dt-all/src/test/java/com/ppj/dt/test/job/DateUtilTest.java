package com.ppj.dt.test.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.format.FastDateFormat;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.TimeZone;

/**
 * @author pipi
 * @since 2021/8/18 15:26
 */
@Slf4j
public class DateUtilTest {
  public static void main(String[] args) {
    System.out.println(DateUtil.parse(null));
    System.out.println(DateUtil.parse(""));
    System.out.println(DateUtil.parse("2021-08-18T06:24:41Z").toString());
    System.out.println(DateUtil.parse("2021-08-18T06:24:41Z").toString(TimeZone.getDefault()));
    System.out.println(parseForAsa("2021-08-20T02:15Z").toString(TimeZone.getDefault()));
    System.out.println(parseForAsa("2021-08-20T02:15Z").toString(TimeZone.getDefault()));
    System.out.println(parseForAsa("2021-08-26T07:30Z   ").toString(TimeZone.getDefault()));
    System.out.println(parseForAsa("2021-08-25T03:27Z").toString(TimeZone.getDefault()));
  }

  public static DateTime parseIgnoreException(String dateStr) {
    return DateUtil.parse(dateStr);
  }

  public static DateTime parseIgnoreException(String dateStr, FastDateFormat format) {
    try {
      return DateUtil.parse(dateStr, format);
    } catch (Exception e) {
      log.error("format error str: {}", dateStr);
      return null;
    }
  }

  /**
   * asa专用
   *
   * @param dateStr
   * @return
   */
  public static DateTime parseForAsa(String dateStr) {
    dateStr = StrUtil.trim(dateStr);
    if (StrUtil.isEmpty(dateStr)) {
      return null;
    }
    DateTime date = null;
    if (dateStr.length() == 17) {
      Console.log("match success str: {}", dateStr);
      date =
          parseIgnoreException(
              dateStr,
              FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm'Z'", TimeZone.getTimeZone("UTC")));
    }
    if (date != null) {
      return date;
    }
    Console.log("match error str: {}", dateStr);
    date = DateUtil.parse(dateStr);
    if (date != null) {
      return date;
    }
    return parseIgnoreException(
        dateStr, FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm'Z'", TimeZone.getTimeZone("UTC")));
  }
}
