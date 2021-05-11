package com.mao.tree;

import java.util.Arrays;

/**
 * @Author: mq
 * @Date: 2021/5/11 19:39
 */
public class Sort {


    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 2, 7, 4, 54, 32, 88};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    /** 快排 */
    public static void quickSort(int[] a, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        // 取start位置的数作为比较数
        int compare = a[startIndex];
        int high = endIndex;
        int low = startIndex;
        while (low < high) {
            while (a[high] >= compare && low < high) {
                high--;
            }
            if (low == high) {
                break;
            }
            a[low] = a[high];
            low++;
            while (a[low] < compare && low < high) {
                low++;
            }
            if (low == high) {
                break;
            }
            a[high] = a[low];
            high--;
        }
        a[low] = compare;

        quickSort(a, startIndex, low - 1);
        quickSort(a, low + 1, endIndex);
    }

    /**
     * 堆排序
     */

    public static void heapSort(int[] heapArray) {

    }

    /** 转换成堆 */
    public static void heapify(int[] a, int i, int length) {
        // 查找i的孩子中较大的一个
        int left = i << 1 + 1;
        int right = left + 1;

    }

    /** 堆内元素上浮 */
    public static void up(int[] a, int index) {

    }

}
