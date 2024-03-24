// (C卷,100分)- 整型数组按个位值排序（Java & JS & Python）
// 题目描述
// 给定一个非空数组（列表），其元素数据类型为整型，请按照数组元素十进制最低位从小到大进行排序，十进制最低位相同的元素，相对位置保持不变。
// 当数组元素为负值时，十进制最低位等同于去除符号位后对应十进制值最低位。
// 输入描述
// 给定一个非空数组，其元素数据类型为32位有符号整数，数组长度[1, 1000]
// 输出描述
// 输出排序后的数组
// 用例
// 输入 1,2,5,-21,22,11,55,-101,42,8,7,32
// 输出 1,-21,11,-101,2,22,42,32,5,55,7,8
// 说明 无
// 题目解析
// 额，这道题，有点简单，应该就是考察数组排序，逻辑请看下面代码
//
// JavaScript算法源码

package com.lew.algo.hw.od._03_Sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class _073_03_Sorting_IntArraySortedByUnitDigit {
    // 输入获取
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(",");
        System.out.println(getResult(arr));
        System.out.println(getResultMy(arr));
    }

    private static String getResultMy(String[] arr) {
        return Arrays.stream(arr).sorted(Comparator.comparing(str -> str.charAt(str.length() - 1)))
            .collect(Collectors.joining(","));
    }

    // 算法入口
    private static String getResult(String[] arr) {
        Arrays.sort(arr, (a, b) -> a.charAt(a.length() - 1) - b.charAt(b.length() - 1));

        StringJoiner sj = new StringJoiner(",");
        for (String s : arr)
            sj.add(s);
        return sj.toString();
    }

}