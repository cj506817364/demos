package com.ppj.algorithm.leetcode;

/**
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 *
 * <p>字符 数值
 *
 * <p>I 1
 *
 * <p>V 5
 *
 * <p>X 10
 *
 * <p>L 50
 *
 * <p>C 100
 *
 * <p>D 500
 *
 * <p>M 1000
 *
 * <p>例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 *
 * <p>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4
 * 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * <p>I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 *
 * <p>X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 *
 * <p>C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 *
 * <p>给你一个整数，将其转为罗马数字。
 *
 * <p>输入: num = 1994
 *
 * <p>输出: "MCMXCIV"
 *
 * <p>释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author pipi
 * @since 2021/5/14 10:47
 */
public class No12_整数转罗马数字 {

  public static void main(String[] args) {
    // 1 <= num <= 3999
    System.out.println(new No12_整数转罗马数字().intToRoman(1994));
  }

  private int cal(int num, int dis, String sign, StringBuilder res) {
    while (num >= dis) {
      num -= dis;
      res.append(sign);
    }
    return num;
  }

  public String intToRoman(int num) {
    StringBuilder res = new StringBuilder();
    num = cal(num, 1000, "M", res);
    // 0=<num<=999
    num = cal(num, 900, "CM", res);
    // 0<=num<=899
    num = cal(num, 500, "D", res);
    num = cal(num, 400, "CD", res);
    num = cal(num, 100, "C", res);
    num = cal(num, 90, "XC", res);
    num = cal(num, 50, "L", res);
    num = cal(num, 40, "XL", res);
    num = cal(num, 10, "X", res);
    num = cal(num, 9, "IX", res);
    num = cal(num, 5, "V", res);
    num = cal(num, 4, "IV", res);
    num = cal(num, 1, "I", res);
    return res.toString();
  }
}
