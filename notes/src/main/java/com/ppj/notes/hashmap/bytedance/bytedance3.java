package com.ppj.notes.hashmap.bytedance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author cj
 * @date 2019-08-11 19:02
 */
public class bytedance3 {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(test(0, new int[]{1, 2, 3, 4, 5})));
//        System.out.println(Arrays.toString(test(0, new int[]{1, 1, 1})));
//        System.out.println(Arrays.toString(test(0, new int[]{3, 9, 2, 7})));
        System.out.println(Arrays.toString(test(4, new int[]{8, 8, 8, 8})));
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String[] s = s2.split(" ");
        int[] ints = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            ints[i] = Integer.parseInt(s[i]);
        }
        System.out.println(Arrays.toString(test(Integer.parseInt(s1), ints)));
    }

    private static int[] test(int num, int[] s) {
        int[] ints = new int[s.length];
        for (int i = 0; i < num; i++) {
            // 最后一个
            if (i == ints.length - 1) {
                if (gt(s[i], s[i - 1])) {
                    ints[i] = ints[i - 1] + 100;
                } else {
                    ints[i] = 100;
                }
                continue;
            }
            // 第一个
            if (i == 0) {
                if (gt(s[i], s[i + 1])) {
                    ints[i] = 200;
                } else {
                    ints[i] = 100;
                }
                continue;
            }
            // 其他情况
            if (gt(s[i], s[i + 1]) || gt(s[i], s[i - 1])) {
                ints[i] = ints[i - 1] + 100;
            } else {
                ints[i] = 100;
            }
        }
        return ints;
    }

    private static boolean gt(int i, int j) {
        return i > j;
    }
}
