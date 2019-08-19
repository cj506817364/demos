package com.ppj.algorithm.sort;

/**
 * 插入排序: 前i个元素必然有序,每次将第i+1个元素插入到正确位置
 * 时间复杂度:
 * 平均: O(n^2)
 * 最坏: O(n^2)
 * 最好: O(n)
 */
public class InsertionSorter implements Sorter {

    @Override
    public void sort(int[] arr) {

        if (arr == null || arr.length <= 1) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {

            int elem = arr[i];
            int j = i - 1;
            while (elem < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
                if (j < 0) {
                    break;
                }
            }

            arr[j + 1] = elem;


//            int index = i;//需要和前一个元素比较的元素的下标
//            while(arr[index] < arr[index - 1]){
//
//                SorterUtil.swap(arr,index,index - 1);
//                index--;
//                if(index <= 0){
//                    break;
//                }
//            }
        }

    }

}
