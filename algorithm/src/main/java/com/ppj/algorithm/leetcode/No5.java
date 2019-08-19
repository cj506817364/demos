package com.ppj.algorithm.leetcode;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cj
 * Date: 2019-03-25 14:38
 * Description:
 */
public class No5 {

    public static void main(String[] args) {


        List<Person> personList = new ArrayList<>();
        personList.add(new Person(null));
        personList.add(new Person(BigDecimal.ONE));
        personList.add(new Person(BigDecimal.TEN));
        personList.add(new Person(BigDecimal.ZERO));
        BigDecimal reduce = personList.stream()
                .filter(person -> person != null && person.getMoney() != null)
                .map(Person::getMoney)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(reduce);

        System.out.println(new No5().longestPalindrome("asdbdbdbdb"));

    }

    static class Person {
        private BigDecimal money;

        public Person(BigDecimal money) {
            this.money = money;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
