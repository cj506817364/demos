package com.ppj.algorithm.leetcode;

/**
 * @author pipi
 * @since 2021/7/14 11:21
 */
public class No1818_绝对差值和 {

  public static void main(String[] args) {
    final int i =
        new No1818_绝对差值和()
            .minAbsoluteSumDiff(new int[] {1,7,5}, new int[] {2,3,5});
    System.out.println(i);
  }

  public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
    int sum = 0, max = 0, n = nums1.length, mod = (int) 1e9 + 7;
    for (int i = 0; i < n; i++) { // 两次循环看能每个元素最多能节省多少
      int n2 = nums2[i], originAbs = Math.abs(nums1[i] - n2), minAbs = Integer.MAX_VALUE;
      for (int j = 0; j < n; j++) minAbs = Math.min(minAbs, Math.abs(n2 - nums1[j]));
      max = Math.max(max, originAbs - minAbs);
      sum += originAbs; // abs求和
    }
    return (sum - max) % mod;
  }

  //
  //    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
  //          final int MOD = 1000000007;
  //          int n = nums1.length;
  //          int[] rec = new int[n];
  //          System.arraycopy(nums1, 0, rec, 0, n);
  //          Arrays.sort(rec);
  //          int sum = 0, maxn = 0;
  //          for (int i = 0; i < n; i++) {
  //              int diff = Math.abs(nums1[i] - nums2[i]);
  //              sum = (sum + diff) % MOD;
  //              int j = binarySearch(rec, nums2[i]);
  //              if (j < n) {
  //                  maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
  //              }
  //              if (j > 0) {
  //                  maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
  //              }
  //          }
  //          return (sum - maxn + MOD) % MOD;
  //      }
  //
  //      public int binarySearch(int[] rec, int target) {
  //          int low = 0, high = rec.length - 1;
  //          if (rec[high] < target) {
  //              return high + 1;
  //          }
  //          while (low < high) {
  //              int mid = (high - low) / 2 + low;
  //              if (rec[mid] < target) {
  //                  low = mid + 1;
  //              } else {
  //                  high = mid;
  //              }
  //          }
  //          return low;
  //      }
  //  }

}
