// (C卷,100分)- 小明找位置（Java & JS & Python & C）
// 题目描述
// 小朋友出操，按学号从小到大排成一列；
//
// 小明来迟了，请你给小明出个主意，让他尽快找到他应该排的位置。
//
// 算法复杂度要求不高于nLog(n)；学号为整数类型，队列规模 ≤ 10000；
//
// 输入描述
// 第一行：输入已排成队列的小朋友的学号（正整数），以","隔开；例如：
//
// 93,95,97,100,102,123,155
//
// 第二行：小明学号，如：
//
// 110
//
// 输出描述
// 输出一个数字，代表队列位置（从1开始）。例如：
//
// 6
//
// 用例
// 输入 93,95,97,100,102,123,155
// 110
// 输出 6
// 说明 无
// 题目解析
// 本题应该不会存在重复学号问题，因此本题其实就是在一个有序数组中寻找目标值的有序插入位置，可以使用二分法。
//
// 关于二分法在有序数组中查找目标值有序插入位置的实现，可以参考：

package com.lew.algo.hw.od._02_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class _027_02_BinarySearch_FindLocationForMing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numsStr = sc.nextLine();
        int target = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(numsStr.split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(target, nums));
        System.out.println(getResultMy(target, nums));
    }

    private static int getResultMy(int target, int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left + 1;
    }

    private static int getResult(int target, int[] nums) {
        // 这里Arrays.binarySearch找不到目标值时，就会返回-low-1，其中low就是目标值的有序插入位置，为什么这么设计返回值，请看解析中外链的博客说明
        int idx = Arrays.binarySearch(nums, target);

        if (idx < 0) {
            idx = -idx - 1;
        }
        // 队列位置（从1开始），因此索引+1
        return idx + 1;
    }

}