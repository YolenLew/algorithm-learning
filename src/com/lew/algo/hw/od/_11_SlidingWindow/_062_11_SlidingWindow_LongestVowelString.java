// (C卷,100分)- 最长的指定瑕疵度的元音子串（Java & JS & Python & C）
// 题目描述
// 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。比如:
//
// “a” 、 “aa”是元音字符串，其瑕疵度都为0
// “aiur”不是元音字符串（结尾不是元音字符）
// “abira”是元音字符串，其瑕疵度为2
// 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
//
// 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。
//
// 输入描述
// 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
//
// 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
//
// 输出描述
// 输出为一个整数，代表满足条件的元音字符子串的长度。
//
// 用例
// 输入 0
// asdbuiodevauufgh
// 输出 3
// 说明 无
// 输入 2
// aeueo
// 输出 0
// 说明 无

package com.lew.algo.hw.od._11_SlidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class _062_11_SlidingWindow_LongestVowelString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int flaw = sc.nextInt();
        String s = sc.next();

        System.out.println(getResult(flaw, s));
        System.out.println(getResultMy(flaw, s));
    }

    private static int getResultMy(int flaw, String s) {
        Character[] yuan = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        HashSet<Character> yuanSet = new HashSet<>(Arrays.asList(yuan));
        boolean[] matchYuanArr = new boolean[s.length()];
        for (int i = 0; i < s.toCharArray().length; i++) {
            matchYuanArr[i] = yuanSet.contains(s.charAt(i));
        }

        // 结果及计数器初始化
        int ans = 0;
        int windowFlawCount = 0;
        // 双指针初始化: 相当于头部和尾部
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            if (!matchYuanArr[right]) {
                // 辅音瑕疵度加一
                windowFlawCount++;
            } else {
                // 元音，此时尾部符合元音的要求了，还需要头部及瑕疵度判断
                while (windowFlawCount > flaw || !matchYuanArr[left]) {
                    if (!matchYuanArr[left]) {
                        windowFlawCount--;
                    }
                    left++;
                }

                // 判断结果
                if (windowFlawCount == flaw) {
                    ans = Math.max(ans, right - left + 1);
                }
            }

            right++;
        }

        return ans;
    }

    public static int getResult(int flaw, String s) {
        Character[] yuan = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        HashSet<Character> set = new HashSet<>(Arrays.asList(yuan));

        // 记录元音的索引位置：后面使用双指针时，就是利用此集合里的索引，保证左右指针始终指向元音字符，满足题目首尾字符元音的要求
        ArrayList<Integer> yuanIndexList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i)))
                yuanIndexList.add(i);
        }

        int ans = 0;
        int n = yuanIndexList.size();

        int l = 0;
        int r = 0;

        // 当两指针之间划定的子串的瑕疵度diff 大于 指定的瑕疵度flaw时，则左指针 l++
        // 当两指针之间划定的子串的瑕疵度diff 小于 指定的瑕疵度flaw时，则右指针 r++
        // 当两指针之间划定的子串的瑕疵度diff 等于 指定的瑕疵度flaw时，则记录当前子串长度，并r++
        // 按以上逻辑，直到r指针移动到idxs数组的尾部。
        while (r < n) {
            // 瑕疵度计算
            // 字符串总长度：yuanIndexList.get(r) - yuanIndexList.get(l) + 1
            // 包含元音的个数： r - l + 1
            // 那么 瑕疵度的个数就是 = 字符串总长度 - 元音的个数
            int diff = yuanIndexList.get(r) - yuanIndexList.get(l) - (r - l);

            if (diff > flaw) {
                l++;
            } else if (diff < flaw) {
                r++;
            } else {
                ans = Math.max(ans, yuanIndexList.get(r) - yuanIndexList.get(l) + 1);
                r++;
            }
        }

        return ans;
    }
}