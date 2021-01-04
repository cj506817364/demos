package com.ppj.algorithm.leetcode;

/**
 * 给你一个二进制字符串
 * 00 => 10
 * 10 => 01
 * 返回最大二进制字符串
 *
 * @author pipi
 * @date 2020/12/30 14:10
 */
public class Q1702 {

  public static void main(String[] args) {
    final String res = new Q1702().maximumBinaryString("01111001100000110010");
    System.out.println(res);
  }

  public String maximumBinaryString(String binary) {
    while (contains20(binary)) {
      while (binary.contains("00")) {
        binary = binary.replaceAll("00", "10");
      }
      // 没有00但是 有间隔的0(1..)0
      if (contains20(binary)) {
        final int i = secondIndexOf(binary, '0');
        final char[] chars = binary.toCharArray();
        chars[i] = '1';
        chars[i - 1] = '0';
        binary = new String(chars);
      }
    }
    return binary;
  }

  private int secondIndexOf(String binary, char c) {
    int count = 0;
    final char[] chars = binary.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == c) {
        count++;
      }
      if (count == 2) {
        return i;
      }
    }
    return -1;
  }

  private boolean contains20(String binary) {
    int zeroNum = 0;
    for (char c : binary.toCharArray()) {
      if (c == '0') {
        zeroNum++;
      }
      if (zeroNum >= 2) {
        return true;
      }
    }
    return false;
  }

}
