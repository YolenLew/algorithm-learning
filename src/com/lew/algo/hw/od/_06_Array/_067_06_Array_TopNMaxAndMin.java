// (C卷,100分)- 最大N个数与最小N个数的和（Java & JS & Python）
// 题目描述
// 给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。
//
// 说明：
//
// 数组中数字范围[0, 1000]
// 最大N个数与最小N个数不能有重叠，如有重叠，输入非法返回-1
// 输入非法返回-1
// 输入描述
// 第一行输入M， M标识数组大小
// 第二行输入M个数，标识数组内容
// 第三行输入N，N表达需要计算的最大、最小N个数
// 输出描述
// 输出最大N个数与最小N个数的和
//
// 用例
// 输入
// 5
// 95 88 83 64 100
// 2
//
// 输出 342
// 说明 最大2个数[100,95],最小2个数[83,64], 输出为342。
// 输入
// 5
// 3 2 3 4 2
// 2
//
// 输出 -1
// 说明 最大2个数[4,3],最小2个数[3,2], 有重叠输出为-1。
// 题目解析
// 简单的逻辑题。主要应该是考察数组常用方法的使用，以及数组去重如何实现。

package com.lew.algo.hw.od._06_Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class _067_06_Array_TopNMaxAndMin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }

        int n = sc.nextInt();

        System.out.println(getResult(m, arr, n));
        System.out.println(getResultMy(m, arr, n));
    }

    public static int getResultMy(int m, int[] arr, int n) {
        // 数组去重
        HashSet<Integer> numsSet = new HashSet<>();
        for (int num : arr) {
            numsSet.add(num);
        }

        if (numsSet.size() < 2 * n) {
            // 最大N个数与最小N个数不能有重叠，如有重叠，输入非法返回-1
            return -1;
        }

        Integer[] targetNums = numsSet.toArray(new Integer[numsSet.size()]);
        Arrays.sort(targetNums);
        int left = 0, right = targetNums.length - 1;
        int sum = 0;
        while (n > 0) {
            sum += targetNums[left] + targetNums[right];
            left++;
            right--;
            n--;
        }
        return sum;
    }

    // 考察数组常用方法的使用，以及数组去重如何实现。
    public static int getResult(int m, int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();

        for (int val : arr) {
            if (val < 0 || val > 1000)
                return -1;
            set.add(val);
        }

        if (set.size() < n * 2)
            return -1;

        Integer[] distinct_arr = set.toArray(new Integer[0]);

        Arrays.sort(distinct_arr, (a, b) -> a - b);

        int l = 0;
        int r = distinct_arr.length - 1;
        int ans = 0;

        while (n > 0) {
            ans += distinct_arr[l] + distinct_arr[r];
            l++;
            r--;
            n--;
        }

        return ans;
    }
}