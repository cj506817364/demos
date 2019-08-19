package com.ppj.algorithm.leetcode;

/**
 * 无重复的最长子串的长度
 *
 * @author cj
 * @date 2019-05-22 17:56
 */
public class No3 {

    public static void main(String[] args) {

        String s = "pwwkew";
        int i = lengthOfLongestSubstring(s);
        System.out.println("result: " + i);
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        int maxLength = 0;
        int tempLength = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if(chars[i] == chars[j]){
                    if(maxLength < tempLength){
                        maxLength = tempLength;
                    }
                    tempLength = 0;
                    j++;
                    break;
                }
                tempLength++;
            }
        }
        return maxLength;
    }
}
