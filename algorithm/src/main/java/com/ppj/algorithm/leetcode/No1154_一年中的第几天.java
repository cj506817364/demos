package com.ppj.algorithm.leetcode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cj
 * @since 2021/12/21 19:57
 */
public class No1154_一年中的第几天 {

  public static void main(String[] args) throws ParseException {
    System.out.println(new No1154_一年中的第几天().dayOfYear("2019-01-09"));
  }

  public int dayOfYear(String time) throws ParseException {
    DateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
    Date date = fm.parse(time);
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    String str = String.format("%tj", date);//得到time日期是在这年的第几天
    return Integer.valueOf(str);
  }
}
