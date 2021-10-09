package com.ppj.random;

import cn.hutool.core.convert.Convert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 2021/9/24 14:55
 * @author pipi
 */
public class AliasMethodTest {

  public static void main(String[] args) {
    List<Double> doubleList = new ArrayList<>();
    doubleList.add(0.05);
    doubleList.add(0.3);
    doubleList.add(0.15);
    doubleList.add(0.25);
    doubleList.add(0.2);
    final AliasMethod aliasMethod = new AliasMethod(doubleList);
    for (int j = 100000; j > 0; j--) {
      Map<Integer, Integer> countMap = new HashMap<>();
      for (int i = 100; i > 0; i--) {
        final int next = aliasMethod.next();
        Integer orDefault = countMap.getOrDefault(next, 0);
        countMap.put(next, ++orDefault);
      }
      System.out.print("count: " + Convert.toStr(countMap) + "\t\t");
      Map<Integer, Integer> disCountMap = new HashMap<>();
      countMap.forEach(
          (k, v) -> {
            switch (k) {
              case 0:
                disCountMap.put(k, Math.abs(v - 5));
                break;
              case 1:
                disCountMap.put(k, Math.abs(v - 15));
                break;
              case 2:
                disCountMap.put(k, Math.abs(v - 20));
                break;
              case 3:
                disCountMap.put(k, Math.abs(v - 25));
                break;
              case 4:
                disCountMap.put(k, Math.abs(v - 30));
                break;
            }
          });
      System.out.println("disCount: " + Convert.toStr(disCountMap));
    }
  }
}
