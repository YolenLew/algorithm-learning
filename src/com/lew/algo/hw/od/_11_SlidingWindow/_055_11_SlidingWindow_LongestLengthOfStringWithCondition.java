// (C卷,100分)- 求满足条件的最长子串的长度（Java & JS & Python & C）
// 题目描述
// 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，字符串本身是其最长的子串，子串要求：
//
// 1、 只包含1个字母(a~z, A~Z)，其余必须是数字；
//
// 2、 字母可以在子串中的任意位置；
//
// 如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
//
// 输入描述
// 字符串(只包含字母和数字)
//
// 输出描述
// 子串的长度
//
// 用例
// 输入 abC124ACb
// 输出 4
// 说明 满足条件的最长子串是C124或者124A，长度都是4
// 输入 a5
// 输出 2
// 说明 字符串自身就是满足条件的子串，长度为2
// 输入 aBB9
// 输出 2
// 说明 满足条件的子串为B9，长度为2
// 输入 abcdef
// 输出 -1
// 说明 没有满足要求的子串，返回-1
// 题目解析
// 此题可以用滑动窗口求解。
//
// 滑动窗口的左指针开始指向索引0，右指针也是从索引0开始不断向右移动，当右指针遇到字母时，则滑动窗口内部含字母量+1。

package com.lew.algo.hw.od._11_SlidingWindow;

import java.util.LinkedList;
import java.util.Scanner;

public class _055_11_SlidingWindow_LongestLengthOfStringWithCondition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(getResult(input));
        System.out.println(getResult01(input));
        System.out.println(getResultMy(input));
    }

    private static int getResult(String str) {
        int maxLen = -1;
        boolean hasLetter = false;

        int l = 0, r = 0;
        LinkedList<Integer> letterIdx = new LinkedList<>();

        while (r < str.length()) {
            char c = str.charAt(r);
            // if (Character.isLetter(c)) {
            if (isLetter(c)) {
                hasLetter = true;
                letterIdx.add(r);

                if (letterIdx.size() > 1) {
                    l = letterIdx.removeFirst() + 1;
                }

                if (r == l) {
                    r++;
                    continue;
                }
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        if (!hasLetter)
            return -1;
        return maxLen;
    }

    private static int getResultMy(String s) {
        int alphaNum = 0;
        int digitNum = 0;
        int ans = -1;
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);

            if (Character.isDigit(ch)) {
                digitNum++;
            } else if (Character.isLetter(ch)) {
                alphaNum++;
            }

            // 缩小窗口判断
            while (alphaNum >= 2) {
                char leftCh = s.charAt(left);
                if (Character.isDigit(leftCh)) {
                    digitNum--;
                } else if (Character.isLetter(leftCh)) {
                    alphaNum--;
                }
                left++;
            }

            // 更新结果
            if (alphaNum == 1 && digitNum > 0) {
                ans = Math.max(ans, right - left + 1);
            }

            // 移动右指针扩大窗口
            right++;
        }

        return ans;

    }

    private static int getResult01(String s) {
        int alphaNum = 0;
        int digitNum = 0;
        int ans = -1;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (Character.isDigit(ch)) {
                digitNum++;
            } else if (Character.isLetter(ch)) {
                alphaNum++;
            }

            if (alphaNum == 2) {
                while (alphaNum == 2) {
                    if (Character.isDigit(s.charAt(left))) {
                        digitNum--;
                    } else if (Character.isLetter(s.charAt(left))) {
                        alphaNum--;
                    }
                    left++;
                }
            }

            if (alphaNum == 1 && digitNum > 0) {
                ans = Math.max(ans, digitNum + 1);
            }
        }

        return ans;
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}