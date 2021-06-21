package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pipi
 * @since 2021/6/21 10:30
 */
public class No401_二进制手表 {

  public static final int MAX_HOUR_NUM = 4;
  public static final int MAX_MILL_NUM = 6;
  public static final char[] charArr = new char[] {'0', '0', '0', '0', '0', '0'};

  public static void main(String[] args) {
    System.out.println(new No401_二进制手表().readBinaryWatch(1));
  }

  public List<String> readBinaryWatch(int turnedOn) {
    if (turnedOn >= 10) {
      return null;
    }
    Set<String> timeSet = new HashSet<>();
    for (int hourNum = 0;
        hourNum <= Math.min(MAX_HOUR_NUM - 1, turnedOn) && hourNum <= turnedOn;
        hourNum++) {
      List<Integer> hourList = callHourList(hourNum);
      if (hourList.isEmpty()) {
        continue;
      }
      int millNum = turnedOn - hourNum;
      final List<Integer> millList = callMillNumRes(millNum);
      for (Integer hour : hourList) {
        // 校验合法
        if (hour.compareTo(12) >= 0) {
          continue;
        }
        for (Integer mill : millList) {
          // 校验合法
          if (mill.compareTo(60) >= 0) {
            continue;
          }
          if (mill <= 9) {
            timeSet.add(hour + ":0" + mill);
          } else {
            timeSet.add(hour + ":" + mill);
          }
        }
      }
    }
    return new ArrayList<>(timeSet);
  }

  private List<Integer> callMillNumRes(int millNum) {
    return calBinaryNumRes(millNum, MAX_MILL_NUM);
  }

  private List<Integer> callHourList(int hourNum) {
    return calBinaryNumRes(hourNum, MAX_HOUR_NUM);
  }

  private List<Integer> calBinaryNumRes(int oneNum, int bit) {
    if (oneNum > bit) {
      return new ArrayList<>();
    }
    String baseStr = String.copyValueOf(charArr, 0, bit);
    final HashSet<Integer> resList = new HashSet<>();
    getStrList(baseStr, oneNum, resList);
    return new ArrayList<>(resList);
  }

  private void getStrList(String baseStr, int oneNum, Set<Integer> resList) {
    if (oneNum <= 0) {
      resList.add(Integer.parseInt(baseStr, 2));
      return;
    }
    for (int i = 0; i < baseStr.length(); i++) {
      if (baseStr.charAt(i) == '0') {
        final String subStr = baseStr.substring(0, i) + "1" + baseStr.substring(i + 1);
        getStrList(subStr, oneNum - 1, resList);
      }
    }
  }
}
