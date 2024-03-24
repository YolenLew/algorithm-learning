// (C卷,100分)- 字符串序列判定（Java & JS & Python & C）
// 题目描述
// 输入两个字符串 S 和 L ，都只包含英文小写字母。S长度 ≤ 100，L长度 ≤ 500,000。判定S是否是L的有效子串。
//
// 判定规则：S 中的每个字符在 L 中都能找到（可以不连续），且 S 在Ｌ中字符的前后顺序与 S 中顺序要保持一致。（例如，S = ”ace” 是 L= ”abcde”
// 的一个子序列且有效字符是a、c、e，而”aec”不是有效子序列，且有效字符只有a、e）
//
// 输入描述
// 输入两个字符串 S 和 L，都只包含英文小写字母。S长度 ≤ 100，L长度 ≤ 500,000。
//
// 先输入S，再输入L，每个字符串占一行。
//
// 输出描述
// S 串最后一个有效字符在 L 中的位置。（首位从0开始计算，无有效字符返回-1）
//
// 用例
// 输入
// ace
// abcde
//
// 输出 4
// 说明 无
// 输入
// fgh
// abcde
//
// 输出 -1
// 说明 无
// 题目解析
// 本题可以利用双指针来解决。

package com.lew.algo.hw.od._15_DoubleIndex;

import java.util.Scanner;

public class _061_15_DoubleIndex_StringSequenceDecision {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String l = sc.nextLine();

        System.out.println(getResult(s, l));
    }

    public static int getResult(String s, String l) {
        // 定义两个指针 i , j，分别指向S，L 字符串的索引0位置，
        //
        // 当 S[i] 和 L[j] 的字符相同时，则 i++ 且 j++
        // 当 S[i] 和 L[j] 的字符不同时，则仅 j++
        // 当 i ≥ S.length || J ≥ l.length 时结束
        //
        // 如果最后，i == S.length，则说明，在 L 字符串中找到了所有的 S 字符串字符。且 S 字符串最后一个字符在 L 中的位置就是 j - 1。否则，就返回-1。
        int i = 0;
        int j = 0;

        while (i < s.length() && j < l.length()) {
            if (s.charAt(i) == l.charAt(j)) {
                i++;
            }
            j++;
        }

        if (i == s.length())
            return j - 1;
        else
            return -1;
    }
}