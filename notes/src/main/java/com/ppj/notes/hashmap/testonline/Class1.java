package com.ppj.notes.hashmap.testonline;

import java.util.Scanner;

/**
 * @author cj
 * @date 2019-08-11 15:48
 */
public class Class1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }
}
