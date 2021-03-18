package com.ppj.algorithm.leetcode;

import cn.hutool.core.lang.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pipi
 * @since 2021/3/17 11:01
 */
public class Q70 {
  public static final Map<Integer,Integer> map = new HashMap<>();

  static {
    map.put(0,0);
    map.put(1,1);
    map.put(2,2);
    map.put(3,3);
    map.put(4,5);
    map.put(5,8);
    map.put(6,13);
    map.put(7,21);
    map.put(8,34);
    map.put(9,55);
    map.put(10,89);
    map.put(11,144);
    map.put(12,233);
    map.put(13,377);
    map.put(14,610);
    map.put(15,987);
    map.put(16,1597);
    map.put(17,2584);
    map.put(18,4181);
    map.put(19,6765);
    map.put(20,10946);
    map.put(21,17711);
    map.put(22,28657);
    map.put(23,46368);
    map.put(24,75025);
    map.put(25,121393);
    map.put(26,196418);
    map.put(27,317811);
    map.put(28,514229);
    map.put(29,832040);
    map.put(30,1346269);
    map.put(31,2178309);
    map.put(32,3524578);
    map.put(33,5702887);
    map.put(34,9227465);
    map.put(35,14930352);
    map.put(36,24157817);
    map.put(37,39088169);
    map.put(38,63245986);
    map.put(39,102334155);
    map.put(40,165580141);
    map.put(41,267914296);
    map.put(42,433494437);
    map.put(43,701408733);
    map.put(44,1134903170);
  }

  public static void main(String[] args) {
    System.out.println("Map<Integer> map = new HashMap<>();");
    for (int i = 0; i < 45; i++) {
      Console.log("map.put({},{});", i, new Q70().climbStairs(i));
    }
  }

  public int climbStairs(int n) {
    if (n == 0 || n == 1 || n == 2) {
      return n;
    }
    return climbStairs(n - 1) + climbStairs(n - 2);
  }

}
