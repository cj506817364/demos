package com.ppj.notes.hashmap.bytedance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author cj
 * @date 2019-08-11 19:02
 */
public class bytedance2 {

    // 1001010
    //  1001010
    //   1001010
    //    1001010
    // 1110100110

    // 1110100110
    // 01110100110
    // 001110100110
    // 0001110100110
    public static void main(String[] args) {

//        System.out.print(1 ^ 0 ^ 0 ^ 0);
//        System.out.print(0 ^ 1 ^ 0 ^ 0);
//        System.out.print(0 ^ 0 ^ 1 ^ 0);
//        System.out.print(1 ^ 0 ^ 0 ^ 1);
//        System.out.print(0 ^ 0 ^ 1 ^ 0);
//        System.out.print(0 ^ 1 ^ 0 ^ 1);
//        System.out.print(1 ^ 0 ^ 1 ^ 0);
//        System.out.print(0 ^ 1 ^ 0);
//        System.out.print(1 ^ 0);
//        System.out.print(0);
        int n = 7;
        int k = 4;
        String str = "1110100110";
        System.out.println(test(n, k, str));
//        Scanner sc = new Scanner(System.in);
//        String s1 = sc.nextLine();
//        String s2 = sc.nextLine();
//        String[] s = s1.split(" ");
//        int[] ints = new int[s.length];
//        for (int i = 0; i < s.length; i++) {
//            ints[i] = Integer.parseInt(s[i]);
//        }
//        System.out.println(Arrays.toString(test(Integer.parseInt(s1), ints)));

    }

    private static String test(int n, int k, String str) {
        String[] temp = new String[k];
        for (int i = 0; i < k; i++) {
            StringBuilder pre = new StringBuilder();
            for (int j = 0; j < i; j++) {
                pre.append("0");
            }
            temp[i] = pre + str;
        }
        StringBuilder reStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int result = 0;
            for (int j = 0; j < temp.length; j++) {
                if (j == 0) {
                    result = getByIndexToInt(temp[j], i);
                } else {
                    result = result ^ getByIndexToInt(temp[j], i);
                }
            }
            reStr.append(result);
        }

        return reStr.toString();
    }

    private static int getByIndexToInt(String s, int i) {
        if(s.length() < i){
            return 0;
        }
        return Integer.parseInt(s.substring(i, i + 1));
    }

}
