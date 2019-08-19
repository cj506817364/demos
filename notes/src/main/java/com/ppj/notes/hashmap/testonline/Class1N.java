package com.ppj.notes.hashmap.testonline;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author cj
 * @date 2019-08-11 15:48
 */
public class Class1N {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String[] val = in.nextLine().split(",");
            int[] x = new int[val.length];
            for (int i = 0; i < val.length; i++) {
                x[i] = Integer.parseInt(val[i]);
            }
            System.out.println(maximumGap(x));
        }

    }

    public static int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i+1 >= nums.length ){
                return max;
            }
            max = sub(nums[i],nums[i+1]);
        }
        return max;
    }

    public static int sub(int i, int j) {
        return i > j ? i - j : j - i;
    }

}
