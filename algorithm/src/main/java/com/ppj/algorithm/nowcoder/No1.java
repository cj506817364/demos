package com.ppj.algorithm.nowcoder;

import java.util.Scanner;

/**
 *
 * 给定一段引文文章片段, 若干单词组成, 单词间以空格隔间, 单词下标从0开始
 * 请翻转片段中指定区间的单词顺序并返回翻转后的内容
 *
 * @author cj
 * @since 2022/2/27 09:46
 */
public class No1 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final String line = sc.nextLine();
    int i1 = sc.nextInt();
    int i2 = sc.nextInt();
    if (i1 < 0) {
      i1 = 0;
    }
    final String[] arr = line.split(" ");
    if (i2 > arr.length - 1) {
      i2 = arr.length - 1;
    }
    if (arr.length <= 1) {
      System.out.println(line);
      return;
    }
    // [i1, i2]
    final int _arrLen = i2 - i1 + 1;
    if (_arrLen <= 0) {
      System.out.println(line);
      return;
    }
    final String[] _arr = new String[_arrLen];
    int index = 0;
    for (int i = i2; i >= i1; i--) {
      _arr[index++] = arr[i];
    }

    int tempIndex = 0;
    for (int i = 0; i < arr.length; i++) {
      if (i >= i1 && i <= i2) {
        // [1,5]
        System.out.print(_arr[tempIndex++]);
      } else {
        System.out.print(arr[i]);
      }
      System.out.print(" ");
    }
  }
}
