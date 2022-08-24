package com.ppj.algorithm.nowcoder;

import java.util.Scanner;

/**
 * @author cj
 * @since 2022/2/27 09:36
 */
public class HJ1_字符串最后一个单词的长度 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    int len = line.length() - line.lastIndexOf(" ");
    System.out.print(len);
  }

}
