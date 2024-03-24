// (B卷,100分)- 数组去重和排序（Java & JS & Python）
// 题目描述
// 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行先后排序。
//
// 输入描述
// 一个数组
//
// 输出描述
// 去重排序后的数组
//
// 用例
// 输入 1,3,3,3,2,4,4,4,5
// 输出 3,4,1,2,5
// 备注 数组大小不超过100 数组元素值大小不超过100。
// 题目解析
// 简单的排序问题。

package com.lew.algo.hw.od._03_Sorting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class _080_03_Sorting_SortingAndRemoveDeplicatesForArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(",");
        System.out.println(getResult(arr));
        System.out.println(getResult10(arr));
    }

    public static String getResult(String[] arr) {
        HashMap<String, Integer> count = new HashMap<>();
        HashMap<String, Integer> first = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            count.put(s, count.getOrDefault(s, 0) + 1);
            first.putIfAbsent(s, i);
        }

        StringJoiner sj = new StringJoiner(",");

        first.keySet().stream().sorted((a, b) -> {
            int countA = count.get(a);
            int countB = count.get(b);

            if (countA != countB) {
                return countB - countA;
            } else {
                int firstA = first.get(a);
                int firstB = first.get(b);
                return firstA - firstB;
            }
        }).forEach(sj::add);

        return sj.toString();
    }

    public static String getResult10(String[] arr) {
        // 使用LinkedHashMap，保留输入顺序
        LinkedHashMap<String, Integer> countMap = new LinkedHashMap<>();
        for (String s : arr) {
            countMap.put(s, countMap.getOrDefault(s, 0) + 1);
        }

        // 排序输出
        return countMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .map(Map.Entry::getKey).collect(Collectors.joining(","));
    }
}