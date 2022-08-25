package com.ppj.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author cj
 * @since 2022/3/1 09:57
 */
public class No6_Z字形变换 {

  public static void main(String[] args) {
    System.out.println(new No6_Z字形变换().convert("PAYPALISHIRING", 3));
  }

  public String convert(String s, int numRows) {
    int width = calWidth(s.length(), numRows);
    String[][] arr = new String[width][numRows];
    if (numRows == 1) {
      return s;
    }

    for (int i = 0; i < arr.length; i++) {
      final String[] lie = arr[i];
      for (int j = 0; j < lie.length; j++) {
//        lie[j] = ;
      }
    }
    return Arrays.deepToString(arr);
  }

  private int calWidth(int length, int numRows) {
    if (numRows == 1) {
      return length;
    }
    int width = 0;
    if (numRows == 2) {
      do {
        width++;
      } while ((length -= 2) > 0);
      return width;
    }

    // numRows 大于2 有Z形变换
    int onceTimes = numRows - 2;
    while (true) {
      if (length == 0) {
        return width;
      }
      if (length <= numRows) {
        return ++width;
      }
      // 大于行数
      length -= numRows;
      width++;
      if (length <= onceTimes) {
        return width + length;
      }
      length -= onceTimes;
      width += onceTimes;
    }

  }
}
