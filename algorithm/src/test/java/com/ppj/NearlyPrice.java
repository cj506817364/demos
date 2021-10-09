package com.ppj;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @since 2021/9/15 18:25
 * @author pipi
 */
public class NearlyPrice {

  public static void main(String[] args) {
    int[] a = {560, 589, 1890, 2990, 3790, 3380, 3180};
    int target = 8900;
    int nearly = Integer.MAX_VALUE;
    List<Integer> nearlyList = null;
    for (int length = a.length; length > 0; length--) {
      final List<Integer> closestElements = findClosestElements(a, length, target);
      int sum = closestElements.stream().mapToInt(value -> value).sum();
      if (sum < nearly && sum >= target) {
        nearly = sum;
        nearlyList = closestElements;
      }
    }
    System.out.println(nearly + ": " + nearlyList);
  }

  public static List<Integer> findClosestElements(int[] arr, int k, int x) {
    List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
    int n = ret.size();
    if (x <= ret.get(0)) {
      return ret.subList(0, k);
    } else if (ret.get(n - 1) <= x) {
      return ret.subList(n - k, n);
    } else {
      int index = Collections.binarySearch(ret, x);
      if (index < 0) index = -index - 1;
      int low = Math.max(0, index - k - 1), high = Math.min(ret.size() - 1, index + k - 1);

      while (high - low > k - 1) {
        if ((x - ret.get(low)) <= (ret.get(high) - x)) high--;
        else if ((x - ret.get(low)) > (ret.get(high) - x)) low++;
        else System.out.println("unhandled case: " + low + " " + high);
      }
      return ret.subList(low, high + 1);
    }
  }
}
