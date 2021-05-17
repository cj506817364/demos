//package com.ppj.algorithm.leetcode;
//
///**
// * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
// *
// * @author pipi
// * @since 2021/5/14 14:59
// */
//public class No4_寻找两个正序数组的中位数 {
//
//  public static void main(String[] args) {
//    //
//    System.out.println(
//        new No4_寻找两个正序数组的中位数().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
//  }
//
//  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//    int midNum;
//    int nexNum;
//    int len1 = nums1.length;
//    int len2 = nums2.length;
//    int midIndex = len1 + len2 / 2;
//    int i1 = 0;
//    int i2 = 0;
//    boolean breakFlag = false;
//
//    for (int i = 0; i < len1 + len2; i++) {
//      if (i == midIndex){
//        breakFlag = true;
//      }
//      int res;
//      if (nums1[i1] < nums2[i2]) {
//
//      }
//      if(breakFlag) {
//        nexNum = res;
//        break;
//      }else {
//        midNum = res;
//      }
//    }
//  }
//}
