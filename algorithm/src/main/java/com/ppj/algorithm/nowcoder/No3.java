package com.ppj.algorithm.nowcoder;

import java.util.Scanner;


/**
 * 旋转骰子
 *
 * @author cj
 * @since 2022/2/27 09:46
 */
public class No3 {

  public static void main(String[] args) {
    int[][] t = init();
    Scanner sc = new Scanner(System.in);
    for (char ch : sc.nextLine().toCharArray()) {
      int a = t[0][0];
      int b = t[0][1];
      int c = t[1][0];
      int d = t[1][1];
      int e = t[2][0];
      int f = t[2][1];
      switch (ch) {
        case 'L':
          // 左转
          t[0][0] = e;
          t[0][1] = f;
          t[1][0] = c;
          t[1][1] = d;
          t[2][0] = b;
          t[2][1] = a;
          break;
        case 'R':
          // 右转
          t[0][0] = f;
          t[0][1] = e;
          t[1][0] = c;
          t[1][1] = d;
          t[2][0] = a;
          t[2][1] = b;
          break;
        case 'F':
          // 前转
          t[0][0] = a;
          t[0][1] = b;
          t[1][0] = e;
          t[1][1] = f;
          t[2][0] = d;
          t[2][1] = c;
          break;
        case 'B':
          // 后转
          t[0][0] = a;
          t[0][1] = b;
          t[1][0] = f;
          t[1][1] = e;
          t[2][0] = c;
          t[2][1] = d;
          break;
        case 'A':
          // 逆时针转
          t[0][0] = d;
          t[0][1] = c;
          t[1][0] = a;
          t[1][1] = b;
          t[2][0] = e;
          t[2][1] = f;
          break;
        case 'C':
          // 顺时针转
          t[0][0] = c;
          t[0][1] = d;
          t[1][0] = b;
          t[1][1] = a;
          t[2][0] = e;
          t[2][1] = f;
          break;
      }
    }

    System.out.print(toString(t));
  }

  private static String toString(int[][] t) {
    StringBuilder res = new StringBuilder();
    res.append(t[0][0]);
    res.append(t[0][1]);
    res.append(t[1][0]);
    res.append(t[1][1]);
    res.append(t[2][0]);
    res.append(t[2][1]);
    return res.toString();
  }

  private static int[][] init() {
    int[][] t = new int[3][2];
    t[0][0] = 1;
    t[0][1] = 2;
    t[1][0] = 3;
    t[1][1] = 4;
    t[2][0] = 5;
    t[2][1] = 6;
    return t;
  }
}
