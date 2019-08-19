package com.ppj.algorithm.sort;

/**
 * 选择排序: 每次遍历,都把整个数组中最小的元素放在第i个位置(前i-1个元素已经排好序了)
 * 时间复杂度:
 * 平均: O(n^2)
 * 最坏: O(n^2)
 * 最好: O(n^2)
 */
public class SelectionSorter implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            if (i != index) {
                SorterUtil.swap(arr, i, index);
            }
        }

    }

}
