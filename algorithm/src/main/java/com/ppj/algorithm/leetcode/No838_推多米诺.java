package com.ppj.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cj
 * @since 2022/2/21 19:10
 */
public class No838_推多米诺 {

  public static void main(String[] args) {
    System.out.println(new No838_推多米诺().pushDominoes(".L.R...LR..L.."));
  }
  public String pushDominoes(String dominoes) {
    char[] chars = dominoes.toCharArray();
    boolean flag = true;
    while (flag) {
      char[] sourceChars = Arrays.copyOf(chars, chars.length);
      flag = false;
      List<Integer> indexList = new ArrayList<>();
      for (int i = 0; i < chars.length; i++) {
        if (chars[i] != '.') {
          indexList.add(i);
        }
      }
      for (Integer i : indexList) {
        final char c = chars[i];
        switch (c) {
          case 'L':
            if (i == 0) {
              break;
            } else if ((i == 1 && chars[i - 1] == '.') || (i >= 2 && chars[i - 1] == '.' && chars[i - 2] != 'R')) {
              sourceChars[i - 1] = 'L';
              if (!flag) {
                flag = true;
              }
            }
            break;
          case 'R':
            if (i == chars.length - 1) {
              break;
            } else if ((i == chars.length - 2 && chars[i + 1] == '.') || (i <= chars.length - 3 && chars[i + 1] == '.' && chars[i + 2] != 'L')) {
              sourceChars[i + 1] = 'R';
              if (!flag) {
                flag = true;
              }
            }
            break;
          case '.':
            break;
        }
      }
      chars = sourceChars;
    }
    return new String(chars);
  }

}
