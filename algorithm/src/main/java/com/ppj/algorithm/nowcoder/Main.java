package com.ppj.algorithm.nowcoder;


import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int[] input =new int[10];
    int tmp =0;
    for(int i =0;i<10;i++){
      tmp=in.nextInt();
      if(tmp==0){
        break;
      }
      input[i] = tmp;
    }
    for(int i =0;i<10;i++){
      if(input[i]!=0){
        System.out.println(Main.calNum(input[i]));
      }
    }
  }

  public static int calNum(int n) {
    int res = 0;
    int empty = n;
    while (empty >=3) {
      res += 1;
      empty-=2;
    }
    if (empty == 2) {
      return res+1;
    }
    return res;
  }
}

