package com.ppj.algorithm.leetcode;

/**
 * 给你一个整数数组 arr 。
 *
 * <p>现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *
 * <p>a 和 b 定义如下：
 *
 * <p>a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 注意：^ 表示 按位异或
 * 操作。
 *
 * <p>请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 *
 * @author pipi
 * @since 2021/5/18 18:53
 */
public class No1442_形成两个异或相等数组的三元组数目 {

  public static void main(String[] args) {
    System.out.println(new No1442_形成两个异或相等数组的三元组数目().countTriplets(new int[] {2, 3, 1, 6, 7}));
  }

  // arr[i] ^ ... ^ arr[n - 1] = (arr[0] ^ arr[i - 1]) ^ (arr[0] ^ ... ^ arr[n - 1]
  public int countTriplets(int[] arr) {
    int n = arr.length, ans = 0;
    // 创建异或数组 xorArr[i] 表示前i项的异或值
    int[] xorArr = new int[arr.length + 1];
    for (int i = 0; i < n; i++) {
      xorArr[i + 1] = xorArr[i] ^ arr[i];
    }
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        for (int k = j; k < n; k++) {
          // arr[i] ^ ... ^ arr[j-1] = xor[i - 1] ^ xor [j - 1]
          int a = xorArr[i] ^ xorArr[j];
          int b = xorArr[j] ^ xorArr[k + 1];
          if (a == b) ans++;
        }
      }
    }
    return ans;
  }

  //  暴力破解 枚举出所有情况
  //  public int countTriplets(int[] arr) {
  //    int n = arr.length, ans = 0;
  //    for (int i = 0; i < n; i++) {
  //      for (int j = i + 1; j < n; j++) {
  //        for (int k = j; k < n; k++) {
  //          int a = 0, b = 0;
  //          for (int x = i; x < j; x++) a ^= arr[x];
  //          for (int y = j; y <= k; y++) a ^= arr[y];
  //          if (a == b) ans++;
  //        }
  //      }
  //    }
  //    return ans;
  //  }

  //  public int countTriplets(int[] arr) {
  //    int n = arr.length, ans = 0;
  //    int[] preXor = new int[n + 1];
  //    for (int i = 0; i < n; ++i) preXor[i + 1] = preXor[i] ^ arr[i];
  //    for (int i = 1; i <= n; ++i)
  //      for (int k = i + 1; k <= n; ++k)
  //        if (preXor[i - 1] == preXor[k]) ans += k - i;
  //    return ans;
  //  }
}
