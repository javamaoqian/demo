package com.mao.algorithm;

import sun.plugin2.message.ShowStatusMessage;

import java.util.Arrays;

/**
 * @Author: mq
 * @Date: 2021/4/25 15:41
 */
public class Sort {
    public static void main(String[] args) {
        int[] a = new int[]{3, 6, 7, 3, 24, 13, 656, 2, 434, 89};
        mergeSort(a, new int[a.length], 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    // 归并排序
    public static void mergeSort(int[] a, int[] res, int start, int end) {
        if (start >= end) {
            return;
        }
        int md = (start + end) / 2;
        mergeSort(a, res, start, md);
        mergeSort(a, res, md + 1, end);
        int arr1s = start, arr2s = md + 1, arr1e = md, arr2e = end;
        int tempstart = start;
        while (arr1s <= arr1e && arr2s <= arr2e) {
            res[tempstart++] = a[arr1s] > a[arr2s] ? a[arr2s++] : a[arr1s++];
        }
        while (arr1s <= arr1e) {
            res[tempstart++] = a[arr1s++];
        }

        while (arr2s <= arr2e) {
            res[tempstart++] = a[arr2s++];
        }

        for (int i = start; i <= end; i++) {
            a[i] = res[i];
        }
    }


    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    // 快排
    public static void quickSort(int[] a, int lowIndex, int highIndex) {
        int low = lowIndex;
        int high = highIndex;
        int compare = a[low];
        while (low < high) {
            while (a[high] > compare) {
                high--;
            }
            if (low >= high) {
                break;
            }
            a[low] = a[high];
            low++;
            while (a[low] < compare && low < high) {
                low++;
            }
            if (low >= high) {
                break;
            }
            a[high] = a[low];
            high--;
        }
        a[low] = compare;
        if (low - lowIndex > 2) {
            quickSort(a, lowIndex, low - 1);
        }
        if (highIndex - low > 2) {
            quickSort(a, low + 1, highIndex);
        }
    }


    // 冒泡排序

}
