package com.ppj.algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pipi
 * @since 2021/3/25 12:12
 */
public class 有效的数独 {


  public static void main(String[] args) {
    final char[][] ints = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    System.out.println(new 有效的数独().isValidSudoku(ints));
  }

  // 0-9行 -> key 0-9
  // 0-9列 -> key 10-19
  // 0-9块 -> key 20-29
  public boolean isValidSudoku(char[][] board) {
    Map<Integer, Set<Character>> setMap = new HashMap<>();

    for (int hang = 0; hang < board.length; hang++) {
      final char[] chars = board[hang];
      for (int lie = 0; lie < chars.length; lie++) {
        if (putToMapAndCheck(hang, setMap, chars[lie])) {
          return false;
        }
        if (putToMapAndCheck(lie + 10, setMap, chars[lie])) {
          return false;
        }
        if (putToMapAndCheck(calculateKuai(hang, lie) + 20, setMap, chars[lie])) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean putToMapAndCheck(int flag, Map<Integer, Set<Character>> setMap, char aChar) {
    if (aChar == '.') {
      return false;
    }
    Set<Character> characters = setMap.computeIfAbsent(flag, k -> new HashSet<>());
    if (characters.contains(aChar)) {
      return true;
    }
    characters.add(aChar);
    return false;
  }

  private int calculateKuai(int hang, int lie) {
    return (hang / 3) * 3 + (lie / 3);
  }
}
