package com.ppj.algorithm.nowcoder;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (true) {
      int i;
      String line;
      while(true) {
        if (!sc.hasNext()){
          break;
        }
        line=sc.nextLine();
        if (line == null || line.equals("")){
          break;
        }
        i = Integer.parseInt(line);
        int[] array = new int[i];
        for (int j = 0; j < i; j++) {
          array[j] = sc.nextInt();
        }
        Set<Integer> res = calNum(array);
        for (int re : res) {
          System.out.println(re);
        }
      }
    }
  }

  private static Set<Integer> calNum(int[] array) {
    final Set<Integer> hashSet = new HashSet<>();
    for (int i : array) {
      hashSet.add(i);
    }
    final TreeSet<Integer> integers = new TreeSet<>(Comparator.naturalOrder());
    integers.addAll(hashSet);
    return integers;
  }

}

