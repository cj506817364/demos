package com.ppj.algorithm.nowcoder;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 翻转指定区间的单词
 *
 * @author cj
 * @since 2022/2/27 09:46
 */
public class No2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final String line = sc.nextLine();
    final List<Integer> newList = Arrays.stream(line.split(",")).map(Integer::parseInt)
        .sorted().collect(Collectors.toList());
    int[] arr = null;
    if (newList.size() >= 3) {
     arr = new int[3];
    }else {
      arr = new int[newList.size()];
    }

    for (int i = 0; i < arr.length; i++) {
      arr[i] = newList.get(i);
    }

    String minStr = getMinStr(arr);
    System.out.print(minStr);
  }

  private static String getMinStr(int[] arr) {
    if (arr == null || arr.length == 0) {
      return "";
    }

    int len = arr.length;
    String[] res = new String[len];
    for (int i = 0; i < arr.length; i++) {
      res[i] = String.valueOf(arr[i]);
    }
    StringBuilder builder = new StringBuilder();
    Arrays.sort(res, (o1, o2) -> {
      String s1 = o1 + o2;
      String s2 = o2 + o1;
      return s1.compareTo(s2);
    });
    for (String re : res) {
      builder.append(re);
    }
    return builder.toString();
  }
}
