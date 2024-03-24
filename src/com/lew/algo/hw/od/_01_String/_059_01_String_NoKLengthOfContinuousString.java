// (C卷,100分)- 连续字母长度（Java & JS & Python）
// 题目描述
// 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
//
// 输入描述
// 第一行有一个子串(1<长度<=100)，只包含大写字母。
//
// 第二行为 k的值
//
// 输出描述
// 输出连续出现次数第k多的字母的次数。
//
// 用例
// 输入
// AAAAHHHBBCDHHHH
// 3
//
// 输出 2
// 说明
// 同一字母连续出现的最多的是A和H，四次；
//
// 第二多的是H，3次，但是H已经存在4个连续的，故不考虑；
//
// 下个最长子串是BB，所以最终答案应该输出2。
//
// 输入
// AABAAA
// 2
//
// 输出 1
// 说明
// 同一字母连续出现的最多的是A，三次；
//
// 第二多的还是A，两次，但A已经存在最大连续次数三次，故不考虑；
//
// 下个最长子串是B，所以输出1。
//
// 输入 ABC
// 4
// 输出 -1
// 说明 只含有3个包含同一字母的子串，小于k，输出-1
// 输入 ABC
// 2
// 输出 1
// 说明 三个子串长度均为1，所以此时k = 1，k=2，k=3这三种情况均输出1。特此说明，避免歧义。

package com.lew.algo.hw.od._01_String;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _059_01_String_NoKLengthOfContinuousString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        System.out.println(getResult(s, k));
        System.out.println(getResultMy(s, k));
    }

    public static int getResultMy(String s, int k) {
        s += '0';
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        char lastChar = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (lastChar == currentChar) {
                count++;
            } else {
                // 结果记录
                if (!charCountMap.containsKey(lastChar) || charCountMap.get(lastChar) < count) {
                    charCountMap.put(lastChar, count);

                }
                // 开始新一轮统计，重置计数器
                lastChar = currentChar;
                count = 1;
            }
        }

        List<Integer> countList =
            charCountMap.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (countList.size() < k) {
            return -1;
        } else {
            return countList.get(k - 1);
        }

    }

    public static int getResult(String s, int k) {
        if (k <= 0)
            return -1;

        // 方便收尾操作，如果没有这一步的话，那么输入字符串最后两个字符如果相同的话，会走进24，25的逻辑，然后结束21的for循环。
        // 而最终统计是27，28行逻辑，为了避免后面重复写27，28行逻辑，因此给s+=''0"，这样的话，输入字符串的最后两字符肯定不同，也就会走进27,28，且拼接的"0"不会被统计到。
        s += "0";

        HashMap<Character, Integer> count = new HashMap<>();

        char lastChar = s.charAt(0);
        int len = 1;

        for (int i = 1; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (lastChar == currentChar) {
                len++;
            } else {
                if (!count.containsKey(lastChar) || count.get(lastChar) < len) {
                    count.put(lastChar, len);
                }
                // 开始新一轮统计，重置计数器
                len = 1;
                lastChar = currentChar;
            }
        }

        Integer[] arr = count.values().toArray(new Integer[0]);

        if (k > arr.length)
            return -1;
        else {
            Arrays.sort(arr, Comparator.reverseOrder());
            // List<Integer> lengthList =
            // count.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            // System.out.println(lengthList.get(k - 1));
            return arr[k - 1];
        }
    }
}