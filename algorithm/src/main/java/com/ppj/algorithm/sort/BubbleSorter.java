package com.ppj.algorithm.sort;

/**
 * 冒泡排序,每次循环后,最大的元素会被移动到最后面,如果没有产生任何移动操作,则数组已经有序,不需要多余循环
 * 时间复杂度:
 * 平均: O(n^2)
 * 最坏: O(n^2)
 * 最好: O(n)
 */
public class BubbleSorter implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    SorterUtil.swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) break;
        }
    }

}
